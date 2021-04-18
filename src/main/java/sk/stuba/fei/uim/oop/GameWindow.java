package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    MazeCanvas canv;
    int wins;
    public GameWindow(MazeCanvas canv,JLabel winLabel) throws HeadlessException {
        this.canv = canv;
        this.setVisible(true);
        this.setSize(524,666);
        wins=0;

        var panelMain = new JPanel();
        var mainLayout= new BorderLayout();
        panelMain.setLayout(mainLayout);

        var panelMenu = new MenuPanel(winLabel,canv);

        panelMain.add(panelMenu,BorderLayout.PAGE_START);
        panelMain.add(canv);
        this.add(panelMain);

        canv.addMouseListener(new MoveMouseListener(canv));
        canv.addMouseMotionListener(new MoveMouseListener(canv));
        canv.setFocusable(false);
        this.addKeyListener(new MoveKeyListener(canv));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
