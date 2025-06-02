import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends GameLogger {

    /**
     * main method, initializes DiceGame object, and displays options for user to play.
     *
     * @param args command line, N/A
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DiceGame game = new StandardDiceGame(100);
        LocalDateTime now = LocalDateTime.now();
        log("New Session! Starting Balance: $" + game.getPlayerMoney() + " " + now);

        boolean running = true;

        while (running && game.getPlayerMoney() > 0) {
            System.out.println("\n=== Dice Game Menu ===");
            System.out.println("Balance: $" + game.getPlayerMoney());
            System.out.println("1) Play Standard Dice Game");
            System.out.println("2) Play Multi Dice Game");
            System.out.println("h) Game History");
            System.out.println("q) Quit");
            System.out.print("Select an option: \n");
            String choice = input.next();

            //quit option
            if (choice.equalsIgnoreCase("q")) {
                running = false;
                System.out.println("Goodbye! Final balance: $" + game.getPlayerMoney());
                log("Session Ended! Final Balance: $" + game.getPlayerMoney());
            }
            //history option
            else if (choice.equalsIgnoreCase("h")) {
                for (String s : history) {
                    System.out.println(s);
                }
            }
            //standard dice game
            else if (choice.equals("1")) {
                int bet;
                while (true) {
                    System.out.print("Enter your bet amount: ");
                    if (input.hasNextInt()) {
                        bet = input.nextInt();
                        if (bet > 0 && bet <= game.getPlayerMoney()) {
                            break;
                        } else {
                            System.out.println("Invalid bet amount.");
                        }
                    } else {
                        System.out.println("Please enter a valid number.");
                        input.next();
                    }
                }
                game.playRound(bet);
            }
            //multi dice option
            else if (choice.equals("2")) {
                DiceGame multiGame = new MultiDiceGame(game.getPlayerMoney());
                int bet;
                while (true) {
                    System.out.print("Enter your bet amount: ");
                    if (input.hasNextInt()) {
                        bet = input.nextInt();
                        if (bet > 0 && bet <= multiGame.getPlayerMoney()) {
                            break;
                        } else {
                            System.out.println("Invalid bet amount.");
                        }
                    } else {
                        System.out.println("Please enter a valid number.");
                        input.next();
                    }
                }
                multiGame.playRound(bet);
                //update game reference
                game = multiGame;
            }
            else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        //player out of money
        if (game.getPlayerMoney() <= 0) {
            System.out.println("You are out of money. Game over.");
            log("Session Ended! Final Balance: $" + game.getPlayerMoney());
        }

        input.close();
    }
}
