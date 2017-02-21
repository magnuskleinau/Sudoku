import java.util.ArrayList;

/**
 * Created by Justus on 21.02.2017.
 */
public class PossibilityManager {
    Frame frame;

    public PossibilityManager(Frame frame) {
        this.frame = frame;
    }

    ///*
// Method that runs through our Sudoku Table and calls the look for Impossibilities Method.
//
// */
    public void solve() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (frame.getTileTable()[i][j].getValue() == 0) {
                    frame.getTileTable()[i][j].removePossibilties(lookForImpossibilites(i, j));
                    break;
                }


            }
        }
    }

    /*
    * Method that looks at all the Numbers in the same Column and Line to eliminate the Possibilities.
    * */
    public ArrayList<Integer> lookForImpossibilites(int i, int j) {
        ArrayList<Integer> impossibilities = new ArrayList<>();
        for (int k = 0; k < 9; k++) {
            if (frame.getTileTable()[i][k].getValue() != 0) {
                impossibilities.add(frame.getTileTable()[i][k].getValue());
            }
        }
        for (int l = 0; l < 9; l++) {
            if (frame.getTileTable()[l][j].getValue() != 0) {
                impossibilities.add(frame.getTileTable()[l][j].getValue());
            }
        }
        for (int m = 0; m < 9; m++) {

        }
        return impossibilities;
    }
}
