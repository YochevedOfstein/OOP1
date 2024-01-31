public class Position {

    private int x;
    private int y;
    private int[] pieces;


    public Position(int x,int y){
        this.x = x;
        this.y = y;
        this.pieces = new int[37];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getnumofPieces(){
        int amountofpieces = 0;
        for(int i = 0; i < 37; i++){
            if(this.pieces[i] > 0){
              amountofpieces++;
            }
        }
        return amountofpieces;
    }
    public void addpiece(ConcretePiece p){
        if(p.getOwner().isPlayerOne()){
            this.pieces[(p.getID())-1]++;
        }
        else{
            this.pieces[(p.getID())+12]++;
        }
    }
    public void removepiece(ConcretePiece p){
        if(p.getOwner().isPlayerOne()){
            this.pieces[(p.getID())-1]--;
        }
        else{
            this.pieces[(p.getID())+12]--;
        }
    }
    public void printNumOfPieces(){
        String str = "(" + this.x + ", " + this.y + ")" + this.getnumofPieces() + " pieces";
        System.out.println(str);
    }
}

