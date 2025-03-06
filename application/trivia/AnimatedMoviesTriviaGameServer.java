package trivia;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import netgame.common.Hub;
//Shahadat Anadil, 3/06/2025, IT114-004, Phase 2, sha38@njit.edu

/**
 * the server for the game
 * handles the player connections, agme state management, and message processing
 * manages the game flow, including handling player answers and starting new game
 * 
 * @author Shahadat Anadil
 * @version 3/06/2025
 */
public class AnimatedMoviesTriviaGameServer extends Hub {

    private final static int PORT = 37829;

    private AnimatedMoviesTriviaGameState state;

    private AnimatedMoviesTriviaQuestionsList questions;
    private int currentQuestionIndex = -1;
    private Map<Integer, String> answersReceived; // Tracks answers from each player.


    /**
     * constructs a new instance of the trivia game server
     * intializes the game state, sets up networking, and prepares the question list
     * @throws IOException if an error occurs while starting the server
     */
    public AnimatedMoviesTriviaGameServer() throws IOException {
        super(PORT);
        setAutoreset(true);
        state = new AnimatedMoviesTriviaGameState();
        initializeNewGame();
    }

    /**
     * Initialize a new game by resetting the state and loading questions.
     */
    private void initializeNewGame() {
        state.clearScores();
        questions = new AnimatedMoviesTriviaQuestionsList();
        currentQuestionIndex = -1;
        answersReceived = new HashMap<>();
    }

    @Override
    protected void messageReceived(int playerID, Object message) {
        if (message instanceof String) {
            String command = ((String) message).trim();

            if (command.equalsIgnoreCase("restart")) {
                if (state.getPlayerCount() >= 2) {
                    sendToAll("[sha38] A new game is starting!");
                    initializeNewGame();
                    startGame();
                } else {
                    sendToAll("[sha38] Waiting for at least 2 players to start a new game.");
                }
            } else {
                handleAnswer(playerID, command);
            }
        }
    }

    @Override
    protected void playerConnected(int playerID) {
        System.out.println("[sha38] Player connected: " + playerID);
        state.addPlayer(playerID);

        if (state.getPlayerCount() == 1) {
            sendToAll("[sha38] Waiting for another player to join...");
        } else if (state.getPlayerCount() == 2) {
            sendToAll("[sha38] Two players connected. Starting the game!");
            startGame();
        }
    }

    @Override
    protected void playerDisconnected(int playerID) {
        System.out.println("[sha38] Player disconnected: " + playerID);
        state.removePlayer(playerID);

        if (state.getPlayerCount() < 2) {
            sendToAll("[sha38] A player disconnected. Waiting for another player to continue the game.");
            initializeNewGame();
        }
        // Remove disconnected player's answers.
        synchronized (answersReceived) {
            answersReceived.remove(playerID);
        }
    }

    /**
     * Starts the trivia game.
     */
    private void startGame() {
        currentQuestionIndex = -1;
        nextQuestion();
    }

    /**
     * Handle answers from players.
     */
    private void handleAnswer(int playerID, String answer) {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {

            synchronized (answersReceived) {
                if (!answersReceived.containsKey(playerID)) {
                    answersReceived.put(playerID, answer);
                    System.out.println("[sha38] Player " + playerID + " answered: " + answer);
                    sendToAll("[sha38] Player " + playerID + " answered: " + answer);

                    if (answersReceived.size() == state.getPlayerCount()) {
                        System.out.println("[sha38] All players have answered.");
                        sendToAll("[sha38] All players have answered.");
                        // All players have answered, cancel the timer and proceed.
                        evaluateAnswers();
                    }
                }
            }
        }
    }

    /**
     * Moves to the next question and sets a timer for players to answer.
     */
    private void nextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex >= questions.size()) {
            endGame();
            return;
        }

        AnimatedMoviesTriviaQuestion currentQuestion = questions.get(currentQuestionIndex);
        sendToAll("[sha38] Question: " + currentQuestion.question());
    }

    /**
     * Evaluate the answers received for the current question.
     */
    private void evaluateAnswers() {
        AnimatedMoviesTriviaQuestion currentQuestion = questions.get(currentQuestionIndex);

        for (Map.Entry<Integer, String> entry : answersReceived.entrySet()) {
            int playerID = entry.getKey();
            String answer = entry.getValue();

            if (currentQuestion.isCorrectAnswer(answer)) {
                state.incrementScore(playerID);
                sendToAll("[sha38] Player " + playerID + " answered correctly! The answer was: " + currentQuestion.answer());
            } else {
                sendToAll("[sha38] Player " + playerID + " answered incorrectly.");
            }
        }
        answersReceived = new HashMap<>();
        nextQuestion();
    }

    /**
     * End the game and declare the result.
     */
    private void endGame() {
        if (!state.hasAnyPlayerScored()) {
            sendToAll("[sha38] The game ended with no correct answers. Better luck next time!");
        } else {
            int winner = state.getWinner();
            if (winner == -1) {
                sendToAll("[sha38] The game ended in a tie!");
            } else {
                sendToAll("[sha38] Player " + winner + " wins the game!");
            }
        }
        sendToAll("[sha38]Type 'restart' to play again.");
    }

    public static void main(String[] args) {
        try {
            new AnimatedMoviesTriviaGameServer();
        } catch (IOException e) {
            System.out.println("[sha38] Error starting server: " + e.getMessage());
        }
    }
}