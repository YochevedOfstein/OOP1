import java.util.Comparator;

public class killComp implements Comparator<Pawn> {
    private ConcretePlayer winner;
    public killComp(ConcretePlayer winner){
        this.winner = winner;
    }
    @Override
    public int compare(Pawn a, Pawn b) {

        if(a.getKills() > b.getKills()){
            return -1;
        }
        else if(b.getKills() > a.getKills()){
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
                return -1;
            }
                else if((b.getOwner() == winner) && (a.getOwner() != winner)){
                return 1;
                }
            }
        }
        return 0;
    }
}
