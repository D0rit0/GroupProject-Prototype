import javax.swing.*;
import javax.swing.event.TreeExpansionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class AppPanel extends JPanel {
    private final int width = 800;
    private final int height = 800;
    private final int centerX = width/2;
    private final int centerY = height/2;
    private final int delay = 1;
    private ArrayList<Mob> mobList = new ArrayList<>();
    private ArrayList<Scenery> sceneryList = new ArrayList<>();
    public static PlayerCharacter player;
    public static PlayerController playerController = new PlayerController();
    public ArrayList<Tree> treeList = new ArrayList<>();
    public ArrayList<ArrayList<Scenery>> gameMap = new ArrayList<>();
    private void mapInit(){
        for(int i = 0; i < 25; i++){
            gameMap.add(new ArrayList<Scenery>());
            if(i == 3 || i == 2){
                for(int j = 0; j < 25; j++){
                    gameMap.get(i).add(new Ground(i*32,j*32,1,(new Random()).nextInt(3)+6));
                }
            }else {
                for (int j = 0; j < 25; j++) {
                    gameMap.get(i).add(new Ground(i * 32, j * 32, (new Random()).nextInt(7)+1,(new Random()).nextInt(3)+1));
                    if((new Random().nextInt(40)+1)==3){
                        gameMap.get(i).add(new Tree(i*32,j*32, (new Random()).nextInt(3)+1));
                    }
                }
            }
        }
    }
    Timer gameTimer;
    AppPanel(){
        mapInit();
        spawnPlayer();
        spawnFloor();
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(Color.BLACK);
        ActionListener timerTask = e -> update();
        gameTimer = new Timer(delay, timerTask);
        gameTimer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw((Graphics2D) g);
    }
    private void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.drawString("dx:" + PlayerController.getDx() + " dy: " + PlayerController.getDy(),0,20);
        g2.drawString("x:" + player.x + " y: " + player.y,0,40);
        g2.drawLine((int)player.getCenter().getX(),(int)player.getCenter().getY(),playerController.mouseX,playerController.mouseY);
        for(ArrayList<Scenery> list: gameMap){
            for(Scenery scenery: list) {
                if(scenery instanceof Ground) {
                    g2.drawImage(scenery.getImage(), scenery.x, scenery.y, this);
                }
            }
        }
        for(ArrayList<Scenery> list: gameMap){
            for(Scenery scenery: list) {
                if(scenery instanceof Tree) {
                    g2.drawImage(scenery.getImage(), scenery.x, scenery.y, this);
                }
            }
        }
        for(Mob entity: mobList){
            g2.drawImage(entity.getImage(),entity.x, entity.y, this);
        }
    }
    public static PlayerController getPlayerController(){
        return playerController;
    }

    private void spawnPlayer(){
        player = PlayerCharacter.getInstance();
        addMouseListener(playerController.MyMouseAdapter);
        addMouseMotionListener(playerController.MyMouseAdapter);
        mobList.add(player);
        mobList.add(new PeacefulAnimal(player.x+32, player.y, "Cat"));
        addKeyListener(playerController.MyKeyAdapter);
        System.out.print(""+player.getTop() + player.getBottom() + player.getLeft() + player.getRight());
    }
    private void spawnFloor(){
        for(int i = 0; i <= 12; i++){
            if(i==6){
                sceneryList.add(new Ground(32*i,sceneryList.get(4).getTop()-32,1,1));
                System.out.println("x" +(32*i+32)+ "y"+(sceneryList.get(4).getTop()-32));
            }
            sceneryList.add(new Ground(32 * i, player.getBottom()+10, 1,1));
        }
    }
    public void checkCollide(Entity e) {
        Rectangle r1 = player.getBounds();
        Rectangle r2 = e.getBounds();
        if (r2.intersects(r1) && PlayerController.getDx() == 2) {
            playerController.setDx(0);
            player.setRight(e.getLeft());
        } else if (r1.intersects(r2) && PlayerController.getDx() == -2) {
            playerController.setDx(0);
            player.setLeft(e.getRight());
        } if (r1.intersects(r2) && PlayerController.getDy() == -2) {
            playerController.setDy(0);
            player.setTop(e.getBottom());
        } if (r1.intersects(r2) && PlayerController.getDy() == 2) {
            playerController.setDy(0);
            player.setBottom(e.getTop());
        }
    }

    void update(){
        playerController.move();
        for(Scenery scenery: sceneryList){
            checkCollide(scenery);
        }
        if(player.y>height){
            player.y=0;
        }if(player.x>width){
            player.x=0;
        }if(player.x<0)
            player.x=width - player.width;
        repaint();
        //System.out.println();
        //System.out.println(player.getX()+ ", " +player.getX());
        //System.out.println(mouseX + mouseY);
    }
}
