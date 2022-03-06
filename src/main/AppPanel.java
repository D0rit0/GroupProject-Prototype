package main;

import entities.mobs.Mob;
import entities.mobs.PeacefulAnimal;
import entities.mobs.PlayerCharacter;

import entities.mobs.characters.questGivers.Florist;
import util.CollisionHandler;
import util.TileHandler;
import util.imageRenderer.GraphicsHandler;

import world.Map;
import world.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static util.TileHandler.manageTiles;

public class AppPanel extends JPanel {
    private final String imagePath="src\\resources\\atlas_32x.png";

    public static final int width = 800;
    public static final int height = 800;
    private static final int delay = 1;

    public static boolean mapScrollX = false;
    public static boolean mapScrollY = false;


    public static PlayerCharacter player;
    public static PlayerController playerController = new PlayerController();

    public static final ArrayList<Mob> mobList = new ArrayList<>();

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
        Map.init();

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

        //Adds player to the list of mobs.
        mobList.add(player);
        mobList.add(new PeacefulAnimal(player.getX() +32, player.getY(), "Cat"));
        mobList.add(new Florist(player.getX() + 64, player.getY(), "Florist"));
    }

    //renders graphics
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //getting the information for what to render
        GraphicsHandler.render((Graphics2D) g);
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

    //update method called by the game timer
    private void update() {
        playerController.move();
        for(Tile tile: Map.getCollideableList()){
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
