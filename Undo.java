public class Undo {

    private Position current;
    private Position previous;
    private ConcretePiece[] kills;

    public Undo(Position current, Position previous, ConcretePiece[] kills){
       this.current = current;
       this.previous = previous;
       this.kills = kills;
    }

    public Position getCurrent(){
        return this.current;
    }
    public Position getPrevious(){
        return this.previous;
    }

    public ConcretePiece[] getKills(){
        return this.kills;
    }
}
