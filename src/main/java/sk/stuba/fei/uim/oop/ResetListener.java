package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
    MyCanvas can;
    public ResetListener(MyCanvas can) {
        this.can=can;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        can.reset();
    }
}
