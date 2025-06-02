/**
 * base class for all dicegames, initializes playerMoney, creates getter and setter methods.
 */
public abstract class DiceGame extends GameLogger {
    private int playerMoney;

    /**
     * constructor to create a new dicegame with players money
     *
     * @param startingMoney the amount of money a player starts with.
     */
    public DiceGame(int startingMoney) {
        this.playerMoney = startingMoney;
    }

    /**
     * returns players current balance
     *
     * @return int the players money
     */
    public int getPlayerMoney() {
        return playerMoney;
    }

    /**
     * adjusts the players money by specific amount.
     * @param money int the amount to adjust by
     */
    public void adjustPlayerMoney(int money) {
        playerMoney += money;
    }

    /**
     * method to play a round of specified dicegame
     * implemented by both StandardDiceGame and MultiDiceGame
     *
     * @param betAmount int the amount being bet in the current round.
     */
    public abstract void playRound(int betAmount);
}
