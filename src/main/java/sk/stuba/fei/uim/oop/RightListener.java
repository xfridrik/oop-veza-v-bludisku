package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightListener implements ActionListener {
    Maze can;
    public RightListener(Maze can) {
        this.can=can;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("RIGHT");
        can.moved(Move.RIGHT);
    }
}
