package reversi;

import java.util.Arrays;

public class Board {

    public static final char EMPTY = '.';
    public static final char RED = 'O';
    public static final char YELLOW = 'X';


    public static final int BOARD_SIZE = 8;
    public final char[][] fields = new char[BOARD_SIZE][BOARD_SIZE];
    private boolean[] ccr = new boolean[8];

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                fields[i][j] = EMPTY;
            }
        }

        fields[3][3] = RED;
        fields[4][4] = RED;
        fields[3][4] = YELLOW;
        fields[4][3] = YELLOW;
    }

    /**
     * Prints the board to System.out
     */
    public void print() {
        System.out.println();
        /* print column headers A - H */
        System.out.print("# ");
        for (int x = 0; x < fields[0].length; x++) {
            char column = (char) (x + 65);
            System.out.print(" " + column);
        }
        System.out.println();

        for (int y = 0; y < fields.length; y++) {
            /* print row number */
            int rowNumber = y + 1;
            System.out.print(rowNumber + " ");
            if (rowNumber < 10) System.out.print(" ");

            /* print row */
            for (int x = 0; x < fields[y].length; x++) {
                char output = fields[x][y];
                System.out.print(output + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Place a token in the given color on the given target field.
     * Flip adjacent opponent's tokens according to the rules.
     *
     * @throws RuntimeException if the move is invalid
     */
    public void placeToken(int[] target, char tokenColor) {
        int x = target[0];
        int y = target[1];

        if (fields[x][y] != EMPTY) {
            throw new RuntimeException("Field occupied: " + Arrays.toString(target));
        }
        if (!isValidMove(target, tokenColor)) {
            throw new RuntimeException("Invalid move: " + Arrays.toString(target));
        }

        
        // place token
        // flip tokens vertical if applicable
        // flip tokens horizontal if applicable
        // flip tokens diagonal if applicable
        fields[x][y] = tokenColor;
        //down flip
        if(ccr[0]){
            for(int i = 1; fields[x+i][y] == opponent(tokenColor); i++) fields[x+i][y] = tokenColor;
        }
        //up flip
        if(ccr[1]){
            for(int i = 1; fields[x-i][y] == opponent(tokenColor); i++) fields[x-i][y] = tokenColor;
        }
        //right flip
        if(ccr[2]){
            for(int i = 1; fields[x][y+i] == opponent(tokenColor); i++) fields[x][y+i] = tokenColor;
        }
        //left flip
        if(ccr[3]){
            for(int i = 1; fields[x][y-i] == opponent(tokenColor); i++) fields[x][y-i] = tokenColor;
        }
        //down-right
        if(ccr[4]){
            for(int i = 1; fields[x+i][y+i] == opponent(tokenColor); i++) fields[x+i][y+i] = tokenColor;
        }
        //down-left
        if(ccr[5]){
            for(int i = 1; fields[x+i][y-i] == opponent(tokenColor); i++) fields[x+i][y-i] = tokenColor;
        }
        //up-right
        if(ccr[6]){
            for(int i = 1; fields[x-i][y+i] == opponent(tokenColor); i++) fields[x-i][y+i] = tokenColor;
        }
        //up-left
        if(ccr[7]){
            for(int i = 1; fields[x-i][y-i] == opponent(tokenColor); i++) fields[x-i][y-i] = tokenColor;
        }
    }

    /**
     * Get the color of the opponent's token
     *
     * @param tokenColor the players token color
     * @return YELLOW, if opponent is RED. RED otherwise.
     */
    private char opponent(char tokenColor) {
        return tokenColor == RED ? YELLOW : RED;
    }

    /**
     * Validate that the given target is a valid move for the given color
     *
     * @return TRUE if the target is unoccupied, within the bounds of the field and enabled flipping of opponent tokens
     */
    public boolean isValidMove(int[] target, char tokenColor) {
        int x = target[0];
        int y = target[1];

        for(int i = 0; i < ccr.length; i++) ccr[i] = false; 
        if((x >= 0 && x <= 7) && (y >= 0 && y <= 7)){ 
            
        }else{
            return false;
        }

        if(fields[x][y] != EMPTY) return false; 

        if(x+1 <= 7){
            for (int i = 1; fields[x+i][y] == opponent(tokenColor) && x+i+1 <= 7; i++){ 
                if(fields[x+i+1][y] == tokenColor){
                    ccr[0] = true;//down
                }
            }
        }
        if(x-1 >= 0){
            for (int i = 1; fields[x-i][y] == opponent(tokenColor) && x-i-1 >= 0; i++){
                if(fields[x-i-1][y] == tokenColor){
                    ccr[1] = true;//up
                }
            }
        }
        if(y+1 <= 7){
            for (int i = 1; fields[x][y+i] == opponent(tokenColor) && y+i+1 <= 7; i++){
                if(fields[x][y+i+1] == tokenColor){
                    ccr[2] = true;//right
                }
            }
        }
        if(y-1 >= 0){
            for (int i = 1; fields[x][y-i] == opponent(tokenColor) && y-i-1 >= 0; i++){
                if(fields[x][y-i-1] == tokenColor){
                    ccr[3] = true;//left
                }
            
            }
        }
        if(x+1 <= 7 && y+1 <= 7){
            for (int i = 1; fields[x+i][y+i] == opponent(tokenColor) && (x+i+1 <= 7 && y+i+1 <= 7); i++){
                if(fields[x+i+1][y+i+1] == tokenColor){
                    ccr[4] = true;//down-right
                }
            }
        }
        if(x+1 <= 7 && y-1 >= 0){
            for (int i = 1; fields[x+i][y-i] == opponent(tokenColor) && (x+i+1 <= 7 && y-1-i >= 0); i++){
                if(fields[x+i+1][y-i-1] == tokenColor){
                    ccr[5] = true;//down-left
                }
            }
        }
        if(x-1 >= 0 && y+1 <= 7){
            for (int i = 1; fields[x-i][y+i] == opponent(tokenColor) && (x-i-1 >= 0 && y+i+1 <= 7); i++){
                if(fields[x-i-1][y+i+1] == tokenColor){
                    ccr[6] = true;//up-right
                }
            }
        }
        if(x-1 >= 0 && y-1 >= 0){
            for (int i = 1; fields[x-i][y-i] == opponent(tokenColor) && (x-i-1 >= 0 && y-i-1 >= 0); i++){
                if(fields[x-i-1][y-i-1] == tokenColor){
                    ccr[7] = true;//up-left
                }
            }
        }
        for(boolean dir : ccr){ 
            if(dir) return true;
        }
        return false;
    }

    /**
     * Get all valid moves for the player with the given color
     * @return an array of moves. each move is an int[] of length 2 with x and y value. the array of moves may have trailing empty fields.
     */
    public int[][] getValidMoves(char tokenColor) {
        int[][] validMoves = new int[64][];
        int counter = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int[] field = {i, j};
                if (isValidMove(field, tokenColor)) {
                    validMoves[counter] = field;
                    counter++;
                }
            }
        }

        return validMoves;
    }


    /**
     * Converts alphanumeric board coordinates to array indexes, e.g. A1 to [0,0]
     */
    public static int[] convertCoordinatesToInt(String input) {
        int x = input.toUpperCase().charAt(0) - 65;
        int y = Integer.parseInt(input.substring(1)) - 1;
        return new int[]{x, y};
    }

    /**
     * Converts array indexes to ahlphanumeric board coordinates, e.g. [0,0] to A1
     */
    public static String convertCoordinatesToString(int[] input) {
        char x = (char) (input[0] + 65);
        String y = Integer.toString(input[1] + 1);
        return x + y;
    }

    public boolean hasValidMoves(char tokenColor) {
        int[][] moves = getValidMoves(tokenColor); 
        if (moves[0] != null){ 
            return true;
        }
        return false;
    }
}
