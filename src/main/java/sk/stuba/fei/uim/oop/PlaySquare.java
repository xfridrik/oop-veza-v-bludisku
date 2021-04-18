package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class PlaySquare {
    private boolean wall;
    private boolean fin;
    private Point position;
    private ArrayList<Move> moves;
    private int mazeSize;
    private ArrayList<ArrayList<PlaySquare>> all;
    private int numOfMoves;

    public PlaySquare(int size,int line,int col,ArrayList<ArrayList<PlaySquare>>all) {
        this.all=all;
        this.position=new Point(line,col);
        this.wall=true;
        this.fin=false;
        this.mazeSize=size;
        moveInit();
    }
    public ArrayList<Move> getMoves(){
        return this.moves;
    }
    //pohybuje sa vzdy na parne suradnice (o 2 policka)
    public void moveTo() {
        this.setWay();
        int line = position.x;
        int col = position.y;

        //skusi vsetky pohyby a ak sa da vykonat zavola sa rekurzia pre policko kam sa vykonal pohyb a policko medzi nimi sa nastavi na cestu
        for (int i = 0; i< numOfMoves; i++) {
             if (moves.get(0) == Move.UP && all.get(line - 2).get(col).isWall()) { //UP
                all.get(line - 1).get(col).setWay(); //stredne policko
                all.get(line - 2).get(col).moveTo(); //pohyb o 2
            }
            else if (moves.get(0) == Move.DOWN && all.get(line + 2).get(col).isWall()) { //DOWN
                all.get(line + 1).get(col).setWay();
                all.get(line + 2).get(col).moveTo();
            }
            else if (moves.get(0) == Move.LEFT && all.get(line).get(col - 2).isWall()) { //LEFT
                all.get(line).get(col - 1).setWay();
                all.get(line).get(col - 2).moveTo();
            }
            else if (moves.get(0) == Move.RIGHT && all.get(line).get(col + 2).isWall()) { //RIGHT
                all.get(line).get(col + 1).setWay();
                all.get(line).get(col + 2).moveTo();
            }
            moves.remove(0);
        }
    }
    public boolean isWall(){
        return wall;
    }
    public void setFin(){
        fin=true;
    }
    public void setWay(){
        wall=false;
    }
    public boolean isFin(){
        return fin;
    }
    //Priradi vsetky mozne pohyby o 2 z tohto policka a nahodne ich zoradi
    private void moveInit(){
        moves=new ArrayList<>();
        int line=position.x;
        int col=position.y;

        if (line > 1) { //UP
            moves.add(Move.UP);
        }
        if (line < mazeSize - 2) { //DOWN
            moves.add(Move.DOWN);
        }
        if (col > 1) { //LEFT
            moves.add(Move.LEFT);
        }
        if (col < mazeSize - 2) { //RIGHT
            moves.add(Move.RIGHT);
        }

        Collections.shuffle(moves);
        this.numOfMoves = moves.size();
    }

}
