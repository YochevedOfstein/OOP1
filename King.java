import java.util.LinkedList;

public class King extends ConcretePiece{


    public King(ConcretePlayer owner, int ID, Position pos) {
        super(owner, ID, pos);

    }
    public String getType(){
        return "♔";
    }

    public void printMoves(){
       String str = "K7: [" ;
       for(int i = 0; i < this.moves.size()-1; i++){
           Position pos = this.moves.get(i);
           str = str + "(" + pos.getX() + ", " + pos.getY() + "), ";
        }
       Position pos = this.moves.getLast();
        str = str + "(" + pos.getX() + ", " + pos.getY() + ")]";
        System.out.println(str);
    }

    public void printDistance(){
        String str = "K7: "+ this.getDistance() + " squares" ;
        System.out.println(str);
    }
}


