import entities.Scenery.Buildings.*;
import entities.Scenery.Ground;
import entities.Scenery.Props.BigRock;
import entities.Scenery.Props.Prop;
import entities.Scenery.Scenery;
import entities.Entity;
import entities.Scenery.Tree;
import entities.mobs.Mob;
import entities.mobs.PeacefulAnimal;
import entities.mobs.PlayerCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AppPanel extends JPanel {
    private final int width = 800;
    private final int height = 800;
    private static final ArrayList<Mob> mobList = new ArrayList<>();
    private final ArrayList<Scenery> sceneryList = new ArrayList<>();
    public static boolean mapScrollX = false;
    public static boolean mapScrollY = false;

    public static PlayerCharacter player;
    public static PlayerController playerController = new PlayerController();
    public ArrayList<Tree> treeList = new ArrayList<>();
    public Map<Building, Point> buildingMap = new HashMap<>();
    public static ArrayList<ArrayList<Scenery>> gameMap = new ArrayList<>();
    //Initializes a 2D arraylist that stores the map tiles and props.
    //Randomly generated WILL CHANGE THIS TO STATIC MAP.
    private void mapInit(){
        buildingMap.put(new BuildingVar1(464,464,'b'),new Point(1000,1000));
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
        //spawns all needed objects
        mapInit();
        spawnPlayer();
        spawnFloor();
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(Color.BLACK);
        //Game Timer
        ActionListener timerTask = e -> update();
        int delay = 1;
        Timer gameTimer = new Timer(delay, timerTask);
        gameTimer.start();
    }
    //renders graphics
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw((Graphics2D) g);
    }
    //tells graphics render what to render
    private void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.drawString("dx:" + PlayerController.getDx() + " dy: " + PlayerController.getDy(),0,20);
        g2.drawString("x:" + player.getX() + " y: " + player.getY(),0,40);
        g2.drawLine((int)player.getCenter().getX(),(int)player.getCenter().getY(),playerController.mouseX,
                playerController.mouseY);
        for(ArrayList<Scenery> list: gameMap){
            for(Scenery scenery: list) {
                if(scenery instanceof Ground) {
                    g2.drawImage(scenery.getImage(), scenery.getX(), scenery.getY(), this);
                }
            }
        }
        for(ArrayList<Scenery> list: gameMap){
            for(Scenery scenery: list) {
                if(scenery instanceof Tree) {
                    g2.drawImage(scenery.getImage(), scenery.getX(), scenery.getY(), this);
                }
            }
        }
        for(ArrayList<Scenery> list: gameMap){
            for(Scenery scenery: list) {
                if(scenery instanceof Prop) {
                    g2.drawImage(scenery.getImage(), scenery.getX(), scenery.getY(), this);
                }
            }
        }
        for(Building building: buildingMap.keySet()){
            g2.drawImage(building.getImage(),building.getX(),building.getY(), this);
        }
        for(Mob entity: mobList){
            g2.drawImage(entity.getImage(), entity.getX(), entity.getY(), this);
        }
    }
    //Method to return the playerController so that other classes can access it.
    public static PlayerController getPlayerController(){
        return playerController;
    }

    //initializes player,
    //player is a singleton
    private void spawnPlayer(){
        player = PlayerCharacter.getInstance();
        //adds mouseListener from mouseAdapter to the Jpanel
        addMouseListener(playerController.MyMouseAdapter);
        //adds the mouseMotionListener from the mouseAdapter
        addMouseMotionListener(playerController.MyMouseAdapter);
        //Adds player to the list of mobs.
        mobList.add(player);
        mobList.add(new PeacefulAnimal(player.getX() +32, player.getY(), "Cat"));
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
                        scenery.setX(2);
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.setX(-2);
                    }
                }
            }else {
                player.setRight(player.getX() + player.getWidth() - 2);
            }
        } else if (r1.intersects(r2) && PlayerController.getDx() == -2) {
            playerController.setDx(0);
            if(mapScrollX) {
                for (ArrayList<Scenery> list : AppPanel.gameMap) {
                    for (Scenery scenery : list) {
                        scenery.setX(-2);
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.setX(-2);
                    }
                }
            }else {
                player.setLeft(player.getX() + 2);
            }
        }if (r1.intersects(r2) && PlayerController.getDy() == -2) {
            playerController.setDy(0);
            if(mapScrollY) {
                for (ArrayList<Scenery> list : AppPanel.gameMap) {
                    for (Scenery scenery : list) {
                        scenery.setY(-2);
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.setY(-2);
                    }
                }
            }else {
                player.setTop(player.getY() + 2);
            }
        } if (r1.intersects(r2) && PlayerController.getDy() == 2) {
            playerController.setDy(0);
            if(mapScrollY) {
                for (ArrayList<Scenery> list : AppPanel.gameMap) {
                    for (Scenery scenery : list) {
                        scenery.setY(2);
                    }
                }
                for (Mob mob : AppPanel.getMobList()) {
                    if (!(mob instanceof PlayerCharacter)) {
                        mob.setY(2);
                    }
                }
            }else {
                player.setBottom(player.getY() + player.getHeight() - 2);
            }
        }
    }
    public static ArrayList<Mob> getMobList(){
        return mobList;
    }
    private boolean checkScrollY(){
        return player.getY() < 32*3 && PlayerController.getDy() == -2
                || player.getY() > height -32*4 && PlayerController.getDy() == 2;
    }
    private boolean checkScrollX(){
        return player.getX() < 32*3 && PlayerController.getDx() == -2
                || player.getX() > width -32*4 && PlayerController.getDx() == 2;
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
        if(player.getY() >height){
            player.setY(0);
        }if(player.getX() >width){
            player.setX(0);
        }if(player.getX() <0)
            player.setX(width - player.getWidth());
        repaint();
    }
}
