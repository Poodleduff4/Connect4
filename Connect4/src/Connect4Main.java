import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Connect4Main {
    static int numColumn = 7;
    static int numRows = 6;
    static int count = 0;
    static int player = 1;
    static int[][] grid = new int[numRows][numColumn];
    static JLabel[][] hole = new JLabel[numRows][numColumn];
    static JButton[] column = new JButton[numColumn];
    static int y;
    static int winRow = 0;
    static int winCol = 0;


    private static final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == column[0]) {
                play(0);
                printGrid();
            } else if (actionEvent.getSource() == column[1]) {
                play(1);
                printGrid();
            } else if (actionEvent.getSource() == column[2]) {
                play(2);
                printGrid();
            } else if (actionEvent.getSource() == column[3]) {
                play(3);
                printGrid();
            } else if (actionEvent.getSource() == column[4]) {
                play(4);
                printGrid();
            } else if (actionEvent.getSource() == column[5]) {
                play(5);
                printGrid();
            } else if (actionEvent.getSource() == column[6]) {
                play(6);
                printGrid();
            }
        }
    };

    public static void main(String[] args) {
        int w1 = 800;
        int h1 = 50;
        int w2 = 800;
        int h2 = 700;

        JPanel board = new JPanel();
        JFrame frame = new JFrame("Connect 4");
        JPanel columnChooser = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, h1 + h2);

        board.setLocation(0, h1 + 1);
        board.setSize(w2, h2);
        board.setBackground(Color.gray);

        columnChooser.setLocation(0, 0);
        columnChooser.setSize(w1, h1 + 1);
        columnChooser.setBackground(Color.gray);

        //create the JLabels for the pieces
        int x = 1;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumn; j++) {
                hole[i][j] = new JLabel("" + x);
                hole[i][j].setPreferredSize(new Dimension(100, 100));
                hole[i][j].setSize(100, 100);
                hole[i][j].setOpaque(true);
                hole[i][j].setHorizontalAlignment(JLabel.CENTER);
                board.add(hole[i][j]);
                hole[i][j].setBackground(Color.WHITE);
                hole[i][j].setForeground(Color.BLACK);
                x++;
            }
        }

//Action Listeners to detect which column the user clicked
        for (int i = 0; i < numColumn; i++) {
            column[i] = new JButton("" + i);
            column[i].setPreferredSize(new Dimension(100, 40));
            columnChooser.add(column[i]);
            column[i].addActionListener(actionListener);

        }

        
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(board);
        frame.add(columnChooser);
    }

    static void play(int columnNum) {
        for (int i = numRows - 1; i >= 0; i--) {
            if (grid[i][columnNum] == 0) {
                grid[i][columnNum] = player;
                hole[i][columnNum].setBackground((player == 1) ? Color.RED : Color.YELLOW);
                y = i;
                if (grid[0][columnNum] != 0) {
                    column[columnNum].setEnabled(false);
                }
                break;
            }
        }
        System.out.println(winCheck(grid, columnNum, y, count, player));
        if (winCheck(grid, columnNum, y, count, player)) {
            System.out.println("Player " + player + "Wins");
            for (int i = 0; i < numColumn; i++) {
                column[i].setEnabled(false);
            }
        }
        player = (player == 1) ? 2 : 1;
    }

    private static void printGrid() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumn; j++) {
                System.out.print(grid[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static boolean winCheck(int[][] grid, int column, int row, int count, int player) {
        System.out.println("Column: " + column);
        System.out.println("Row: " + row);
        System.out.println(count);

        //horizontal check
        for (int i = 0; i < numColumn; i++) {
            if (grid[row][i] == player)
                count++;
            else
                count = 0;
            if (count == 4) {
                return true;
            }
        }

        count = 0;
        //vertical check
        for (int i = 0; i < numRows; i++) {
            if (grid[i][column] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                winCol = column;
                return true;
            }
        }
        return false;
    }
}