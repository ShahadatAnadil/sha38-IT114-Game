package trivia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu

/**
 * displays the game state for animated movies trivia game
 * manages the player scores and game related operations
 * 
 * @author Shahadat Anadil
 */

public class AnimatedMoviesTriviaGameState implements Serializable {
    public String message; // Original message from a client.
    public int senderID; // The ID of the client who sent that message.

    // a map that sores player scores, the key is the player ID and the value is
    // their score
    public HashMap<Integer, Integer> playerScores = new HashMap<>();

    /**
     * constructs an instance of the game state, initializig player scores
     */
    public AnimatedMoviesTriviaGameState() {
        playerScores = new HashMap<>();
    }

    /**
     * add new player to game with a starting score of 0
     * 
     * @param playerID unique id of player
     */
    public void addPlayer(int playerID) {
        playerScores.put(playerID, 0);
    }

    /**
     * removes player from game
     * 
     * @param playerID the id of the player to be removed
     */
    public void removePlayer(int playerID) {
        playerScores.remove(playerID);
    }

    /**
     * get total # of players in game
     * 
     * @return the # of players in game
     */
    public int getPlayerCount() {
        return playerScores.size();
    }

    /**
     * increments the score of the specified player by 1
     * 
     * @param playerID the id of player sscore that will increase
     */
    public void incrementScore(int playerID) {
        int score = playerScores.get(playerID);
        score++;
        playerScores.replace(playerID, score);
    }

    /**
     * checks if any players has scored at least one point.
     * 
     * @return true if any player score greater than 0, false otherwise
     */
    public boolean hasAnyPlayerScored() {
        for (Integer score : playerScores.values()) {
            if (score > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * determines the winner of the game based on who ever has the highest score
     * 
     * @return player ID of winner, -1 if it's a tie
     */
    public int getWinner() {
        ArrayList<Integer> maxKeys = new ArrayList<>();
        int maxValue = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : playerScores.entrySet()) {
            int value = entry.getValue();
            if (value > maxValue) {
                maxKeys.clear();
                maxKeys.add(entry.getKey());
                maxValue = value;
            } else if (value == maxValue) {
                maxKeys.add(entry.getKey());
            }
        }
        // there is more then 1 player with the highest score
        if (maxKeys.size() > 1)
            return -1;
        else
            return maxKeys.get(0);
    }

    /**
     * reset all players' scores to 0
     */
    public void clearScores() {
        for (Map.Entry<Integer, Integer> entry : playerScores.entrySet()) {
            entry.setValue(0);
        }
    }
}
