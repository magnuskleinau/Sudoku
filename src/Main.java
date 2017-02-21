import java.util.ArrayList;

public class Main {

    Frame frame;
    Tile[][] tiles;

    public Main(Frame frame) {
        this.frame = frame;
    }

    public void start(Tile[][] tiles) {
        solve();
        this.tiles = tiles;
    }

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
    private ArrayList<Integer> lookForImpossibilites(int i, int j) {
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
        for (int n = 0; n < 9; n++) {
            for (int o = 0; o < 9; o++) {
                if (frame.getTileTable()[n][o].getValue() != 0 && frame.getTileTable()[i][j].getField() == frame.getTileTable()[n][o].getField()) {
                    impossibilities.add(frame.getTileTable()[n][o].getValue());

                }
            }
        }

        return impossibilities;
    }
}
