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

        var panelMenu = new JPanel();
        panelMenu.setBackground(Color.DARK_GRAY);
        var panLayout= new GridLayout(2,3);
        panelMenu.setLayout(panLayout);
        panelMenu.setPreferredSize(new Dimension(500,120));

        var reset=new JButton("RESET");
        var up=new JButton("↑");
        var left=new JButton("←");
        var down=new JButton("↓");
        var right=new JButton("→") ;
        panelMenu.add(reset);
        panelMenu.add(up);
        panelMenu.add(winLabel);
        panelMenu.add(left);
        panelMenu.add(down);
        panelMenu.add(right);

        reset.addActionListener(new ResetListener(canv));
        up.addActionListener(new UpListener(canv));
        left.addActionListener(new LeftListener(canv));
        down.addActionListener(new DownListener(canv));
        right.addActionListener(new RightListener(canv));

        up.setFocusable(false);
        down.setFocusable(false);
        left.setFocusable(false);
        right.setFocusable(false);
        reset.setFocusable(false);

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
