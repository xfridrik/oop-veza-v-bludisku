package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    JLabel winLabel;
    MazeCanvas canv;
    public MenuPanel(JLabel winLabel, MazeCanvas canv) {
        this.winLabel=winLabel;
        this.canv=canv;
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(2,3));
        this.setPreferredSize(new Dimension(500,120));
        addComponents();
    }

    private void addComponents(){
        var reset=new JButton("RESET");
        var up=new JButton("↑");
        var left=new JButton("←");
        var down=new JButton("↓");
        var right=new JButton("→") ;
        this.add(reset);
        this.add(up);
        this.add(winLabel);
        this.add(left);
        this.add(down);
        this.add(right);

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
    }
}
