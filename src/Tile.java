import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class Tile extends JLabel {

    private int value, square;
    private boolean defined = false, showPossibilities = false;
    private ArrayList<Integer> possibilities = new ArrayList<>();

    Tile(int square) {
        setFont(new Font("TimesNewRoman", Font.PLAIN, 12));
        this.square = square;
        for (int i = 0; i < 9; i++) {
            possibilities.add(i + 1);
        }
    }

    void removePossibilities(Set<Integer> impossibilities) {
        for (Integer imp : impossibilities) {
            if (possibilities.contains(imp)) {
                possibilities.remove(imp);
            }
            /*for (int i = 0; i < possibilities.size(); i++) {
                if (possibilities.get(i) == imp) {
                    possibilities.remove(i);
                }
            }*/
        }
        if (possibilities.size() == 1) {
            setValue(possibilities.get(0));
        }
    }


    void display() {
        if (defined) {
            setFont(new Font("TimesNewRoman", Font.BOLD, 24));
            setText(String.valueOf(value));
        } else if (showPossibilities) {
            setText("");
            for (Integer p : possibilities) {
                setText(getText() + p + " ");
            }
        } else {
            setText("");
        }
    }

    // TODO solve remove problem where index changes during remove process.
    public void removeAllPossibilitiesBut(int p) {
        for (int i = 0; i < possibilities.size(); i++) {
            if (possibilities.get(i) != p) {
                possibilities.remove(i);
                i = 0;
            }
        }
    }

	/*
     *
	 * ------------------- Getters and Setters -------------------
	 * 
	 */

    public boolean isDefined() {
        return defined;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        if (value > 0 && value < 10) {
            this.value = value;
            defined = true;
        } else {
            this.value = 0;
        }
        //removeAllPossibilitiesBut(this.value);

    }

    ArrayList<Integer> getPossibilities() {
        return possibilities;
    }

    int getSquare() {
        return square;
    }

    void switchShowPossibilities() {
        showPossibilities = !showPossibilities;
    }
}
