package main;

import entities.items.loveLetterEntity;
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
import java.awt.*;
import java.awt.event.ActionListener;

import static util.TileHandler.manageTiles;

public class AppPanel extends JPanel {

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

    //Stole game state idea from https://www.youtube.com/watch?v=_SJU99LU1IQ
    public static int gameState;
    public static final int running = 1;
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
        spawnNpc();
        spawnDoorTriggers();

        //loads the maps
        currentMap = market;
        market.init();
        currentMap = florist;
        florist.init();
        currentMap = choco;
        choco.init();
        currentMap = overWorld;
        overWorld.init();

        currentMap.shift(-1200, -464);

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
        choco.getNpcList().add(new Baker(4*32,  6*32, "Baker"));
        overWorld.getNpcList().add(new Merchant(player.getX() - 32, player.getY(), "Merchant"));
        overWorld.getNpcList().add(new Crush(player.getX() +32, player.getY() - 64, "<3"));
        overWorld.getNpcList().add(new Richard(player.getX() + 32, player.getY() + 64, "Richard"));

        overWorld.getItemList().add(new loveLetterEntity(player.getX()+36*32,player.getY()-6*32));
    }
    private void spawnDoorTriggers(){
        DoorTrigger floristDoor = new DoorTrigger(46*32, 33*32,23, overWorld.getMapLocation(), florist, null);
        DoorTrigger marketDoor = new DoorTrigger(47*32, 26*32, 23, overWorld.getMapLocation(), market, null);
        DoorTrigger chocoDoor = new DoorTrigger(57*32, 26*32, 23, overWorld.getMapLocation(), choco, null);

        overWorld.getDoorList().add(floristDoor);
        overWorld.getDoorList().add(marketDoor);
        overWorld.getDoorList().add(chocoDoor);

        florist.getDoorList().add(new DoorTrigger(16*9, 32*10,23, florist.getMapLocation(), overWorld,floristDoor));
        market.getDoorList().add(new DoorTrigger(4*32+16,9*32,23,market.getMapLocation(),overWorld,marketDoor));
        choco.getDoorList().add(new DoorTrigger(4*32+16,9*32,23,choco.getMapLocation(),overWorld,chocoDoor));

        floristDoor.setLinkedDoor(florist.getDoorList().get(0));
        marketDoor.setLinkedDoor(market.getDoorList().get(0));
        chocoDoor.setLinkedDoor(choco.getDoorList().get(0));
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
