package trivia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import trivia.AnimatedMoviesTriviaGamePlayerWindow;

// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu

/**
 * This is the main panel for the Animated Movies Trivia Game.
 * Contains the UI elements for interacting with the trivia game,
 * including an answer input field, control buttons, and status panels.
 *
 * @author Shahadat Anadil
 */

public class AnimatedMoviesTriviaGameMainPanel extends JPanel {

  private static final Color DARK_GREEN = Color.decode("#2A8E37");
  private static final Color DARK_RED = Color.decode(("#B8211F"));
  private static final Color GOLD = Color.decode("#f1dfa9");

  private AnimatedMoviesTriviaGameStatusPanel statusPanel;
  private AnimatedMoviesTriviaGameMessagePanel messagePanel;
  private AnimatedMoviesTriviaGameScoreBoardPanel scoreBoardPanel;

  private AnimatedMoviesTriviaGamePlayerWindow window;

  private JLabel answerLabel;
  private JTextField answerText;
  private JButton sendButton;
  private JButton restartButton;
  private JButton quitButton;

  /**
   * The constructor for the main panel of the trivia game.
   *
   * @param window The main game window managing player actions.
   */

  public AnimatedMoviesTriviaGameMainPanel(AnimatedMoviesTriviaGamePlayerWindow window) {
    super();
    this.window = window;
    initialize();
  }

  /**
   * Initializes the main panel components, setting up the UI layout and buttons.
   */
  private void initialize() {
    this.setBackground(Color.BLACK);
    this.setLayout(new BorderLayout(2, 2));

    statusPanel = new AnimatedMoviesTriviaGameStatusPanel();
    this.add(statusPanel, BorderLayout.NORTH);

    scoreBoardPanel = new AnimatedMoviesTriviaGameScoreBoardPanel();
    this.add(scoreBoardPanel, BorderLayout.EAST);

    messagePanel = new AnimatedMoviesTriviaGameMessagePanel();
    this.add(messagePanel, BorderLayout.WEST);

    JPanel controlPanel = new JPanel();
    controlPanel.setBackground(DARK_GREEN);

    answerLabel = new JLabel("Answer:");
    answerLabel.setForeground(GOLD);
    answerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    controlPanel.add(answerLabel);

    answerText = new JTextField(40);
    controlPanel.add(answerText);

    sendButton = new JButton("Send");
    sendButton.setForeground(DARK_GREEN);
    sendButton.setFont(new Font("Arial", Font.BOLD, 16));
    controlPanel.add(sendButton);

    controlPanel.add(Box.createHorizontalStrut(30));
    restartButton = new JButton("Restart");
    restartButton.setForeground(DARK_GREEN);
    restartButton.setFont(new Font("Arial", Font.BOLD, 16));
    controlPanel.add(restartButton);
    quitButton = new JButton("Quit");
    quitButton.setForeground(DARK_RED);
    quitButton.setFont(new Font("Arial", Font.BOLD, 16));
    controlPanel.add(quitButton);

    answerText.addActionListener(this::sendAnswer);
    sendButton.addActionListener(this::sendAnswer);
    restartButton.addActionListener(this::restartGame);
    quitButton.addActionListener(this::quitGame);

    // controlPanel.add(quitButton);
    this.add(controlPanel, BorderLayout.SOUTH);

    this.setDisable();
  }

  /**
   * Sends the answer input by the player to the game server.
   *
   * @param event The action event triggered by the player.
   */
  private void sendAnswer(ActionEvent event) {
    String message = answerText.getText();
    if (message.trim().length() == 0)
      return;
    window.send(message);
    answerText.setText("");
    answerText.requestFocus();
  }

  /**
   * Sends a restart command to the game server.
   *
   * @param event The action event triggered by clicking the restart button.
   */
  private void restartGame(ActionEvent event) {
    window.send("restart");
  }

  /**
   * Quits the game by invoking the appropriate function in the main game window.
   *
   * @param event The action event triggered by clicking the quit button.
   */
  private void quitGame(ActionEvent event) {
    window.doQuit();
  }

  /**
   * Disables user input fields and buttons.
   */
  public void setDisable() {
    answerText.setEditable(false);
    answerText.setEnabled(false);
    answerText.setBackground(Color.LIGHT_GRAY);
    answerText.setText("");
    sendButton.setEnabled(false);
    restartButton.setEnabled(false);
  }

  /**
   * Enables user input fields and buttons.
   */
  public void setEnable() {
    answerText.setEditable(true);
    answerText.setEnabled(true);
    answerText.setBackground(Color.WHITE);
    answerText.requestFocus();
    sendButton.setEnabled(true);
    restartButton.setEnabled(true);
  }

  /**
   * Updates the UI to indicate server connection.
   *
   * @param playerID The ID assigned to the connected player.
   */
  public void setServerConnected(int playerID) {
    statusPanel.updateServerConnected(playerID);
  }

  /**
   * Updates the UI to indicate server disconnection, clearing messages and
   * resetting scores.
   */
  public void setServerDisconnected() {
    statusPanel.updateServerDisconnected();
    messagePanel.clearText();
    scoreBoardPanel.resetPlayers(new HashMap<>());
  }

  /**
   * Displays a message in the message panel.
   *
   * @param message The message to display.
   */
  public void setMessage(String message) {
    messagePanel.setText(message);
  }

  /**
   * Updates the scoreboard with the latest player scores.
   *
   * @param playerScores A map of player IDs to their respective scores.
   */
  public void updateScoreBoard(HashMap<Integer, Integer> playerScores) {
    scoreBoardPanel.resetPlayers(playerScores);
  }

  public void updateQuestionTimer(boolean questionTimer) {
    if(questionTimer)
      scoreBoardPanel.startQuestionTimer();
    else
      scoreBoardPanel.stopTimer();
  }
 

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("[sha38] Animated Movies Trivia Game - Main Panel");

    AnimatedMoviesTriviaGameMainPanel panel = new AnimatedMoviesTriviaGameMainPanel(null);
    frame.add(panel);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
    System.out.println("Connecting to server...");
    Thread.sleep(5000);
    panel.setServerConnected(1);
    
    
    System.out.println("Enabling user input...");
    Thread.sleep(5000);
    panel.setEnable();

    
    System.out.println("Sending message to message panel...");
    Thread.sleep(5000);
    panel.setMessage("Welcome to Animated Movies Trivia!");

    System.out.println("Updating scoreboard...");
    Thread.sleep(5000);
    HashMap<Integer, Integer> scores = new HashMap<>();
    scores.put(1, 3);
    scores.put(2, 5);
    scores.put(3, 2);
    panel.updateScoreBoard(scores);

    
    System.out.println("Disabling user input...");
    Thread.sleep(5000);
    panel.setDisable();

    
    System.out.println("Disconnecting from server...");
    Thread.sleep(5000);
    panel.setServerDisconnected();

    
    System.out.println("Re-enabling user input for new session...");
    Thread.sleep(5000);
    panel.setEnable();
    

  }
}
