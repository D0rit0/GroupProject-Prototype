import entities.scenery.Scenery;
import entities.Entity;
import entities.mobs.Mob;
import entities.mobs.PeacefulAnimal;
import entities.mobs.PlayerCharacter;

import world.Tile;

import imageRenderer.MapLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppPanel extends JPanel {
    private final String imagePath="src\\resources\\atlas_32x.png";

    private static final int width = 800;
    private static final int height = 800;
    private static final int delay = 1;

    public static boolean mapScrollX = false;
    public static boolean mapScrollY = false;

    //public static WorldMap worldMap;
    public static PlayerCharacter player;
    public static PlayerController playerController = new PlayerController();

    private static final ArrayList<Mob> mobList = new ArrayList<>();
    public static ArrayList<ArrayList<Scenery>> gameMap = new ArrayList<>();

    public static Tile[][] layerList = new Tile[7][100*100];
    //Loads the textures and creates the tile objects using the data given from our map loader class
    private void mapInit() {
        int[][] mapData;
        for (int temp = 0; temp < 7; temp++) {
            mapData = MapLoader.loadLayerArray(MapLoader.getLayer(temp+1));
            MapLoader.LoadTextures(temp+1, mapData);
            int i = 0;
            for (int y = 0; y < mapData.length; y++) {
                for (int x = 0; x < mapData[y].length; x++) {
                    try {
                        if(mapData[y][x]!=0) {
                            layerList[temp][i] = new Tile(x * 32, y * 32, mapData[y][x]);
                        }
                        i++;
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        System.err.println("out of memory");
                    }
                }
            }
        }
        System.out.println("it worked");
    }
    AppPanel(){
        //spawns all needed objects
        spawnPlayer();
        mapInit();

        this.setFocusable(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(Color.BLACK);

        //Game Timer
        ActionListener timerTask = e -> update();
        Timer gameTimer = new Timer(delay, timerTask);
        gameTimer.start();
    }
    //initializes player,
    //player is a singleton meaning no more than one instance of player can be created
    private void spawnPlayer(){
        player = PlayerCharacter.getInstance();
        //adds mouseListener from mouseAdapter to the Jpanel
        addMouseListener(playerController.MyMouseAdapter);
        //adds the mouseMotionListener from the mouseAdapter
        addMouseMotionListener(playerController.MyMouseAdapter);
        //adds the keyListener from the keyAdapter
        addKeyListener(playerController.MyKeyAdapter);
        //Adds player to the list of mobs.
        mobList.add(player);
        mobList.add(new PeacefulAnimal(player.getX() +32, player.getY(), "Cat"));
    }
    //renders graphics
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //getting the information for what to render
        render((Graphics2D) g);
    }
    //tells graphics renderer what to render
    private void render(Graphics2D g2){
        //just checking each tile and seeing if it should be rendered
        for(Tile[] tileList: layerList) {
            for (Tile tile : tileList) {
                if (tile != null) {
                    if (tile.isImageLoaded() && shouldTileload(tile) && tile.isVisible()) {
                        g2.drawImage(tile.getImage(), tile.getX(), tile.getY(), this);
                    }
                }
            }
        }
        //renders all mobs
        for(Mob entity: mobList){
            g2.drawImage(entity.getImage(), entity.getX(), entity.getY(), this);
        }
    }

    //checks collision between play and the given entity
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
    //checks to see if player is past the scroll threshold and which direction the player is moving
    //the direction is used so that if the player goes to turn around the map doesn't continue to scroll
    //if player is then returns true
    private boolean checkScrollY(){
        return player.getY() < 32*10 && PlayerController.getDy() == -2
                || player.getY() > height -32*9 && PlayerController.getDy() == 2;
    }
    private boolean checkScrollX(){
        return player.getX() < 32*10 && PlayerController.getDx() == -2
                || player.getX() > width -32*9 && PlayerController.getDx() == 2;
    }
    //called every tick and determines which direction is scrolling
    private void checkScroll(){
        mapScrollX = checkScrollX();
        mapScrollY = checkScrollY();
    }
    //checks to see if tile should be loaded based on if its within view of the panel
    private boolean shouldTileload(Tile tile){
        if(tile != null) {
            return tile.getX() > -32 && tile.getX() < 832
                    && tile.getY() > -32 && tile.getY() < 832;
        }
        return false;
    }
    //manages which tiles should be rendered and if a texture has been loaded onto a tile.
    private void manageTiles(){
        for(Tile[] tileList: layerList) {
            for (Tile tile : tileList) {
                if(tile !=null) {
                    tile.setImageLoaded(shouldTileload(tile));
                    if (!shouldTileload(tile)) {
                        tile.setVisible(false);
                    } else {
                        if (tile.getImage() != null) {
                            tile.setVisible(true);
                        } else {
                            tile.setImage(MapLoader.textureMap.get(tile.getTileId()));
                        }
                    }
                }
            }
        }
    }

    //update method called by the game timer
    private void update() {
        checkScroll();
        playerController.move();
            repaint();
        if(mapScrollX || mapScrollY){
            manageTiles();
        }
    }
}
