package sk.stuba.fei.uim.oop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveKeyListener implements KeyListener {
    Maze canv;
    public MoveKeyListener(Maze canv) {
        this.canv=canv;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                System.out.println("UP");
                canv.moved(Move.UP);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                canv.moved(Move.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                canv.moved(Move.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                canv.moved(Move.RIGHT);
                break;
            case KeyEvent.VK_R:
                System.out.println("RESET");
                canv.reset();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
