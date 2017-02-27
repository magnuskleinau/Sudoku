import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Main {

    private Frame frame;
    private Tile[][] tiles;

    Main(Frame frame) {
        this.frame = frame;
    }

    void start(Tile[][] tiles) {
        this.tiles = tiles;
        solve();
    }

    private void solve() {
        findImpossibilities();
        checkUniquePossibilities();
        frame.updateLabels();
    }

    /*
    calls findImpossibilities for all the tiles and removes the found impossibilities from their possibility list.
     */
    private void findImpossibilities() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tiles[i][j].getValue() == 0) {
                    tiles[i][j].removePossibilities(impossibilities(i, j));
                }
            }
        }
    }

    /*
    * Method that looks at all the Numbers in the same Column and Line to eliminate the Possibilities.
    * */
    private Set<Integer> impossibilities(int i, int j) {
        Set<Integer> impossibilities = new HashSet<>();

        //Get horizontal and vertical impossibilities
        for (int k = 0; k < 9; k++) {
            if (tiles[i][k].getValue() != 0) {
                impossibilities.add(tiles[i][k].getValue());
            }
            if (tiles[k][j].getValue() != 0) {
                impossibilities.add(tiles[k][j].getValue());
            }
        }
        //Add impossibilities from the square
        for (int n = 0; n < 9; n++) {
            for (int o = 0; o < 9; o++) {
                if (tiles[n][o].getValue() != 0 && tiles[i][j].getSquare() == tiles[n][o].getSquare()) {
                    impossibilities.add(tiles[n][o].getValue());
                }
            }
        }

        return impossibilities;
    }

    private void checkUniquePossibilities() {
        ArrayList<Integer> possibilities = new ArrayList<>();
        ArrayList<ArrayList<Integer>> influences = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                influences.add(new ArrayList<>(influentialPossibilitiesHorizontal(i, j)));
                influences.add(new ArrayList<>(influentialPossibilitiesVertical(i, j)));
                influences.add(new ArrayList<>(influentialPossibilitiesSquare(i, j)));


                for (ArrayList<Integer> inf : influences) {
                    possibilities.clear();
                    possibilities.addAll(tiles[i][j].getPossibilities());
                    for (Integer p : inf) {
                        if (possibilities.contains(p)) {
                            possibilities.remove(p);
                        }
                    }
                    if (possibilities.size() == 1) {
                        tiles[i][j].setValue(possibilities.get(0));
                        break;
                    }
                }
                influences.clear();
            }
        }
    }

    /*
    Get horizontal influentialPossibilities
     */
    private Set<Integer> influentialPossibilitiesVertical(int i, int j) {
        Set<Integer> influentialPossibilities = new HashSet<>();
        for (int k = 0; k < 9; k++) {
            if (tiles[i][k].getValue() == 0 && k != j) {
                influentialPossibilities.addAll(tiles[i][k].getPossibilities());
            }
        }
        return influentialPossibilities;
    }

    /*
    Get vertical influentialPossibilities
     */
    private Set<Integer> influentialPossibilitiesHorizontal(int i, int j) {
        Set<Integer> influentialPossibilities = new HashSet<>();
        for (int k = 0; k < 9; k++) {
            if (tiles[k][j].getValue() == 0 && k != i) {
                influentialPossibilities.addAll(tiles[k][j].getPossibilities());
            }
        }
        return influentialPossibilities;
    }

    /*
    Add influentialPossibilities from the square
     */
    private Set<Integer> influentialPossibilitiesSquare(int i, int j) {
        Set<Integer> influentialPossibilities = new HashSet<>();
        for (int n = 0; n < 9; n++) {
            for (int o = 0; o < 9; o++) {
                if ((tiles[n][o].getValue() == 0) && (tiles[i][j].getSquare() == tiles[n][o].getSquare()) && ((n != i) || (o != j))) {
                    influentialPossibilities.addAll(tiles[n][o].getPossibilities());
                }
            }
        }
        return influentialPossibilities;
    }
}
