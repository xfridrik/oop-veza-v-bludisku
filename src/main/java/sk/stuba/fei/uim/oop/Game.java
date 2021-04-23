package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Game {
    private final JLabel winLabel;
    private int wins;

    public Game() {
        Maze mazeCanvas=new Maze(1,1,this);

        winLabel = new JLabel("Pocet vyhier 0",SwingConstants.CENTER);
        winLabel.setForeground(Color.CYAN);

        GameWindow window = new GameWindow(mazeCanvas, winLabel);
        window.repaint();
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