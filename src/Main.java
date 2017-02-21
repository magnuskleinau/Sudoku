import javax.swing.JTextField;

public class Main {

	Frame frame;
	Tile[][] tiles;

	public Main(Frame frame) {
		this.frame = frame;
	}

	public void start(Tile[][] tiles) {
		this.tiles = tiles;
		showPossibilities();
	}

	private void showPossibilities() {
		int n = 9;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int p : tiles[i][j].getPossibilities()) {
					System.out.print(p);
				}
				for (int k = 0; k < n - tiles[i][j].getPossibilities().size(); k++) {
					System.out.print(" ");
				}
				System.out.print("\t");
			}
			System.out.print("\n");
		}

	}
}
