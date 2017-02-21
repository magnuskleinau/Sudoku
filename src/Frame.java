import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Frame extends JFrame {

    JPanel contentPane;
    BufferedImage image;
    GridBagConstraints c;

    Main main = new Main(this);

    Tile[][] tileTable = new Tile[9][9];
    JTextField[][] inputTable = new JTextField[9][9];

    JButton inputAndOutputButton = new JButton("PROCESS INPUT");
    JButton actionButton = new JButton("ACTION");

    boolean transformed = false, inputState, outputState;

    /*
     * Boundaries of the Frame is set. ContentPane is initialized. MigLayout is
     * added and defined. TextFields are initialized. The Button's
     * ActionListener is initialized to switch between input and output states.
     */
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1000, 1000);
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 60;
        c.ipady = 50;


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

        c.gridy = 9;
        c.gridx = 0;
        c.gridwidth = 5;
        contentPane.add(inputAndOutputButton, c);
        c.gridx = 5;
        contentPane.add(actionButton, c);
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
     * onto the contentPane for the first input.
     */
    private void generateTextFieldsAndLabels() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tileTable[i][j] = new Tile(i / 3 + j / 3);
                inputTable[i][j] = new JTextField();
                c.gridx = i;
                c.gridy = j;
                contentPane.add(inputTable[i][j], c);
            }
        }
        outputState = false;
        inputState = true;
        inputAndOutputButton.setText("PROCESS INPUT");
    }

    /*
    Updates the values of the Tiles after solve attempt of program.
     */



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
        removeTextFieldsAddTiles();
        inputState = false;
        outputState = true;
        inputAndOutputButton.setText("INPUT");
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
        inputAndOutputButton.setText("PROCESS INPUT");
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
