package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownListener implements ActionListener {
    MyCanvas can;
    public DownListener(MyCanvas can) {
        this.can=can;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DOWN");
        can.move(Move.DOWN);
    }
}
