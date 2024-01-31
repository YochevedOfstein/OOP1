import java.util.LinkedList;

public class Pawn extends ConcretePiece{

private int Kills = 0;

private String str;

public Pawn(ConcretePlayer owner, int ID, Position pos) {

    super(owner, ID, pos);
}

public String getType() {
    if (getOwner().isPlayerOne()) {
        return "♙";
    } else return "♟";

}
    public void addKill(){
       this.Kills++;
    }

    public int getKills(){
        return this.Kills;
    }

    public void removekills(int k){
    this.Kills = this.Kills - k;
    }

    public void printMoves(){
        String str = "" ;
        if(this.owner.isPlayerOne()){
            str = str + "D";
        }
        else{
            str = str + "A";
        }
        str= str + this.ID+": [";
        for(int i = 0; i < this.moves.size()-1; i++){
            Position pos = this.moves.get(i);
            str = str + "(" + pos.getX() + ", " + pos.getY() + "), ";
        }
        Position pos = this.moves.getLast();
        str = str + "(" + pos.getX() + ", " + pos.getY() + ")]";
        System.out.println(str);
    }

    public void printKills(){
        String str = "" ;
        if(this.owner.isPlayerOne()){
            str = str + "D";
        }
        else{
            str = str + "A";
        }
        str= str + this.ID+": " + this.getKills() + " kills";
        System.out.println(str);
    }

    public void printDistance(){
        String str = "" ;
        if(this.owner.isPlayerOne()){
            str = str + "D";
        }
        else{
            str = str + "A";
        }
        str= str + this.ID+": " + this.getDistance() + " squares";
        System.out.println(str);
    }

}



