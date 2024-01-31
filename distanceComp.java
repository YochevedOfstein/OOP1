import java.util.Comparator;

public class distanceComp implements Comparator<ConcretePiece> {

    private ConcretePlayer winner;

    public distanceComp(ConcretePlayer winner){
        this.winner = winner;
    }

    @Override
    public int compare(ConcretePiece a, ConcretePiece b) {

        if(a.getDistance() > b.getDistance()){
            return -1;
        }
        else if (a.getDistance() < b.getDistance()) {
            return 1;
        }
        else{
            if(a.getID() > b.getID()){
                return 1;
            }
            else if(a.getID() < b.getID()){
                return -1;
            }
            else{
                if((a.getOwner() == winner) && (b.getOwner() != winner)){
                    return 1;
                }
                else if((a.getOwner() != winner) && (b.getOwner() == winner)){
                    return -1;
                }
            }
        }
        return 0;
    }
}
