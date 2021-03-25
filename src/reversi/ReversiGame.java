package reversi;

import reversi.strategies.ReversiStrategy;

/**
 * An instance of this class holds the state of a running match and the game logic.
 */
public class ReversiGame {

    private final Board board;
    private final ReversiStrategy playerRedStrategy;
    private final ReversiStrategy playerYellowStrategy;

    /**
     * Set to TRUE to keep the game loop running. Set to FALSE to exit.
     */
    boolean running;

    public ReversiGame(ReversiStrategy playerRedStrategy, ReversiStrategy playerYellowStrategy) {
        this.board = new Board();
        this.playerRedStrategy = playerRedStrategy;
        this.playerYellowStrategy = playerYellowStrategy;
    }

    /**
     * Main game loop. Keep running to play.
     * Interrupt the loop to get back to main menu.
     */
    public void run() {
        System.out.println("Game started. Click on ENTER, to go back in main menu.");

        running = true;

        while(running){
            board.print(); 
            int[] targetRed = playerRedStrategy.getTarget(board, Board.RED); 
            if(targetRed == null){return;} 
            board.placeToken(targetRed, Board.RED); 
            if(!board.hasValidMoves(Board.YELLOW)){running = false; break;} 

            board.print(); 
            int[] targetYellow = playerYellowStrategy.getTarget(board, Board.YELLOW); 
            if(targetYellow == null){return;} 
            board.placeToken(targetYellow, Board.YELLOW); 
            if(!board.hasValidMoves(Board.RED)){running = false; break;} 
        }

        
        int counter_yellow = 0;
        for(int i = 0; i < Board.BOARD_SIZE; i++){
            for(int j = 0; j < Board.BOARD_SIZE; j++){
                if(board.fields[i][j] == Board.YELLOW) counter_yellow++; 
            }
        }
        int counter_red = 0;
        for(int i = 0; i < Board.BOARD_SIZE; i++){
            for(int j = 0; j < Board.BOARD_SIZE; j++){
                if(board.fields[i][j] == Board.RED) counter_red++; 
            }
        }

        if(counter_yellow > counter_red) System.out.println("YELLOW WIN!"); 
        else if(counter_red > counter_yellow) System.out.println("RED WIN!");
        else System.out.println("DRAW");
    }

    
    public boolean isFinished() {
        return !board.hasValidMoves(Board.RED) || !board.hasValidMoves(Board.YELLOW);
    }
}
