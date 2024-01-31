import java.util.Comparator;

public class movesComp implements Comparator<ConcretePiece> {

    private final ConcretePlayer winner;

    public movesComp(ConcretePlayer winner){
        this.winner = winner;
    }
    @Override
    public int compare(ConcretePiece a, ConcretePiece b) {
        if((a.getOwner() == winner) && (b.getOwner() != winner)){
            return -1;
        }
        else if ((b.getOwner() == winner) && (a.getOwner() != winner)){
            return 1;
        }
        else{
            if(a.getNumOfMoves() > b.getNumOfMoves()){
                return 1;
            }
            else if(b.getNumOfMoves() > a.getNumOfMoves()){
                return -1;
            }
            else{
                if(a.getID() > b.getID()){
                    return 1;
                }
                else if(a.getID() < b.getID()){
                    return -1;
                }
            }
            return 0;
        }
    }
}
