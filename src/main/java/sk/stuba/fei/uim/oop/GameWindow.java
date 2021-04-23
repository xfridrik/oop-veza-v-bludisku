package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener {
    private final Maze mazeCanvas;

    public GameWindow(Maze mazeCanvas, JLabel winLabel) throws HeadlessException {
        this.mazeCanvas=mazeCanvas;
        var panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());

        var panelMenu = new MenuPanel(winLabel,mazeCanvas);

        panelMain.add(panelMenu,BorderLayout.PAGE_START);
        panelMain.add(mazeCanvas);
        this.add(panelMain);

        mazeCanvas.addMouseListener(new MoveMouseListener(mazeCanvas));
        mazeCanvas.addMouseMotionListener(new MoveMouseListener(mazeCanvas));
        mazeCanvas.setFocusable(false);
        this.addKeyListener(this);
        this.setTitle("MAZE GAME");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                mazeCanvas.moved(Move.UP);
                break;
            case KeyEvent.VK_DOWN:
                mazeCanvas.moved(Move.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                mazeCanvas.moved(Move.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                mazeCanvas.moved(Move.RIGHT);
                break;
            case KeyEvent.VK_R:
                mazeCanvas.reset();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}