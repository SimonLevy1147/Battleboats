import java.util.Scanner;

public class Game {
    public static void main(String args[]) { //prompts player for size of game board (height and width) and then initializes
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter board height from 3 to 10");
        int height = s.nextInt();
        System.out.println("Please enter board width from 3 to 10");
        int width = s.nextInt();
        while (!((3<=width) && (width<=10) && (3<=height) && (height<=10))) {
            System.out.println("Minimum board size is 3x3 and maximum is 10x10. PLease input correct sizes.");
            height = s.nextInt();
            width = s.nextInt();
        }
        Board testBoard = new Board(height,width);//starts game
        testBoard.gameLoop();//see board.java
        System.out.println("It took you " +testBoard.turnCount + " turns."); //after winning the game, displays number of turns the player took to sink all battleboats (including penalty turns)
    }
}
