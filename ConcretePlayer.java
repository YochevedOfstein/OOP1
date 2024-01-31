public class ConcretePlayer implements Player{

    private boolean isPlayer1;

    private int wins;
    public ConcretePlayer(boolean isPlayer1){
        this.wins= 0;
        this.isPlayer1 = isPlayer1;
    }
   public boolean isPlayerOne(){
       return isPlayer1;

    }
   public int getWins(){
       return wins;
    }

    public void addWin(){
        wins++;
    }

}
