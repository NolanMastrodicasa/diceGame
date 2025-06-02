import java.util.Scanner;

/**
 * game where player and computer roll multiple dice 1-5, highest total score wins.
 */
public class MultiDiceGame extends DiceGame {

    /**
     * constructor creates a MultiDiceGame with the starting money of the player.
     *
     * @param startingMoney int the amount of money the player has before playing.
     */
    public MultiDiceGame(int startingMoney) {
        super(startingMoney);
    }

    /**
     * sleep method used to sleep for a specific amount of milliseconds.
     *
     * @param milliseconds int the amount of milliseconds to sleep for.
     */
    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * plays a single round of the dice game.
     * whoever has the highest total score of all dice added up wins 2x bet.
     *
     * @param betAmount int amount the player is risking in current round.
     */
    @Override
    public void playRound(int betAmount) {
        Scanner input = new Scanner(System.in);

        System.out.print("How many dice do you want to roll? (1-5): ");
        int numDice;
        while (true) {
            if (input.hasNextInt()) {
                numDice = input.nextInt();
                if (numDice >= 1 && numDice <= 5) {
                    break;
                } else {
                    System.out.print("Invalid number of dice. Enter 1-5: ");
                }
            } else {
                System.out.print("Invalid input. Enter a number 1-5: ");
                input.next();
            }
        }

        //players rolls
        int[] playerRolls = new int[numDice];
        int playerSum = 0;
        System.out.print("Player rolls: ");
        for (int i = 0; i < numDice; i++) {
            playerRolls[i] = (int) (Math.random() * 6) + 1;
            playerSum += playerRolls[i];
            System.out.print(playerRolls[i] + " ");
            sleep(1500);
        }
        System.out.println(" => Total: " + playerSum);
        sleep(1500);

        //computers rolls
        int[] computerRolls = new int[numDice];
        int computerSum = 0;
        System.out.print("Computer rolls: ");
        for (int i = 0; i < numDice; i++) {
            computerRolls[i] = (int) (Math.random() * 6) + 1;
            computerSum += computerRolls[i];
            System.out.print(computerRolls[i] + " ");
            sleep(1500);
        }
        System.out.println(" => Total: " + computerSum);
        sleep(1500);

        //decide winner and update balance.
        String result;
        int priorBalance = getPlayerMoney();

        if (playerSum > computerSum) {
            adjustPlayerMoney(betAmount);
            result = "Player wins! +" + betAmount;
            System.out.println(result + " New balance: $" + getPlayerMoney());
            sleep(1500);
        } else if (playerSum < computerSum) {
            adjustPlayerMoney(-betAmount);
            result = "loss";
            System.out.println(result + " New balance: $" + getPlayerMoney());
            sleep(1500);
        } else {
            result = "It's a tie! Balance unchanged.";
            System.out.println(result + " Balance: $" + getPlayerMoney());
            sleep(1500);
        }

        //log the result of the round.
        log("[Multi Dice Game], " +
                " Result: " + result +
                " Bet: $" + betAmount + "," +
                " Prior Balance: $" + priorBalance + "," +
                " Updated Balance: $" + getPlayerMoney());
    }
}
