package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

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
            sqLine.add(0, new PlaySquare());
            sqLine.add(new PlaySquare());
        }
        for(int i=0;i<allSquares.size()+2;i++){
            wallLine.add(new PlaySquare());
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

    private void genWay(int size, int line, int col){
        ArrayList<Move> moves= new ArrayList<>();
        moves.add(Move.UP);
        moves.add(Move.DOWN);
        moves.add(Move.LEFT);
        moves.add(Move.RIGHT);
        Collections.shuffle(moves);
        for (int i=0;i<4;i++) {
            if (line > 1 && moves.get(0) == Move.UP && allSquares.get(line - 2).get(col).isWall()) { //UP
                allSquares.get(line - 1).get(col).setWay();
                allSquares.get(line - 2).get(col).setWay();
                genWay(size, line - 2, col);
            }
            else if (line < allSquares.size() - 2 && moves.get(0) == Move.DOWN && allSquares.get(line + 2).get(col).isWall()) { //DOWN
                allSquares.get(line + 1).get(col).setWay();
                allSquares.get(line + 2).get(col).setWay();
                genWay(size, line + 2, col);
            }
            else if (col > 1 && moves.get(0) == Move.LEFT && allSquares.get(line).get(col - 2).isWall()) { //LEFT
                allSquares.get(line).get(col - 1).setWay();
                allSquares.get(line).get(col - 2).setWay();
                genWay(size, line, col - 2);
            }
            else if (col < allSquares.size() - 2 && moves.get(0) == Move.RIGHT && allSquares.get(line).get(col + 2).isWall()) { //RIGHT
                allSquares.get(line).get(col + 1).setWay();
                allSquares.get(line).get(col + 2).setWay();
                genWay(size, line, col + 2);
            }
            moves.remove(0);
        }
    }

    //nastavi stvorceky bez vonkajsej steny
    private void squareInit(int size){
        for(int i=0;i<size;i++){
            allSquares.add(new ArrayList<>());
            for(int j=0;j<size;j++){
                allSquares.get(i).add(new PlaySquare());
            }
        }
        genWay(size,0,0);
        genFin(size);
        addWall();
    }
}
