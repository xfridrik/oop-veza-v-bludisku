package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftListener implements ActionListener {
    MyCanvas can;
    public LeftListener(MyCanvas can) {
        this.can=can;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("LEFT");
        can.move(Move.LEFT);
    }
}