import java.util.ArrayList;

import javax.swing.JLabel;

public class Tile extends JLabel {

    int value;
    boolean defined = false;
    ArrayList<Integer> possibilities = new ArrayList<Integer>();

    public Tile() {
        for (int i = 0; i < 9; i++) {
            possibilities.add(i + 1);
        }
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

    public void removePossibility(int p) {
        possibilities.remove(p);
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

	/*
     *
	 * ------------------- Getters and Setters -------------------
	 * 
	 */

    public boolean isDefined() {
        return defined;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Integer> getPossibilities() {
        return possibilities;
    }
}
