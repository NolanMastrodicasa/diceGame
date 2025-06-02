/**
 * a game where player and computer roll one dice each, higher score wins.
 */
public class StandardDiceGame extends DiceGame {

    /**
     * constructor to create standard dice game with specified starting balance.
     *
     * @param startingMoney int the players money before playing
     */
    public StandardDiceGame(int startingMoney) {
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
     * plays one round of standard dice game, highest roll wins.
     *
     * @param betAmount int the amount up for risk this current round.
     */
    @Override
    public void playRound(int betAmount) {
        int playerRoll = (int)(Math.random() * 6) + 1;
        int computerRoll = (int)(Math.random() * 6) + 1;
        String result = "";
        int priorBalance = getPlayerMoney();

        System.out.println("Computer rolls a " + computerRoll);
        sleep(1500);
        System.out.println("You roll...");
        sleep(1500);
        System.out.println(playerRoll);
        sleep(1500);

        //decide winner
        if (playerRoll > computerRoll) {
            adjustPlayerMoney(betAmount);
            System.out.println("Player wins $" + betAmount * 2 + "! New balance: $" + getPlayerMoney());
            result = "win";
        } else if (playerRoll < computerRoll) {
            adjustPlayerMoney(-betAmount);
            System.out.println("Computer wins. " + "New balance: " + getPlayerMoney());
            result = "loss";
        } else {
            System.out.println("It's a tie! Re-rolling...");
            sleep(1500);
            playRound(betAmount);
            return;
        }

        //logs result to the text file.
        log("[Standard Dice Game], " +
                " Result: " + result +
                " Bet: $" + betAmount + "," +
                " Prior Balance: $" + priorBalance + "," +
                " Updated Balance: $" + getPlayerMoney());
    }
}
