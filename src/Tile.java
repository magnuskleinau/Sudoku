import javax.swing.*;
import java.util.ArrayList;

public class Tile extends JLabel {

    int value;
    boolean defined = false;
    ArrayList<Integer> possibilities = new ArrayList<>();

    public Tile() {
        for (int i = 0; i < 9; i++) {
            possibilities.add(i + 1);
        }
    }

    public void removePossibilties(ArrayList<Integer> impossibilities) {
        for (int i = 0; i < impossibilities.size(); i++) {
            if (possibilities.contains(impossibilities.get(i))) {
                possibilities.remove(impossibilities.get(i));
            }
        }
        if (possibilities.size() == 1) {
            setValue(possibilities.get(0));
            defined = true;
        }
    }

    // TODO solve remove problem where index changes during remove process.
    public void removeAllPossibilitiesBut(int p) {
        int n = 0;
        while (possibilities.size() > 1) {
            if (possibilities.get(n) != p) {
                possibilities.remove(n);
            } else {
                n = 1;
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
        removeAllPossibilitiesBut(this.value);
        defined = true;
    }

    public ArrayList<Integer> getPossibilities() {
        return possibilities;
    }
}
