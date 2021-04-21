package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow(Maze mazeCanvas, JLabel winLabel) throws HeadlessException {
        var panelMain = new JPanel();
        var mainLayout= new BorderLayout();
        panelMain.setLayout(mainLayout);

        var panelMenu = new MenuPanel(winLabel,mazeCanvas);

        panelMain.add(panelMenu,BorderLayout.PAGE_START);
        panelMain.add(mazeCanvas);
        this.add(panelMain);

        mazeCanvas.addMouseListener(new MoveMouseListener(mazeCanvas));
        mazeCanvas.addMouseMotionListener(new MoveMouseListener(mazeCanvas));
        mazeCanvas.setFocusable(false);
        this.addKeyListener(new MoveKeyListener(mazeCanvas));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
