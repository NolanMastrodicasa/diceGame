public abstract class DiceGame {
    private final int playerMoney;

    public DiceGame(int startingMoney){
        this.playerMoney = startingMoney;
    }

    public int getPlayerMoney(){
        return playerMoney;
    }

    public abstract void playRound(int betAmount);

}
