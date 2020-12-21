import java.security.SecureRandom;
import java.util.Scanner;

public class Game {
    private final Scanner in;
    private final SecureRandom random;
    String[] options;

    public Game(String[] args) {
        this.in  = new Scanner(System.in);
        this.random = new SecureRandom();
        this.options = args;
        while(options.length%2 == 0 || options.length < 3) {
            System.out.println("Incorrect arguments. Amount of arguments must be an odd number more >= 3.");
            System.out.println("Example: scissors paper rock lizard Spock");
            System.out.print("Type again, please: ");
            String input = in.nextLine();
            options = input.trim().split(" ");
        }
    }

    public int makeMove(){
        return random.nextInt(options.length);
    }

    public int getPlayerMove() {
        int move = -2;
        while(move < -1 || move > options.length - 1) {
            String input = in.nextLine();
            try {
                move = Integer.parseInt(input) - 1;
                if (move < -1 || move > options.length - 1) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Sorry, \"" + input + "\" is not valid value!");
                printMoves();
            }
        }
        return move;
    }

    public void printMoves() {
        System.out.println("Available moves:");
        for(var i = 0; i < options.length; i++) {
            System.out.println((i+1) + " - " + options[i]);
        }
        System.out.println("0 - exit");
        System.out.print("Enter your move: ");
    }

    public void play(int computer, int player) {
        System.out.println("Your move: " + options[player]);
        System.out.println("Computer move: " + options[computer]);
        int counter = 0;
        while ((player + counter)%options.length != computer) {
            counter ++;
        }
        if(counter == 0 || counter == options.length) {
            System.out.println("Tie!");
        }  else if(counter < options.length/2) {
            System.out.println("You loose");
        } else {
            System.out.println("You win!");
        }
    }
}
