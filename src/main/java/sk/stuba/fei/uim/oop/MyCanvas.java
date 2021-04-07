package sk.stuba.fei.uim.oop;

import java.awt.*;


public class MyCanvas extends Canvas {
    PlayBoard board;
    int posX;
    int posY;
    Game game;
    public MyCanvas(PlayBoard board,int startX,int startY,Game game) {
        this.board=board;
        this.posX=startX;
        this.posY=startY;
        this.game=game;
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
                    g.setColor(Color.white);
                }
                g.fillRect(i * 30 + 18, j*30, 30, 30);

            }
        }
        g.setColor(Color.CYAN);
        g.fillRect(posY * 30 + 18, posX*30, 30, 30);
        if(board.getAllSquares().get(posX).get(posY).isFin()){
            System.out.println("VYHRA");
            game.win();
            nextGame();
        }
        /*for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 15; i++) {
                if ((i+j) % 2 == 0) {
                    g.setColor(Color.darkGray);
                    g.fillRect(i * 30 + 18, j*30, 30, 30);
                } else {
                    g.setColor(Color.white);
                    g.fillRect(i * 30 + 18, j*30, 30, 30);
                }
            }
        }*/
    }

    public void reset(){
        this.board=new PlayBoard();
        posX=1;
        posY=1;
        game.resetWin();
        this.repaint();
    }
    public void nextGame(){
        this.board=new PlayBoard();
        posX=1;
        posY=1;
        this.repaint();
    }
    public void move(Move mov){
        switch (mov){
            case UP: if (!board.getAllSquares().get(posX-1).get(posY).isWall()){
                posX=posX-1;
            }
                break;

            case DOWN: if (!board.getAllSquares().get(posX+1).get(posY).isWall()){
                posX=posX+1;
            }
                break;

            case LEFT: if (!board.getAllSquares().get(posX).get(posY-1).isWall()){
                posY=posY-1;
            }
                break;

            case RIGHT: if (!board.getAllSquares().get(posX).get(posY+1).isWall()){
                posY=posY+1;
            }
                break;
        }
        this.repaint();
    }
}
