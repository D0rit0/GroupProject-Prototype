package main;

import main.AppPanel;
import util.imageRenderer.SpriteLoader;

import javax.swing.*;

public class AppFrame extends JFrame {
    AppFrame(){
        this.add(new AppPanel());
        this.setTitle("ExplorationRPG");
        this.setIconImage(SpriteLoader.loadImage("src\\resources\\atlas1_32x.png",1082, 1));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

}
