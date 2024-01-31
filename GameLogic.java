import org.junit.platform.engine.support.descriptor.FileSystemSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class GameLogic implements PlayableLogic {
    private ConcretePlayer Player1;
    private ConcretePlayer Player2;
    private boolean isSecondPlayerTurn;
    private Position kingPos;
    private ArrayList<ConcretePiece> pieces;
    private ArrayList<Pawn> pawns;
    private Position[][] Positions;
    private ConcretePlayer winner;
    private Stack<Undo> undo;
    ConcretePiece[][] Board = new ConcretePiece[11][11];
    public GameLogic() {
        this.pawns = new ArrayList<Pawn>();
        this.pieces = new ArrayList<ConcretePiece>();
        this.Positions = new Position[11][11];
        this.Player1 = new ConcretePlayer(true);
        this.Player2 = new ConcretePlayer(false);
        this.kingPos = new Position(5,5);
        this.winner = null;
        this.undo = new Stack<Undo>();
        StartingBoard();
        isSecondPlayerTurn = true;
    }
    public void StartingBoard() {
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++) {
                this.Positions[i][j]= new Position(i,j);
            }
        }
        Board[3][0] = new Pawn(Player2,1, this.Positions[3][0]);
        Board[4][0] = new Pawn(Player2,2, this.Positions[4][0]);
        Board[5][0] = new Pawn(Player2,3, this.Positions[5][0]);
        Board[6][0] = new Pawn(Player2,4, this.Positions[6][0]);
        Board[7][0] = new Pawn(Player2,5,this.Positions[7][0]);
        Board[0][3] = new Pawn(Player2,7, this.Positions[0][3]);
        Board[10][3] = new Pawn(Player2,8, this.Positions[10][3]);
        Board[0][4] = new Pawn(Player2,9, this.Positions[0][4]);
        Board[10][4] = new Pawn(Player2,10, this.Positions[10][4]);
        Board[0][5] = new Pawn(Player2,11, this.Positions[0][5]);
        Board[1][5] = new Pawn(Player2,12, this.Positions[1][5]);
        Board[9][5] = new Pawn(Player2,13,this.Positions[9][5]);
        Board[10][5] = new Pawn(Player2,14, this.Positions[10][5]);
        Board[0][6] = new Pawn(Player2,15, this.Positions[0][6]);
        Board[10][6] = new Pawn(Player2,16, this.Positions[10][6]);
        Board[0][7] = new Pawn(Player2,17, this.Positions[0][7]);
        Board[10][7] = new Pawn(Player2,18, this.Positions[10][7]);
        Board[3][10] = new Pawn(Player2,20, this.Positions[3][10]);
        Board[4][10] = new Pawn(Player2,21, this.Positions[4][10]);
        Board[5][10] = new Pawn(Player2,22, this.Positions[5][10]);
        Board[6][10] = new Pawn(Player2,23, this.Positions[6][10]);
        Board[7][10] = new Pawn(Player2,24, this.Positions[7][10]);
        Board[5][1] = new Pawn(Player2,6, this.Positions[5][1]);
        Board[5][9] = new Pawn(Player2,19, this.Positions[5][9]);

        // setting the white team //
        Board[4][4] = new Pawn(Player1,2, this.Positions[4][4]);
        Board[5][4] = new Pawn(Player1,3, this.Positions[5][4]);
        Board[6][4] = new Pawn(Player1,4, this.Positions[6][4]);
        Board[3][5] = new Pawn(Player1,5, this.Positions[3][5]);
        Board[4][5] = new Pawn(Player1,6, this.Positions[4][5]);
        Board[6][5] = new Pawn(Player1,8, this.Positions[6][5]);
        Board[7][5] = new Pawn(Player1,9, this.Positions[7][5]);
        Board[5][3] = new Pawn(Player1,1, this.Positions[5][3]);
        Board[4][6] = new Pawn(Player1,10, this.Positions[4][6]);
        Board[5][6] = new Pawn(Player1,11, this.Positions[5][6]);
        Board[6][6] = new Pawn(Player1,12, this.Positions[6][6]);
        Board[5][7] = new Pawn(Player1,13, this.Positions[5][7]);

        Board[5][5] = new King(Player1,7, this.Positions[5][5]);

        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(Board[i][j] != null) {
                    if (Board[i][j] instanceof Pawn) {
                        pieces.add(Board[i][j]);
                        pawns.add((Pawn) Board[i][j]);
                    }
                    else{
                        pieces.add(Board[i][j]);
                    }
                    Positions[i][j].addpiece(Board[i][j]);
                }
            }
        }

        isSecondPlayerTurn = true;
    }
    public boolean move(Position a, Position b) {
        int xb = b.getX();
        int yb = b.getY();

        int xa = a.getX();
        int ya = a.getY();

        int MaxX = Math.max(xa, xb);
        int MaxY = Math.max(ya, yb);
        int MinX = Math.min(xa, xb);
        int MinY = Math.min(ya, yb);

        if (a == b) {
            return false;
        }
        if (getPieceAtPosition(b) != null){
            return false;
        }
        if (Board[xa][ya] instanceof Pawn && xb == 0 && yb == 0) {
            return false;
        }
        if (Board[xa][ya] instanceof Pawn && xb == 0 && yb == 10) {
            return false;
        }
        if (Board[xa][ya] instanceof Pawn && xb == 10 && yb == 0) {
            return false;
        }
        if (Board[xa][ya] instanceof Pawn && xb == 10 && yb == 10) {
            return false;
        }
        if (xb != xa && ya != yb) {
            return false;
        }

        if (xa == xb) {
            for (int i = MinY + 1; i < MaxY; i++) {
                if (Board[xa][i] != null) {
                    return false;
                }
            }
        }
        else {
            for (int i = MinX + 1; i < MaxX; i++) {
                if (Board[i][ya] != null) {
                    return false;
                }
            }
        }

        if(isSecondPlayerTurn){
            if(getPieceAtPosition(a).getOwner() != Player2)  {
                return false;
            }
            else{
                isSecondPlayerTurn = false;
            }
        }
        else{
            if(getPieceAtPosition(a).getOwner() != Player1)  {
                return false;
            }
            else{
                isSecondPlayerTurn = true;
            }
        }

        Board[xb][yb] = Board[xa][ya];
        Board[xa][ya] = null;

        //add to the position the piece added to it.
        Positions[xb][yb].addpiece(Board[xb][yb]);

        //add distance count in every move:
        //if the x has changed-
        Board[xb][yb].addDistance(Math.abs(xb-xa));
        //if the y has changed-
        Board[xb][yb].addDistance(Math.abs(yb-ya));


        if(Board[xb][yb] instanceof King){
            kingPos = b;
            Board[xb][yb].addMove(b);
        }
        else{
            Board[xb][yb].addMove(b);
        }

        ConcretePiece[] Killed = new ConcretePiece[4];

        if(checkLeft(xb,yb) || checkUp(xb,yb) || checkRight(xb,yb) || checkDown(xb,yb)){
           Killed = Kill(xb,yb);
        }

        Undo U = new Undo(b,a,Killed);
        undo.push(U);

        if(isGameFinished()){
            statistics(this.winner);
        }
        return true;
    }
    public ConcretePiece[] Kill(int x, int y) {
        ConcretePiece[] Killed = new ConcretePiece[4];
        if (Board[x][y] instanceof Pawn) {
            if (checkUp(x, y)) {
                if (y == 1) {
                    Killed[0] = Board[x][y - 1];
                    Board[x][y - 1] = null;
                    ((Pawn) Board[x][y]).addKill();
                } else {
                    if ((checkUp(x, y - 1)) || ((x == 0) && (y == 2)) || ((x == 10) && (y == 2))){
                        Killed[0] = Board[x][y - 1];
                        Board[x][y - 1] = null;
                        ((Pawn) Board[x][y]).addKill();
                    }
                }
            }
            if (checkDown(x, y)) {
                if (y == 9) {
                    Killed[2] = Board[x][y + 1];
                    Board[x][y + 1] = null;
                    ((Pawn) Board[x][y]).addKill();
                }
                else {
                    if ((checkDown(x, y + 1)) || ((x == 0) && (y == 8)) || ((x == 10) && (y == 8))) {
                        Killed[2] = Board[x][y + 1];
                        Board[x][y + 1] = null;
                        ((Pawn) Board[x][y]).addKill();
                    }
                }
            }
            if (checkRight(x, y)) {
                if (x == 1) {
                    Killed[1] = Board[x - 1][y];
                    Board[x - 1][y] = null;
                    ((Pawn) Board[x][y]).addKill();
                }
                else {
                    if ((checkRight(x - 1, y)) || ((x == 2) && (y == 0)) || ((x == 2) && (y == 10))){
                        Killed[1] = Board[x - 1][y];
                        Board[x - 1][y] = null;
                        ((Pawn) Board[x][y]).addKill();
                    }
                }
            }
            if (checkLeft(x, y)) {
                if (x == 9) {
                    Killed[3] = Board[x + 1][y];
                    Board[x + 1][y] = null;
                    ((Pawn) Board[x][y]).addKill();
                }
                else {
                    if ((checkLeft(x + 1, y)) || ((x == 8) && (y == 0)) || ((x == 8) && (y == 10))){
                        Killed[3] = Board[x + 1][y];
                        Board[x + 1][y] = null;
                        ((Pawn) Board[x][y]).addKill();
                    }
                }
            }
        }
        return Killed;
    }
    public boolean kingSurrounded(){
        int y = kingPos.getY();
        int x = kingPos.getX();
        if ((y != 10) && (y != 0) && (x != 0) && (x != 10)){
            if ((Board[x][y-1] != null)&&(Board[x][y + 1] != null)&&(Board[x-1][y] != null)&&(Board[x+1][y] != null)){
                if ((Board[x][y-1].getOwner() == Board[x][y+1].getOwner())&&(Board[x-1][y].getOwner() == Board[x+1][y].getOwner())){
                    if((Board[x][y-1].getOwner() == Board[x-1][y].getOwner()) && (Board[x][y-1].getOwner() != Board[x][y].getOwner())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkUp(int x, int y){
        if(Board[x][y] instanceof Pawn){
            if (y != 0) {
                if ((Board[x][y - 1] != null)&&(Board[x][y-1] instanceof Pawn)){
                    if (Board[x][y - 1].getOwner() != Board[x][y].getOwner()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkDown(int x, int y){
        if(Board[x][y] instanceof Pawn){
            if (y != 10) {
                    if ((Board[x][y + 1] != null) && (Board[x][y + 1] instanceof Pawn)) {
                        if (Board[x][y + 1].getOwner() != Board[x][y].getOwner()) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }
    public boolean checkRight(int x, int y){
        if(Board[x][y] instanceof Pawn){
            if (x != 0) {
                if ((Board[x - 1][y] != null)&&(Board[x - 1][y] instanceof Pawn)) {
                    if (Board[x - 1][y].getOwner() != Board[x][y].getOwner()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkLeft(int x, int y){
        if(Board[x][y] instanceof Pawn){
            if (x != 10) {
                if ((Board[x + 1][y] != null)&&(Board[x + 1][y] instanceof Pawn)) {
                    if (Board[x + 1][y].getOwner() != Board[x][y].getOwner()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Piece getPieceAtPosition(Position position){
      int x = position.getX();
      int y = position.getY();

       return Board[x][y];
    }
   public Player getFirstPlayer(){
      return Player1;
    }
    public Player getSecondPlayer(){
      return Player2;
    }
    public boolean isGameFinished() {
        if (Board[0][0] instanceof King) {
            this.winner = Player1;
            Player1.addWin();
            return true;
        }
        if (Board[10][0] instanceof King) {
            this.winner = Player1;
            Player1.addWin();
            return true;
        }
        if (Board[0][10] instanceof King) {
            this.winner = Player1;
            Player1.addWin();
            return true;
        }
        if (Board[10][10] instanceof King) {
            this.winner = Player1;
            Player1.addWin();
            return true;
        }
        if (kingSurrounded()){
            this.winner = Player2;
            Player2.addWin();
            return true;
        }
        return false;
    }
    public void statistics(ConcretePlayer winner){
        Comparator<ConcretePiece> movecomp = new movesComp(winner);
        pieces.sort(movecomp);
        for(ConcretePiece p: pieces){
            if(p.getNumOfMoves() > 1){
                p.printMoves();
            }
        }
        for(int i = 0; i < 75; i++){
            System.out.print("*");
        }
        System.out.println();

        Comparator<Pawn> killcomp = new killComp(winner);
        pawns.sort(killcomp);
        for(Pawn pa: pawns) {
            if (pa.getKills() > 0) {
                pa.printKills();
            }
        }
        for(int i = 0; i < 75; i++){
            System.out.print("*");
        }
        System.out.println();

        Comparator<ConcretePiece> distcomp = new distanceComp(winner);
        pieces.sort(distcomp);
        for(ConcretePiece d: pieces){
            if(d.getDistance() > 0){
                d.printDistance();
            }
        }
        for(int i = 0; i < 75; i++){
            System.out.print("*");
        }
        System.out.println();

        ArrayList<Position> pos =new ArrayList<Position>();
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                pos.add(this.Positions[i][j]);
            }
        }
        Comparator<Position> numofpcomp = new numOfPieceComp();
        pos.sort(numofpcomp);
        for(Position p: pos){
            if(p.getnumofPieces() > 1){
                p.printNumOfPieces();
            }
        }
        for(int i = 0; i < 75; i++){
            System.out.print("*");
        }
        System.out.println();
    }
    public boolean isSecondPlayerTurn(){
      return isSecondPlayerTurn;
    }

   public void reset(){
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                Board[i][j] = null;
            }
        }
        undo.clear();
        this.winner = null;
        pieces.clear();
        pawns.clear();
        StartingBoard();
   }
    public void undoLastMove(){
        if(!undo.isEmpty()){
            Undo top = undo.pop();
            ConcretePiece[] kills = top.getKills();
            Position cur = top.getCurrent();
            Position pre = top.getPrevious();
            ConcretePiece temp = Board[cur.getX()][cur.getY()];
            Board[pre.getX()][pre.getY()] = temp;
            Board[cur.getX()][cur.getY()] = null;
            int count = 0;
            if(kills[0] != null){
                Board[cur.getX()][cur.getY()-1] = kills[0];
                count++;
                }
            if(kills[1] != null){
                Board[cur.getX()-1][cur.getY()] = kills[1];
                count++;
            }
            if (kills[2] != null){
                Board[cur.getX()][cur.getY()+1] = kills[2];
                count++;
            }
            if(kills[3] != null){
                Board[cur.getX()+1][cur.getY()] = kills[3];
                count++;
            }
            Board[pre.getX()][pre.getY()].moves.removeLast();
            Board[pre.getX()][pre.getY()].removedist(Math.abs(pre.getX()- cur.getX()));
            Board[pre.getX()][pre.getY()].removedist(Math.abs(pre.getY()- cur.getY()));
            ((Pawn) Board[pre.getX()][pre.getY()]).removekills(count);
            Positions[cur.getX()][cur.getY()].removepiece(Board[pre.getX()][pre.getY()]);
            isSecondPlayerTurn=!isSecondPlayerTurn;
        }
    }
    public int getBoardSize(){
      return 11;
    }
}
