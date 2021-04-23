package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveMouseListener extends MouseAdapter {
    private final Maze mazeCanvas;
    private final int squareSize;
    private int xp=1;
    private int yp=1;
    private boolean clicked;

    public MoveMouseListener(Maze canv) {
        this.mazeCanvas =canv;
        this.squareSize=canv.getSquareSize();
    }

    //ked sa klikne na vezu nastavi sa clicked a zavola funkcia v canvas
    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX()/squareSize;
        int y=e.getY()/squareSize;
        if(y== mazeCanvas.getPosition().getX() && x== mazeCanvas.getPosition().getY()){
            mazeCanvas.click();
            clicked=true;
            mazeCanvas.repaint();
        }
        else if(clicked){
            clicked= mazeCanvas.click();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int xc=e.getX()/squareSize;
        int yc=e.getY()/squareSize;
        //STACI SA PREKRESLIT LEN KED SA ZMENIL CELY STVORCEK
        if(xp!=xc || yp!=yc){
            xp=xc;
            yp=yc;
            mazeCanvas.setCurPos(new Point(yc,xc));
        }

    }

}