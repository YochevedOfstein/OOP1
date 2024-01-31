import java.util.LinkedList;

public abstract class ConcretePiece implements Piece {
 protected ConcretePlayer owner;
 protected int ID;
 private Position pos;
 private String str;
 private int distance = 0;
 protected LinkedList<Position> moves = new LinkedList<Position>();
    public ConcretePiece(ConcretePlayer owner, int ID, Position pos) {
        this.owner = owner;
        this.ID = ID;
        this.pos = pos;
        this.moves.add(pos);
    }
    public Position getPosition() {return this.pos;}
    public ConcretePlayer getOwner(){
        return this.owner;
    }
    public abstract String getType();
    public void addMove(Position move){
        this.moves.add(move);
    }

    public int getID(){
        return this.ID;
    }
    public abstract void printMoves();
    public int getNumOfMoves(){
        return moves.size();
    }

    public void addDistance(int dis) {
        this.distance = this.distance + dis;
    }

    public void removedist(int dis){
     this.distance = this.distance - dis;
    }

    public int  getDistance(){
        return this.distance;
    }

    public abstract void printDistance();
}
