package reversi.strategies;

import reversi.Board;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Strategy to determine move by checking all valid moves and calculating the maximum number of flipped opponent tokens.
 */
public class MaxTokenStrategy implements ReversiStrategy {
    Random random = new Random();
    

    @Override
    public int[] getTarget(Board board, char tokenColor) {
       
    	int[] result = {0,0};
        int[][] moves = board.getValidMoves(tokenColor); 
        int length = 0;
        for(int i = 0; moves[i] != null; i++){ 
            length++;
        }
        int num = random.nextInt(length); 
        result = moves[num]; 

        System.out.println(tokenColor + " is next in line.");
       
        pause();

        return result;
    }

    private void pause() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
