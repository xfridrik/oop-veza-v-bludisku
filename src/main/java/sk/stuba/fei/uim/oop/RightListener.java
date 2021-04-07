package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightListener implements ActionListener {
    MyCanvas can;
    public RightListener(MyCanvas can) {
        this.can=can;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("RIGHT");
        can.move(Move.RIGHT);
    }
}
