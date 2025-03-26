package trivia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu

/**
 * Status panel for the Animated Movies Trivia Game.
 * Displays connection status and player ID.
 * 
 * @author Shahadat Anadil
 */

/**
 * Constructor initializes the status panel.
 */
public class AnimatedMoviesTriviaGameStatusPanel extends JPanel {

  private static final Color GOLD = Color.decode("#f1dfa9");
  private static final Color DARK_GREEN = Color.decode(("#246108"));

  private JLabel serverStatus;

  public AnimatedMoviesTriviaGameStatusPanel() {
    super();
    initialize();
  }

  /**
   * Initializes the panel layout and components.
   */
  private void initialize() {
    this.setBackground(DARK_GREEN);
    this.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.setLayout(new BorderLayout());

    serverStatus = new JLabel();
    serverStatus.setFont(new Font("Arial", Font.BOLD, 16));

    this.add(serverStatus, BorderLayout.WEST);
    this.updateServerDisconnected();

  }

  /**
   * Updates the status to indicate a connected server.
   * 
   * @param playerID The ID of the connected player.
   */
  public void updateServerConnected(int playerID) {
    serverStatus.setText("Player " + playerID);
    serverStatus.setForeground(GOLD);
  }

  /**
   * Updates the status to indicate a disconnected server.
   */
  public void updateServerDisconnected() {
    serverStatus.setText("Disconnected");
    serverStatus.setForeground(Color.RED);
  }

  /**
   * Main method to simulate the status panel.
   * 
   * @param args Command-line arguments.
   * @throws InterruptedException If the thread sleep is interrupted.
   */
  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("[sha38] Animated Movies Trivia Game - Status Panel");

    AnimatedMoviesTriviaGameStatusPanel panel = new AnimatedMoviesTriviaGameStatusPanel();
    frame.add(panel);
    frame.pack();
    frame.setSize(686, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    Thread.sleep(5000);
    panel.updateServerConnected(10);
    Thread.sleep(5000);
    panel.updateServerDisconnected();
  }
}