import java.util.ArrayList;

/**
 * Created by Justus on 21.02.2017.
 */
public class PossibilityManager {
    Frame frame;
  public  PossibilityManager(Frame frame){
        this.frame = frame;
    }

    public void solve() {
        for (int i = 0; i < 9; i++){
            for (int a = 0; a < 9; a++) {
                if (frame.getTileTable()[a][i].getValue() == 0){
                    look4Possibilites(i,a);
                }

            }
        }
    }
    public ArrayList<Integer> look4Possibilites (int i, int a){
        ArrayList<Integer> possibilities = new ArrayList<>();
      for (int b = 0; b < 9; b++){
          frame.
      }
      return possibilities;
    }
}
