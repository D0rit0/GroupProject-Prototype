import entities.Scenery.Buildings.*;
import entities.Scenery.Props.Prop;
import entities.Scenery.Scenery;
import entities.Entity;
import entities.Scenery.Tree;
import entities.mobs.Mob;
import entities.mobs.PeacefulAnimal;
import entities.mobs.PlayerCharacter;

import world.Tile;

import imageRenderer.MapLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppPanel extends JPanel {
    private final String imagePath="src\\resources\\atlas_32x.png";

    private final int width = 800;
    private final int height = 800;
    private static final ArrayList<Mob> mobList = new ArrayList<>();
    private final ArrayList<Scenery> sceneryList = new ArrayList<>();
    public static boolean mapScrollX = false;
    public static boolean mapScrollY = false;

    //public static WorldMap worldMap;
    public static PlayerCharacter player;
    public static PlayerController playerController = new PlayerController();

    public Map<Building, Point> buildingMap = new HashMap<>();
    public static ArrayList<ArrayList<Scenery>> gameMap = new ArrayList<>();
    public static Tile[] tileList = new Tile[100*100];
    public static int[][] map = MapLoader.loadLayerArray(MapLoader.getLayer(1));
    //Loads the textures and creates the tile objects using the data given from our map loader class
    private void mapInit(){
        MapLoader.LoadTextures();
        int i =0;
        for(int y = 0; y<map.length; y++){
            for(int x = 0; x<map[y].length;x++){
                try{
                    tileList[i] = new Tile(x * 32, y * 32, map[y][x]);
                    i++;
                }catch(OutOfMemoryError oome){
                    System.err.println("out of memory");
                }
            }
        }
        //dumps the map array to save some memory
        map=null;
        System.out.println("it worked");
    }
    AppPanel(){
        //spawns all needed objects
        spawnPlayer();
        mapInit();
        //spawnFloor();
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
        for(Tile tile: tileList){
            if(tile.isImageLoaded()&&shouldTileload(tile)&&tile.isVisible()) {
                g2.drawImage(tile.getImage(), tile.getX(), tile.getY(), this);
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

    private boolean shouldTileload(Tile tile){
        return tile.getX()>-32&&tile.getX()<832
        && tile.getY()>-32&&tile.getY()<832;
    }

    void update() {
        checkScroll();
        playerController.move();

        for (ArrayList<Scenery> list : gameMap) {
            for (Scenery scenery : list) {
                if (scenery instanceof Tree || scenery instanceof Prop)
                    checkCollide(scenery);
            }
        }
        if (player.getY() > height) {
            player.setY(0);
        }
        if (player.getX() > width) {
            player.setX(0);
        }
        if (player.getX() < 0){
            player.setX(width - player.getWidth());
        }
            repaint();
        if(mapScrollX || mapScrollY){
            for(Tile tile: tileList) {
                tile.setImageLoaded(shouldTileload(tile));
                if (!shouldTileload(tile)) {
                    tile.setVisible(false);
                } else {
                    if (tile.getImage() != null) {
                        tile.setVisible(true);
                    }else{
                        tile.setImage(MapLoader.textureMap.get(tile.getTileId()));
                    }
                }
            }
        }
    }
}
