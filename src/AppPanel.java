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
    private static final ArrayList<Mob> mobList = new ArrayList<>();
    private final ArrayList<Scenery> sceneryList = new ArrayList<>();
    public static boolean mapScrollX = false;
    public static boolean mapScrollY = false;

    public static PlayerCharacter player;
    public static PlayerController playerController = new PlayerController();
    public ArrayList<Tree> treeList = new ArrayList<>();
    public static ArrayList<ArrayList<Scenery>> gameMap = new ArrayList<>();
    private void mapInit(){
        for(int i = 0; i < 25; i++){
            gameMap.add(new ArrayList<>());
            if(i == 3 || i == 2){
                for(int j = 0; j < 25; j++){
                    gameMap.get(i).add(new Ground(i*32,j*32,1,(new Random()).nextInt(3)+6));
                }
            }else {
                for (int j = 0; j < 25; j++) {
                    gameMap.get(i).add(new Ground(i * 32, j * 32, (new Random()).nextInt(7)+1,
                            (new Random()).nextInt(3)+1));
                    if((new Random().nextInt(40)+1)==3){
                        gameMap.get(i).add(new Tree(i*32,j*32, (new Random()).nextInt(3)+1));
                    }if((new Random().nextInt(100)+1)==5){
                        gameMap.get(i).add(new BigRock(i*32,j*32));
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
        int delay = 1;
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
        g2.drawLine((int)player.getCenter().getX(),(int)player.getCenter().getY(),playerController.mouseX,
                playerController.mouseY);
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
        for(ArrayList<Scenery> list: gameMap){
            for(Scenery scenery: list) {
                if(scenery instanceof Prop) {
                    g2.drawImage(scenery.getImage(), scenery.x, scenery.y, this);
                }
            }
        }
        for(Mob entity: mobList){
            if(entity instanceof PlayerCharacter) {
                for(ArrayList<Scenery> list: gameMap) {
                    for (Scenery scenery : list) {
                        if (scenery instanceof Tree) {
                            if (entity.getY() < scenery.getY()+64)
                            g2.drawImage(entity.getImage(), entity.x, entity.y, this);
                            g2.drawImage(scenery.getImage(), scenery.x, scenery.y, this);
                        }
                    }
                }
            }else {
                g2.drawImage(entity.getImage(), entity.x, entity.y, this);
            }
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
            if (mapScrollX) {
                for (ArrayList<Scenery> list : AppPanel.gameMap) {
                    for (Scenery scenery : list) {
                        scenery.x += 2;
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.x += 2;
                    }
                }
            }else {
                player.setRight(player.x + player.width - 2);
            }
        } else if (r1.intersects(r2) && PlayerController.getDx() == -2) {
            playerController.setDx(0);
            if(mapScrollX) {
                for (ArrayList<Scenery> list : AppPanel.gameMap) {
                    for (Scenery scenery : list) {
                        scenery.x -= 2;
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.x -= 2;
                    }
                }
            }else {
                player.setLeft(player.x + 2);
            }
        }if (r1.intersects(r2) && PlayerController.getDy() == -2) {
            playerController.setDy(0);
            if(mapScrollY) {
                for (ArrayList<Scenery> list : AppPanel.gameMap) {
                    for (Scenery scenery : list) {
                        scenery.y -= 2;
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.y -= 2;
                    }
                }
            }else {
                player.setTop(player.y + 2);
            }
        } if (r1.intersects(r2) && PlayerController.getDy() == 2) {
            playerController.setDy(0);
            if(mapScrollY) {
                for (ArrayList<Scenery> list : AppPanel.gameMap) {
                    for (Scenery scenery : list) {
                        scenery.y += 2;
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.y += 2;
                    }
                }
            }else {
                player.setBottom(player.y + player.height - 2);
            }
        }
    }
    public static ArrayList<Mob> getMobList(){
        return mobList;
    }
    private boolean checkScrollY(){
        return player.y < 32*3 && PlayerController.getDy() == -2
                || player.y > height -32*4 && PlayerController.getDy() == 2;
    }
    private boolean checkScrollX(){
        return player.x < 32*3 && PlayerController.getDx() == -2
                || player.x > width -32*4 && PlayerController.getDx() == 2;
    }
    private void checkScroll(){
        mapScrollX = checkScrollX();
        mapScrollY = checkScrollY();
    }

    void update(){
        checkScroll();
        playerController.move();
        for(ArrayList<Scenery> list: gameMap){
            for(Scenery scenery:list) {
                if (scenery instanceof Tree || scenery instanceof Prop)
                    checkCollide(scenery);
            }
        }
        if(player.y>height){
            player.y=0;
        }if(player.x>width){
            player.x=0;
        }if(player.x<0)
            player.x=width - player.width;
        repaint();
    }
}
