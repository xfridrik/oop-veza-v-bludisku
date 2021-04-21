package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;


public class Maze extends JPanel {
    private final int squareSize;
    private PlayBoard board;
    private Point pos; //pozicia veze
    private Game game;
    private Point cur; //bod nad kt. sa nachadza kurzor
    private boolean clicked; //kontroluje ci je zapata funkcia pohybu pomocou mysi

    public Maze(int startX, int startY, Game game) {
        this.squareSize=30;
        this.board=new PlayBoard();
        this.setPreferredSize(new Dimension(board.getAllSquares().size()*squareSize,board.getAllSquares().size()*squareSize));
        this.pos=new Point();
        this.cur=new Point();
        this.pos.setLocation(startX,startY);
        this.game=game;
        this.clicked=false;
    }

    //Vykresli vzdy vsetky stvorceky a nastavuje farby podla premennych
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int j = 0; j < board.getAllSquares().size(); j++) {
            for (int i = 0; i < board.getAllSquares().size(); i++) {
               squareSetColor(g,board.getAllSquares().get(j).get(i),i,j);
                g.fillRect(i * squareSize, j*squareSize, squareSize, squareSize);
            }
        }
        towerSetColor(g);
        g.fillOval(pos.y * squareSize + 5, pos.x*squareSize+5, 20, 20);
    }

    private void towerSetColor(Graphics g){
        if (clicked){
            g.setColor(Color.MAGENTA);
        }
        else{
            g.setColor(Color.PINK);
        }
    }

    private void squareSetColor(Graphics g,PlaySquare square,int sqX, int sqY){
        if (square.isWall()) {
            g.setColor(Color.darkGray);
        }
        else if(sqX==1 && sqY==1){
            g.setColor(Color.green);
        }
        else if (board.getAllSquares().get(sqY).get(sqX).isFin()){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.white);
        }
        if(clicked && cur.x==sqY&&cur.y==sqX) {
            if (board.isReachableFromTo(pos.x, pos.y, sqY, sqX)) {
                g.setColor(Color.CYAN);
            }
        }
    }

    public boolean click(){
        System.out.println(pos);
        System.out.println(cur);
        //druhe kliknutie nastavi poziciu ak splna podmienky
        if(clicked && !board.getAllSquares().get(cur.x).get(cur.y).isWall() && board.isReachableFromTo(pos.x, pos.y, cur.x,cur.y)){
            pos.x=cur.x;
            pos.y=cur.y;
            clicked=false;
            this.repaint();
            checkWin();
            return false;
        }
        //prve kliknutie alebo kliknutie mimo povolene stvorce zapne/necha zapnutu funkciu pohybu mysou
        else{
            clicked=true;
            return true;
        }
    }

    //nastavi poziciu kurzora pri pohybe mysou
    public void setCurPos(Point position){
        if (clicked){
            cur.x=position.x;
            cur.y=position.y;
            repaint();
        }
    }

    public void reset(){
        game.resetWin();
        nextGame();
    }

    public void nextGame(){
        this.board=new PlayBoard();
        pos.x=1;
        pos.y=1;
        this.repaint();
    }

    public void moved(Move mov){
        switch (mov){
            case UP:
                if (!board.getAllSquares().get(pos.x-1).get(pos.y).isWall()){
                pos.x=pos.x-1;}
                break;

            case DOWN:
                if (!board.getAllSquares().get(pos.x+1).get(pos.y).isWall()){
                pos.x=pos.x+1;}
                break;

            case LEFT:
                if (!board.getAllSquares().get(pos.x).get(pos.y-1).isWall()){
                pos.y=pos.y-1;}
                break;

            case RIGHT:
                if (!board.getAllSquares().get(pos.x).get(pos.y+1).isWall()){
                pos.y=pos.y+1;}
                break;
        }
        this.repaint();
        checkWin();
    }

    private void checkWin(){
        if(board.getAllSquares().get(pos.x).get(pos.y).isFin()){
            System.out.println("VYHRA");
            game.win();
            nextGame();
        }
    }
    public Point getPosition(){
        return pos;
    }

    public int getSquareSize() {
        return squareSize;
    }
}
