package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveMouseListener extends MouseAdapter {
    MazeCanvas canv;
    int xp=1;
    int yp=1;
    boolean clicked;
    public MoveMouseListener(MazeCanvas canv) {
        this.canv=canv;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        int x=e.getX()/30;
        int y=e.getY()/30;
        if(y==canv.getPosition().getX() && x==canv.getPosition().getY()){
            canv.click();
            clicked=true;
            System.out.println("SUC");
        }
        else if(clicked){
            clicked=canv.click();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int xc=e.getX()/30;
        int yc=e.getY()/30;
        //PREKRESLI SA LEN KED SA ZMENIL CELY STVORCEK - AVOID BLIKANIE
        if(xp!=xc || yp!=yc){
            xp=xc;
            yp=yc;
            canv.setCurPos(new Point(yc,xc));

            /*System.out.println("pred"+xp);
            System.out.println("pred"+yp);
            System.out.println("po"+xc);
            System.out.println("po"+yc);*/
        }

    }

}

