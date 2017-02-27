import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Frame extends JFrame {

    private static int WIDTH = 1700, HEIGHT = 1400;
    BufferedImage image;
    private JPanel contentPane;
    private GridBagConstraints c;

    private Main main = new Main(this);

    private Tile[][] tileTable = new Tile[9][9];
    private JTextField[][] inputTable = new JTextField[9][9];

    private JButton inputAndOutputButton = new JButton("INPUT");

    private JButton actionButton = new JButton("ACTION");
    private JButton possibilitiesButton = new JButton("POSSIBILITIES");

    private boolean transformed = false, inputState, outputState;

    /*
     * Boundaries of the Frame is set. ContentPane is initialized. GridBagLayout is
     * added and defined. GridBagConstraints are set so the fit the desired design.
     * TextFields are initialized. The Button's ActionListener is initialized to
     * switch between input and output states.
     */
    private Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, WIDTH, HEIGHT);
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = WIDTH / 16;
        c.ipady = HEIGHT / 20;

        inputAndOutputButton.setFont(new Font("TimesNewRoman", Font.BOLD, 26));
        actionButton.setFont(new Font("TimesNewRoman", Font.BOLD, 26));
        possibilitiesButton.setFont(new Font("TimesNewRoman", Font.BOLD, 26));

        generateTextFieldsAndLabels();
        inputAndOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputState) {
                    processInput();
                } else if (outputState) {
                    inputState();
                }
            }
        });
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (outputState) {
                    main.start(tileTable);
                }
            }
        });
        possibilitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPossibilities();
            }
        });

        c.gridy = 9;
        c.gridx = 0;
        c.gridwidth = 3;
        contentPane.add(inputAndOutputButton, c);
        c.gridx = 3;
        contentPane.add(actionButton, c);
        c.gridx = 6;
        contentPane.add(possibilitiesButton, c);
        c.gridwidth = 1;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame();
                    frame.setTitle("Sudoku Test");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
     * Generates the textFields(input) and tiles(output) and adds the textFields
     * onto the contentPane for the first input. Also assigns a each tile to a
     * square from 0 to 8 according to their position on he sudoku.
     */
    private void generateTextFieldsAndLabels() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tileTable[i][j] = new Tile(i / 3 + (j / 3) * 3);
                inputTable[i][j] = new JTextField();
                c.gridx = i;
                c.gridy = j;
                contentPane.add(inputTable[i][j], c);
            }
        }
        outputState = false;
        inputState = true;
    }

    /*
     * Takes the input values from the textFields and makes them the values of
     * the tiles. If no value or an invalid value is written value is set to 0.
     * (reverse of inputState())
     */
    private void processInput() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    tileTable[i][j].setValue(Integer.valueOf(inputTable[i][j].getText()));

                } catch (Exception e) {
                    tileTable[i][j].setValue(0);
                }
            }
        }
        updateLabels();
        removeTextFieldsAddTiles();
        inputState = false;
        outputState = true;
    }


    /*
     * Takes the input values from the tiles and makes them the values of the
     * textFields. (reverse of processInput())
     */
    private void inputState() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tileTable[i][j].getValue() == 0) {
                    inputTable[i][j].setText("");
                } else {
                    inputTable[i][j].setText(String.valueOf(tileTable[i][j].getValue()));
                }
            }
        }
        removeTilesAddTextFields();
        outputState = false;
        inputState = true;
    }

    /*
     * What the name says.
     */
    private void removeTextFieldsAddTiles() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.getContentPane().remove(inputTable[i][j]);
                c.gridx = i;
                c.gridy = j;
                contentPane.add(tileTable[i][j], c);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
        repaint();
    }

    /*
     * What the name says.
     */
    private void removeTilesAddTextFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.getContentPane().remove(tileTable[i][j]);
                c.gridx = i;
                c.gridy = j;
                contentPane.add(inputTable[i][j], c);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
        repaint();
    }

    /*
    changes the tiles showPossibilities boolean so that the possibilities can be displayed. updates the labels.
     */
    private void showPossibilities() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tileTable[i][j].switchShowPossibilities();
            }
        }
        updateLabels();
    }


    /*
    updates all the labels displays: Shows the new, decimated possibilities or even a successful solve of a tile.
     */
    void updateLabels() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tileTable[i][j].display();
            }
        }
    }

	/*
     *
	 * ------------------- Getters and Setters -------------------
	 * 
	 */

    public Tile[][] getTileTable() {
        return tileTable;
    }

    public void setTileTable(Tile[][] tileTable) {
        this.tileTable = tileTable;
    }
}
