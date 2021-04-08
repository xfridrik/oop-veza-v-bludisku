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

    private void addWall(){
        ArrayList<PlaySquare> wallLine=new ArrayList<>();
        for (ArrayList<PlaySquare> sqLine : allSquares) {
            sqLine.add(0, new PlaySquare());
            sqLine.add(new PlaySquare());
        }
        for(int i=0;i<allSquares.size()+2;i++){
            wallLine.add(new PlaySquare());
        }
        allSquares.add(wallLine);
        allSquares.add(0,wallLine);


    }
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
                    System.out.println("F");
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
    private void genereateWay(int size){
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                var curSquare=allSquares.get(i).get(j);
                if (!curSquare.wasVisited()){
                    curSquare.visit();
                    var end=curSquare.randomNeigh();
                    if(i==0 && j==0){
                        end.setFin();
                    }
                }
            }
        }
    }

    private void squareInit(int size){
        for(int i=0;i<size;i++){
            allSquares.add(new ArrayList<>());
            for(int j=0;j<size;j++){
                allSquares.get(i).add(new PlaySquare());
            }
        }
        for(int line=0;line<allSquares.size();line++){
            for(int col = 0; col <allSquares.size(); col++){
                ArrayList<PlaySquare> neighs=new ArrayList<>();
                if (line>0){ //UP
                    neighs.add(allSquares.get(line-1).get(col));
                }
                if (line<allSquares.size()-1){ //DOWN
                    neighs.add(allSquares.get(line+1).get(col));
                }
                if (col >0){ //LEFT
                    neighs.add(allSquares.get(line).get(col -1));
                }
                if (col <allSquares.size()-1){ //RIGHT
                    neighs.add(allSquares.get(line).get(col +1));
                }
                allSquares.get(line).get(col).setNeigh(neighs);
            }
        }
        genereateWay(size);
        addWall();
        for(ArrayList<PlaySquare> sql:allSquares){
            System.out.println();
            for(PlaySquare sq:sql) {
                System.out.print(sq.getS());
            }
        }
    }
}
