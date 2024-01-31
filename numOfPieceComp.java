import java.util.Comparator;

public class numOfPieceComp implements Comparator<Position> {
    public numOfPieceComp(){
    }

    @Override
    public int compare(Position po1, Position po2) {
        if(po1.getnumofPieces() > po2.getnumofPieces()){
            return -1;
        }
        else if(po1.getnumofPieces() < po2.getnumofPieces()){
            return 1;
        }
        else{
            if(po1.getX() > po2.getX()){
                return 1;
            }
            else if(po1.getX() < po2.getX()){
                return -1;
            }
            else{
                if(po1.getY() > po2.getY()){
                    return 1;
                }
                else if(po1.getY() < po2.getY()){
                    return -1;
                }
            }
        }
        return 0;
    }
}
