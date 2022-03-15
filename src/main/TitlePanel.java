package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TitlePanel extends JPanel implements ActionListener {
    private String name = "Valentines Day Present Hunt";
    private Container c;
    private JFrame menuscreen;
    private JButton startbutton;
    private JLabel img;
    public TitlePanel(){
        menuscreen = new JFrame("Start Screen");
        ImageIcon image = new ImageIcon(getClass().getResource("StartScreen.png"));
        img = new JLabel(image);
        startbutton = new JButton("Start");
        startbutton.setPreferredSize(new Dimension(300, 300));
        startbutton.setBounds(300, 600, 200, 50);
        startbutton.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.add(img);
        img.add(startbutton);
        menuscreen.add(panel, BorderLayout.CENTER);
        menuscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = menuscreen.getContentPane();
        menuscreen.setTitle("TitleScreen");
        menuscreen.pack();
        c.add(this);
        menuscreen.setLocationRelativeTo(null);
        menuscreen.setVisible(true);
    }
    @Override
    public void paintComponent(Graphics g){

        g.setFont(new Font(name, 6, 18));
        g.setColor(Color.BLACK );
        g.drawString(name, 250, 300);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        Object a = ae.getSource();
        if(a == startbutton) {
            new AppFrame();
            this.menuscreen.dispose();
        }
    }

}
