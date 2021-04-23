package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private JLabel winLabel;
    private Maze canv;
    private JButton reset;
    private JButton up;
    private JButton left;
    private JButton down;
    private JButton right;

    public MenuPanel(JLabel winLabel, Maze canv) {
        this.winLabel=winLabel;
        this.canv=canv;
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(2,3));
        this.setPreferredSize(new Dimension(500,120));
        addComponents();
    }

    private void addComponents(){
        reset=new JButton("RESET");
        up=new JButton("↑");
        left=new JButton("←");
        down=new JButton("↓");
        right=new JButton("→") ;
        this.add(reset);
        this.add(up);
        this.add(winLabel);
        this.add(left);
        this.add(down);
        this.add(right);

        reset.addActionListener(this);
        up.addActionListener(this);
        left.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);

        up.setFocusable(false);
        down.setFocusable(false);
        left.setFocusable(false);
        right.setFocusable(false);
        reset.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (reset.equals(source)) {
            canv.reset();
        } else if (up.equals(source)) {
            canv.moved(Move.UP);
        } else if (down.equals(source)) {
            canv.moved(Move.DOWN);
        } else if (left.equals(source)) {
            canv.moved(Move.LEFT);
        } else if (right.equals(source)) {
            canv.moved(Move.RIGHT);
        }
    }
}