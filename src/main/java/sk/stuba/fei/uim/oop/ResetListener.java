package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
    Maze can;
    public ResetListener(Maze can) {
        this.can=can;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        can.reset();
    }
}
