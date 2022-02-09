import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppPanel extends JPanel {
    private final int width = 800;
    private final int height = 800;
    private final int centerX = width/2;
    private final int centerY = height/2;
    private final int delay = 10;
    private ArrayList<Mob> mobList = new ArrayList<>();
    private ArrayList<Scenery> sceneryList = new ArrayList<>();
    public static PlayerCharacter player;
    public static PlayerController playerController;
    Timer gameTimer;
    AppPanel(){
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(Color.BLACK);
        ActionListener timerTask = e -> update();
        gameTimer = new Timer(delay, timerTask);
        playerController = new PlayerController();
        spawnPlayer();
        spawnFloor();
        gameTimer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw((Graphics2D) g);
    }
    private void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.drawLine((int)player.getCenter().getX(),(int)player.getCenter().getY(),playerController.mouseX,playerController.mouseY);
        for(Mob entity: mobList){
            g2.drawImage(entity.getImage(),entity.x, entity.y, this);
        }
        for(Scenery entity: sceneryList){
            g2.drawImage(entity.getImage(),entity.x, entity.y, this);
        }
    }

    private void spawnPlayer(){
        player = PlayerCharacter.getInstance();
        addMouseListener(playerController.MyMouseAdapter);
        addMouseMotionListener(playerController.MyMouseAdapter);
        mobList.add(player);
        addKeyListener(playerController.MyKeyAdapter);
        System.out.print(""+player.getTop() + player.getBottom() + player.getLeft() + player.getRight());
    }
    private void spawnFloor(){
        for(int i = 0; i <= 12; i++){
            if(i==6){
                sceneryList.add(new Ground(32*i,sceneryList.get(4).getTop()-32,67));
            }
            sceneryList.add(new Ground(32 * i, player.getBottom()+10, 64));
        }
    }

    void update(){
        if(player.y>height){
            player.y=0;
        }if(player.x>width){
            player.x=0;
        }if(player.x<0)
            player.x=width- player.width;
        repaint();
        player.checkCollide();
        //System.out.println();

        playerController.move();
        //System.out.println(player.getX()+ ", " +player.getX());
        //System.out.println(mouseX + mouseY);
    }
}
