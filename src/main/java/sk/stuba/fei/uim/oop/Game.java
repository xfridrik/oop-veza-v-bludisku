package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Game {
    PlayBoard board;
    GameWindow window;
    JLabel winLabel;
    int wins;

    public Game() {
        board=new PlayBoard();
        var canv=new MazeCanvas(board,1,1,this);

        winLabel = new JLabel("Pocet vyhier 0",SwingConstants.CENTER);
        winLabel.setForeground(Color.CYAN);

        var window=new GameWindow(canv,winLabel);
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
