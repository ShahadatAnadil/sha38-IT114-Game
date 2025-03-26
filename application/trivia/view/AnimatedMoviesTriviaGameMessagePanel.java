package trivia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu

/**
 * This class represents a message panel
 * for displaying trivia game messages with an image background.
 * It provides methods for setting and clearing messages dynamically.
 * 
 * @author Shahadat Anadil
 */
public class AnimatedMoviesTriviaGameMessagePanel extends JPanel {

    private static final Color GOLD = Color.decode("#f1dfa9");
    private static final Color DARK_RED = Color.decode("#B8211F");
    private static final String IMAGE_FILENAME = "/trivia/resources/images/trivia_game.jpg";

    JTextArea textArea;
    JPanel textPanel;

    /**
     * this constructs the message panel with an image background and a text area.
     */
    public AnimatedMoviesTriviaGameMessagePanel() {
        this.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(getClass().getResource(IMAGE_FILENAME));
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(686, 386, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
        imageLabel.setLayout(new GridBagLayout());

        textArea = new JTextArea(6, 18);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(GOLD);
        textArea.setFont(new Font("Arial", Font.BOLD, 18));

        BevelBorder customBorder = (BevelBorder) BorderFactory.createBevelBorder(
                BevelBorder.RAISED,
                GOLD,
                DARK_RED);

        textPanel = new JPanel();
        textPanel.setBackground(Color.BLACK);
        textPanel.setBorder(customBorder);
        textPanel.add(textArea);

        imageLabel.add(textPanel, new GridBagConstraints());

        this.add(imageLabel, BorderLayout.CENTER);
        this.clearText();
    }

    /**
     * This clears the message displayed in the text panel.
     */
    public void clearText() {
        textPanel.setVisible(false);
        textArea.setText("");
        revalidate();
        repaint();
    }

    /**
     * Sets the specified text in the message panel.
     * 
     * @param text The message to be displayed.
     */
    public void setText(String text) {
        if (textArea != null) {
            textPanel.setVisible(true);
            textArea.setText(text);
            revalidate();
            repaint();
        }
    }

    /**
     * A Main method to simulate the functionality of the message panel.
     * 
     * @param args Command-line arguments.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("[sha38] Animated Movie Trivia Game - Message Panel");

        AnimatedMoviesTriviaGameMessagePanel panel = new AnimatedMoviesTriviaGameMessagePanel();
        frame.add(panel);
        frame.pack();
        frame.setSize(686, 386);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Thread.sleep(5000);
        panel.setText("Animated Movie trivia question here.");
        Thread.sleep(5000);
        panel.setText("Next question here.");
        Thread.sleep(5000);
        panel.clearText();
        Thread.sleep(5000);
        panel.setText("Game Over");
    }
}


/**
     * try {
     * // Simulate server connection
     * Thread.sleep(3000);
     * panel.setServerConnected(1);
     * System.out.println("Server connected, Player ID: 1");
     * 
     * // Enable the game for input
     * Thread.sleep(2000);
     * panel.setEnable();
     * System.out.println("Game enabled, user can enter answers.");
     * 
     * // Display first trivia question
     * Thread.sleep(3000);
     * panel.setMessage("Question: Who won the Best Actor Oscar in 2020?");
     * System.out.println("Question displayed: Who won the Best Actor Oscar in
     * 2020?");
     * 
     * // Simulate player answering
     * Thread.sleep(5000);
     * panel.setMessage("Answer submitted: Joaquin Phoenix");
     * System.out.println("Player answered: Joaquin Phoenix");
     * 
     * // Update scoreboard
     * Thread.sleep(3000);
     * panel.updateScoreBoard(new HashMap<>(Map.of(1, 10, 2, 5)));
     * System.out.println("Scoreboard updated: Player 1 - 10 points, Player 2 - 5
     * points");
     * 
     * // Display next question
     * Thread.sleep(4000);
     * panel.setMessage("Next Question: Which movie won Best Picture in 1994?");
     * System.out.println("Next question displayed.");
     * 
     * // Simulate incorrect answer
     * Thread.sleep(5000);
     * panel.setMessage("Answer submitted: Titanic (Incorrect)");
     * System.out.println("Player answered incorrectly: Titanic");
     * 
     * // Scoreboard remains unchanged
     * Thread.sleep(3000);
     * panel.updateScoreBoard(new HashMap<>(Map.of(1, 10, 2, 5)));
     * System.out.println("Scoreboard unchanged.");
     * 
     * // Simulate restarting game
     * Thread.sleep(5000);
     * panel.setMessage("Restarting game...");
     * panel.setDisable();
     * panel.updateScoreBoard(new HashMap<>());
     * Thread.sleep(3000);
     * panel.setEnable();
     * panel.setMessage("New game started! Get ready.");
     * System.out.println("Game restarted.");
     * 
     * // Simulate quitting game
     * Thread.sleep(5000);
     * panel.setMessage("Game Over. Exiting...");
     * Thread.sleep(2000);
     * frame.dispose();
     * System.out.println("Game window closed.");
     * } catch (InterruptedException e) {
     * e.printStackTrace(); // Handle exception properly (e.g., logging)
     * }
     **/