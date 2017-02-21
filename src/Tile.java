import javax.swing.*;
import java.util.ArrayList;

public class Tile extends JLabel {

    int value, field;
    boolean defined = false;
    ArrayList<Integer> possibilities = new ArrayList<>();

    public Tile(int field) {
        this.field = field;
        for (int i = 0; i < 9; i++) {
            possibilities.add(i + 1);
        }
    }

    public void removePossibilties(ArrayList<Integer> impossibilities) {
        for (Integer imp : impossibilities) {
            for (int i = 0; i < possibilities.size(); i++) {
                if (possibilities.get(i) == imp) {
                    possibilities.remove(i);
                }
            }
        }
        if (possibilities.size() == 1) {
            setValue(possibilities.get(0));
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

    public boolean isDefined() {
        return defined;
    }

	/*
     *
	 * ------------------- Getters and Setters -------------------
	 * 
	 */

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if (value > 0 && value < 10) {
            setText(String.valueOf(this.value));
        } else {
            this.value = 0;
            setText("");
        }
        //removeAllPossibilitiesBut(this.value);
        defined = true;
    }

    public ArrayList<Integer> getPossibilities() {
        return possibilities;
    }

    public int getField() {
        return field;
    }
}
