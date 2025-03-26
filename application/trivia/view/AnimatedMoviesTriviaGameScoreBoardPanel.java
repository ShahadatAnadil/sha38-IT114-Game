package trivia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu

/**
 * This class displays the scoreboard panel for the Animated Movies Trivia Game.
 * It displays players' scores using a JTable component.
 *
 * Author: Shahadat Anadil
 */

public class AnimatedMoviesTriviaGameScoreBoardPanel extends JPanel {

  private static final Color GOLD = Color.decode("#f1dfa9");
  private static final Color DARK_GOLD = Color.decode("#d2b172");

  private DefaultTableModel tableModel;
  private JTable table;

  /**
   * Constructs the scoreboard panel and initializes its components.
   */
  public AnimatedMoviesTriviaGameScoreBoardPanel() {
    super();
    initialize();
  }

  /**
   * Initializes the scoreboard panel layout and JTable properties.
   */
  private void initialize() {
    this.setBackground(Color.BLACK);//207 , 300
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(207, 300));
    this.setBorder(BorderFactory.createLineBorder(DARK_GOLD, 3));

    // Setup table model with columns "Player" and "Score"
    tableModel = new DefaultTableModel(new Object[] { "Player", "Score" }, 0);
    table = new JTable(tableModel);
    table.setEnabled(false);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setBackground(Color.BLACK);
    table.setForeground(GOLD);
    table.setGridColor(Color.GRAY);
    table.setSelectionBackground(Color.DARK_GRAY);
    table.setFont(new Font("Arial", Font.BOLD, 14));

    JTableHeader header = table.getTableHeader();
    header.setBackground(Color.DARK_GRAY);
    header.setForeground(GOLD);
    header.setFont(new Font("Arial", Font.BOLD, 16));
    ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

    TableColumnModel columnModel = table.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(100);
    columnModel.getColumn(0).setMaxWidth(100);
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(1).setMaxWidth(100);

    this.add(table.getTableHeader(), BorderLayout.NORTH);
    this.add(table, BorderLayout.CENTER);
  }

  /**
   * Updates the scoreboard with new player scores.
   *
   * @param players A HashMap containing player IDs as keys and their scores as
   *                values.
   */
  public void resetPlayers(HashMap<Integer, Integer> players) {
    tableModel.setRowCount(0); // Clear existing data
    for (var entry : players.entrySet()) {
      tableModel.addRow(new Object[] { entry.getKey(), entry.getValue() });
    }
  }

  /**
   * Main method for simulating the scoreboard panel.
   *
   * @param args Command line arguments (not used).
   * @throws InterruptedException If the thread sleep is interrupted.
   */

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("[sha38] Animated Movies Trivia Game - Score Board Panel");

    AnimatedMoviesTriviaGameScoreBoardPanel panel = new AnimatedMoviesTriviaGameScoreBoardPanel();
    frame.add(panel);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    Thread.sleep(5000);
    panel.resetPlayers(new HashMap<>(Map.of(1, 1, 2, 2, 3, 3)));
    Thread.sleep(5000);
    panel.resetPlayers(new HashMap<>());
    Thread.sleep(5000);
    panel.resetPlayers(new HashMap<>(Map.of(1, 0, 2, 0)));
    Thread.sleep(5000);
    panel.resetPlayers(new HashMap<>(Map.of(1, 1, 2, 1)));
  }
}
