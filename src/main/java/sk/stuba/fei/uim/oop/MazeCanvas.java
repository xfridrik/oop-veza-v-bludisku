package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;


public class MazeCanvas extends JPanel {
    PlayBoard board;
    Point pos; //pozicia veze
    boolean clicked; //kontroluje ci je zapata funkcia pohybu pomocou mysi
    Game game;
    Point cur; //bod nad kt. sa nachadza kurzor

    public MazeCanvas(PlayBoard board, int startX, int startY, Game game) {
        this.board=board;
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
        for (int j = 0; j < board.allSquares.size(); j++) {
            for (int i = 0; i < board.allSquares.size(); i++) {
                if (board.getAllSquares().get(j).get(i).isWall()) {
                    g.setColor(Color.darkGray);
                }
                else if(i==1 && j==1){
                    g.setColor(Color.green);
                }
                else if (board.getAllSquares().get(j).get(i).isFin()){
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.white);
                }
                if(clicked&&cur.x==j&&cur.y==i) {
                    if (board.isReachableFromTo(pos.x, pos.y, j, i)) {
                        g.setColor(Color.CYAN);
                    }
                }
                g.fillRect(i * 30, j*30, 30, 30);

            }
        }
        if (clicked){
            g.setColor(Color.MAGENTA);
        }
        else{
            g.setColor(Color.PINK);
        }
        g.fillOval(pos.y * 30 + 5, pos.x*30+5, 20, 20);
        if(board.getAllSquares().get(pos.x).get(pos.y).isFin()){
            System.out.println("VYHRA");
            game.win();
            nextGame();
        }
    }

    public boolean click(){
        System.out.println(pos);
        System.out.println(cur);
        //druhe kliknutie nastavi poziciu ak splna podmienky
        if(clicked && !board.allSquares.get(cur.x).get(cur.y).isWall() && board.isReachableFromTo(pos.x, pos.y, cur.x,cur.y)){
            pos.x=cur.x;
            pos.y=cur.y;
            clicked=false;
            this.repaint();
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
        this.board=new PlayBoard();
        pos.x=1;
        pos.y=1;
        game.resetWin();
        this.repaint();
    }
    public void nextGame(){
        this.board=new PlayBoard();
        pos.x=1;
        pos.y=1;
        this.repaint();
    }

    public void moved(Move mov){
        switch (mov){
            case UP: if (!board.getAllSquares().get(pos.x-1).get(pos.y).isWall()){
                pos.x=pos.x-1;
            }
                break;

            case DOWN: if (!board.getAllSquares().get(pos.x+1).get(pos.y).isWall()){
                pos.x=pos.x+1;
            }
                break;

            case LEFT: if (!board.getAllSquares().get(pos.x).get(pos.y-1).isWall()){
                pos.y=pos.y-1;
            }
                break;

            case RIGHT: if (!board.getAllSquares().get(pos.x).get(pos.y+1).isWall()){
                pos.y=pos.y+1;
            }
                break;
        }
        this.repaint();
    }
    public Point getPosition(){
        return pos;
    }
}
