package reversi;


import java.util.Scanner;

import reversi.strategies.MaxTokenStrategy;
import reversi.strategies.UserInputStrategy;

public class ReversiApplication {
    static boolean flag = true;
    Scanner sc = new Scanner (System.in);
    String line;
    char letter;

    private ReversiGame game;

    public static void main(String[] args) {
        ReversiApplication battleshipApplication = new ReversiApplication();
        battleshipApplication.mainMenu();
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("***** ***** ********** ***** *****");
        System.out.println("***** *** PIRATE REVERSI *** *****");
        System.out.println("***** ***** ********** ***** *****");
        System.out.println();
        System.out.println("Main Menu");
        System.out.println();
        if(hasRunningGame())
            System.out.println("Continue Game: S");
        System.out.println("Start Game vs Player: P");
        System.out.println("Start Game vs AI: C");
        System.out.println("Start Game AI vs AI: A");
        System.out.println("Close: E");
        System.out.println();
        System.out.println();
    }

    private void mainMenu() {
        while(flag){
            printMainMenu();
            System.out.print("Drücke eine Taste um fortzufahren: ");
            line = sc.nextLine();
            letter = line.charAt(0);

            if(letter == 'C' || letter == 'c') {
                startNewGameVersusComputer();
            }else if(hasRunningGame() && (letter == 'S' || letter == 's')) {
                continueGame();
            }else if(letter == 'P' || letter == 'p') { 
                startNewGameVersusPlayer();
            }else if(letter == 'A' || letter == 'a') {
                startNewGameAI();
            }else if(letter == 'E' || letter == 'e'){
                flag = false;
            }else {
                System.out.println("There is no such option! Please chose correct option.");
            }
        }
    }


    private boolean hasRunningGame() {
        return !(game == null || game.isFinished());
    }

    private void continueGame() {
        this.game.run();
    }

    private void startNewGameVersusComputer() {
        this.game = new ReversiGame(new UserInputStrategy(), new MaxTokenStrategy());
        continueGame();
    }

    private void startNewGameVersusPlayer() {
        this.game = new ReversiGame(new UserInputStrategy(), new UserInputStrategy());
        continueGame();
    }

    private void startNewGameAI() {
        this.game = new ReversiGame(new MaxTokenStrategy(), new MaxTokenStrategy());
        continueGame();
    }

}
