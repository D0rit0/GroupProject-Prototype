package main;

import main.AppPanel;

import javax.swing.*;

public class AppFrame extends JFrame {
    AppFrame(){
        this.add(new AppPanel());
        this.setTitle("MouseTest");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

}
