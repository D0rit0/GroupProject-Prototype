package main;

import entities.mobs.Mob;
import entities.mobs.PeacefulAnimal;
import entities.mobs.PlayerCharacter;

import entities.mobs.characters.Crush;
import entities.mobs.characters.questGivers.Baker;
import entities.mobs.characters.questGivers.Florist;
import entities.mobs.characters.questGivers.Merchant;
import entities.mobs.characters.questGivers.Richard;
import util.CollisionHandler;
import util.TileHandler;
import util.imageRenderer.GraphicsHandler;

//import world.DoorTrigger;
//import world.DoorTrigger;
import world.DoorTrigger;
import world.Map;
import world.Tile;

import javax.swing.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static util.TileHandler.manageTiles;

public class AppPanel extends JPanel {
    private final String imagePath="src\\resources\\atlas1_32x.png";

    public static final int width = 800;
    public static final int height = 800;
    private static final int delay = 1;

    public static boolean mapScrollX = false;
    public static boolean mapScrollY = false;

    public static final Map overWorld = new Map("src\\world\\Team Valentine World Map.tmx",
            100, 100, 7);
    public static final Map market = new Map("src\\world\\generalStore.tmx",
            10, 10, 7);
    public static final Map florist = new Map("src\\world\\Florist.tmx",10,11, 9);
    public static final Map choco = new Map("src\\world\\Chocolate_shop.tmx",10,10,8);
    public static Map currentMap;

    public static PlayerCharacter player;
    public static PlayerController playerController = new PlayerController();

    //public static final ArrayList<Mob> mobList = new ArrayList<>();
    //public static final ArrayList<DoorTrigger> DoorList = new ArrayList<>();

    //Stole game state idea from https://www.youtube.com/watch?v=_SJU99LU1IQ
    public static int gameState;
    public static final int titleScreen = 0;
    public static final int running = 1;
    public static final int paused = 2;
    public static final int dialogue = 3;

    AppPanel(){
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);

        run();
    }

    private void run(){
        //spawns all needed objects
        spawnPlayer();

        //loads the maps
        currentMap = market;
        market.init();
        currentMap = florist;
        florist.init();
        currentMap = choco;
        choco.init();
        currentMap = overWorld;
        overWorld.init();

        spawnNpc();
        spawnDoorTriggers();

        //Game Timer
        ActionListener timerTask = e -> update();
        Timer gameTimer = new Timer(delay, timerTask);
        gameState = running;
        gameTimer.start();
    }

    //initializes player,
    //player is a singleton meaning no more than one instance of player can be created
    private void spawnPlayer(){
        player = PlayerCharacter.getInstance();

        //adds mouseListener from mouseAdapter to the JPanel
        addMouseListener(playerController.MyMouseAdapter);

        //adds the mouseMotionListener from the mouseAdapter
        addMouseMotionListener(playerController.MyMouseAdapter);

        //adds the keyListener from the keyAdapter
        addKeyListener(playerController.MyKeyAdapter);
    }
    private void spawnNpc(){
        overWorld.getNpcList().add(new PeacefulAnimal(player.getX() +32, player.getY(), "Cat"));
        florist.getNpcList().add(new Florist(3*32, 128, "Florist"));
        overWorld.getNpcList().add(new Baker(player.getX() + 32, player.getY() + 32, "Baker"));
        overWorld.getNpcList().add(new Merchant(player.getX() - 32, player.getY(), "Merchant"));
        overWorld.getNpcList().add(new Crush(player.getX() +32, player.getY() - 64, "<3"));
        overWorld.getNpcList().add(new Richard(player.getX() + 32, player.getY() + 64, "Richard"));
    }
    private void spawnDoorTriggers(){
        DoorTrigger floristDoor = new DoorTrigger(1824, 832,23, overWorld.getMapLocation(), florist, null);
        overWorld.getDoorList().add(floristDoor);
        florist.getDoorList().add(new DoorTrigger(16*9, 32*9,23, florist.getMapLocation(), overWorld,floristDoor));
        floristDoor.setLinkedDoor(florist.getDoorList().get(0));
    }

    //renders graphics
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //getting the information for what to render
        GraphicsHandler.render((Graphics2D) g);
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

    //update method called by the game timer
    private void update() {
        playerController.move();
        for(Tile tile: currentMap.getCollideableList()){
            if(TileHandler.shouldTileLoad(tile)){
                CollisionHandler.checkCollide(tile);
            }
        }
        checkScroll();
            repaint();
        if(mapScrollX || mapScrollY){
            manageTiles();
        }
    }
}
