package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class PlayBoard {
    ArrayList<ArrayList<PlaySquare>> allSquares;

    public PlayBoard() {
        allSquares=new ArrayList<>();
        squareInit(15);
    }

    public ArrayList<ArrayList<PlaySquare>> getAllSquares() {
        return allSquares;
    }

    //prida vonkajsiu stenu
    private void addWall(){
        ArrayList<PlaySquare> wallLine=new ArrayList<>();
        for (ArrayList<PlaySquare> sqLine : allSquares) {
            sqLine.add(0, new PlaySquare(allSquares.size(), -1,-1,allSquares));
            sqLine.add(new PlaySquare(allSquares.size(), -1,-1,allSquares));
        }
        for(int i=0;i<allSquares.size()+2;i++){
            wallLine.add(new PlaySquare(allSquares.size(), -1,-1,allSquares));
        }
        allSquares.add(wallLine);
        allSquares.add(0,wallLine);


    }
    //zisti ci sa da prejst na dane policko bez narazenia na stenu
    public boolean isReachableFromTo(int fromX,int fromY,int toX, int toY){
        if(fromX!=toX && fromY!=toY){
            return false;
        }
        else if(fromY==toY){
            if(toX<fromX){
                int temp=toX;
                toX=fromX;
                fromX=temp;
            }

            for(int i=fromX;i<=toX;i++){
                if(allSquares.get(i).get(toY).isWall()){
                    return false;
                }
            }
        }
        else {
            if(toY<fromY){
                int temp=toY;
                toY=fromY;
                fromY=temp;
            }
            for(int i=fromY;i<=toY;i++){
                if(allSquares.get(toX).get(i).isWall()){
                    return false;
                }
            }
        }
        return true;
    }
    //nastavi ako ciel policko najblizsie pravemu dolnemu rohu
    private void genFin(int size){
        for (int line=size-1;line>0;line--){
            for (int col=size-1;col>0;col--){
                if(!allSquares.get(line).get(col).isWall()){
                    allSquares.get(line).get(col).setFin();
                    return;
                }
            }
        }
    }

    //nastavi stvorceky bez vonkajsej steny a vytvori v nich cestu
    private void squareInit(int size){
        for(int i=0;i<size;i++){
            allSquares.add(new ArrayList<>());
            for(int j=0;j<size;j++){
                allSquares.get(i).add(new PlaySquare(size,i,j,allSquares));
            }
        }

        //zacne "kopat" cestu od policka 0,0
        allSquares.get(0).get(0).moveTo();
        genFin(size);
        addWall();
    }
}
