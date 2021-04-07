package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Game {
    PlayBoard board;
    JLabel winLabel;
    int wins;
    public Game() {
        board=new PlayBoard();
        var window=new JFrame();
        window.setVisible(true);
        window.setSize(560,680);
        wins=0;

        var panelMain = new JPanel();
        var mainLayout= new BorderLayout();
        panelMain.setLayout(mainLayout);

        var panelMenu = new JPanel();
        panelMenu.setBackground(Color.DARK_GRAY);
        var panLayout= new GridLayout(2,3);
        panelMenu.setLayout(panLayout);
        panelMenu.setPreferredSize(new Dimension(500,120));
        winLabel = new JLabel("Pocet vyhier 0",SwingConstants.CENTER);
        winLabel.setForeground(Color.CYAN);
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

        var canv=new MyCanvas(board,1,1,this);
        reset.addActionListener(new ResetListener(canv));
        up.addActionListener(new UpListener(canv));
        left.addActionListener(new LeftListener(canv));
        down.addActionListener(new DownListener(canv));
        right.addActionListener(new RightListener(canv));

        up.addKeyListener(new MoveKeyListener());

        panelMain.add(panelMenu,BorderLayout.PAGE_START);
        panelMain.add(canv);
        window.add(panelMain);

    }
    public void win(){
        wins++;
        winLabel.setText("Pocet vyhier "+wins);
    }
    public void resetWin(){
        wins=0;
        winLabel.setText("Pocet vyhier "+wins);
    }



}
