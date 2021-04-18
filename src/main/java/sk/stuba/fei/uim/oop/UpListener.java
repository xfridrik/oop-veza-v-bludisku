package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpListener implements ActionListener {
    MazeCanvas can;
    public UpListener(MazeCanvas can) {
        this.can=can;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("UP");
        can.moved(Move.UP);
    }
}
