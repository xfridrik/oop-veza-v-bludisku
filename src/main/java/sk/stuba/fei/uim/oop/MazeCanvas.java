package sk.stuba.fei.uim.oop;

import java.awt.*;


public class MazeCanvas extends Canvas {
    PlayBoard board;
    Point pos;
    boolean clicked;
    Game game;
    Point cur;
    public MazeCanvas(PlayBoard board, int startX, int startY, Game game) {
        this.board=board;
        pos=new Point();
        this.pos.setLocation(startX,startY);
        this.game=game;
        clicked=false;
        cur=new Point();
    }

    @Override
    public void paint(Graphics g) {
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
                    if(clicked&&cur.x==j&&cur.y==i){
                        if(board.isReachableFromTo(pos.x, pos.y, j,i)){
                            g.setColor(Color.CYAN);
                        }
                        else g.setColor(Color.white);
                    }
                    else{
                        g.setColor(Color.white);
                    }
                }
                g.fillRect(i * 30, j*30, 30, 30);

            }
        }
        g.setColor(Color.PINK);
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
        if(clicked && !board.allSquares.get(cur.x).get(cur.y).isWall() && board.isReachableFromTo(pos.x, pos.y, cur.x,cur.y)){
            pos.x=cur.x;
            pos.y=cur.y;
            clicked=false;
            this.repaint();
            return false;
        }
        else{
            clicked=true;
            return true;
        }
    }

    public void setCurPos(Point position){
        if (clicked){
            cur.x=position.x;
            cur.y=position.y;
            repaint();
            System.out.println("NEW POLE");
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
    public void move(Move mov){
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
