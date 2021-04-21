package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveMouseListener extends MouseAdapter {
    Maze canv;
    int squareSize;
    int xp=1;
    int yp=1;
    boolean clicked;
    public MoveMouseListener(Maze canv) {
        this.canv=canv;
        this.squareSize=canv.getSquareSize();
    }

    //ked sa klikne na vezu nastavi sa clicked a zavola funkcia v canvas
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        int x=e.getX()/squareSize;
        int y=e.getY()/squareSize;
        if(y==canv.getPosition().getX() && x==canv.getPosition().getY()){
            canv.click();
            clicked=true;
            canv.repaint();
        }
        else if(clicked){
            clicked=canv.click();
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
            canv.setCurPos(new Point(yc,xc));
        }

    }

}

