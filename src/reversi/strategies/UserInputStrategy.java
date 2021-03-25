package reversi.strategies;

import reversi.Board;

import java.util.Scanner;

/**
 * Strategy to determine move by prompting the user for input.
 */
public class UserInputStrategy implements ReversiStrategy {
    Scanner scanner = new Scanner(System.in); 
    String str;
    
    /**
     * Ask the user to enter a target field. Validate input and re-prompt until valid.
     */
    @Override
    public int[] getTarget(Board board, char tokenColor) {
        boolean valid = false;
        int[] target = new int[2];
        System.out.println(tokenColor + " is next in line.");
        System.out.println();
        while(!valid){ 
            System.out.print("Input the coordinates: ");
            str = scanner.nextLine();
            if(str == " " || str == "" || str == null){ 
                target = null; 
                valid = true;
                break;
            }else if(str.length() != 2){ 
                System.out.println("Invalid input");
            }else {
                target = Board.convertCoordinatesToInt(str); 
                if(board.isValidMove(target, tokenColor)) valid = true; 
                else System.out.println("Invalid input");
            }
        }
        
        return target;
    }
}
