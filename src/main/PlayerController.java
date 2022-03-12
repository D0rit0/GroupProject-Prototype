package main;

import entities.mobs.Mob;
import entities.mobs.PlayerCharacter;

import util.CollisionHandler;
import util.Decisions;
import world.Tile;
import world.Map;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.AppPanel.*;

public class PlayerController {
    protected int mouseX;
    protected int mouseY;
    private static int dx = 0;
    private static int dy = 0;


    private final PlayerCharacter player = PlayerCharacter.getInstance();


    MouseInputAdapter MyMouseAdapter = new MouseInputAdapter(){

        public void mouseMoved(MouseEvent e){
            mouseX = e.getX();
            mouseY = e.getY();
        }
        public void mouseEntered(MouseEvent e){
            System.out.println("mouse Entered");
        }
    };
    public static int getDx(){
        return dx;
    }
    public static int getDy(){
        return dy;
    }


    KeyAdapter MyKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            //controls for when in regular gameplay
            if(gameState == running) {
                if(key != KeyEvent.VK_E) {
                    if (key == KeyEvent.VK_A) {
                        dx = -2;
                        player.changeImage(player.getSsCol(), player.setSsRow(player.getLeftFace()));
                        player.moving = true;
                    } else if (key == KeyEvent.VK_D) {
                        dx = 2;
                        player.changeImage(player.getSsCol(), player.setSsRow(player.getRightFace()));
                        player.moving = true;
                    }
                    if (key == KeyEvent.VK_W) {
                        player.changeImage(player.getSsCol(), player.setSsRow(player.getRearFace()));
                        dy = -2;
                        player.moving = true;
                    } else if (key == KeyEvent.VK_S) {
                        player.changeImage(player.getSsCol(), player.setSsRow(player.getCenterFace()));
                        dy = 2;
                        player.moving = true;
                    }
                    player.animationTimer.start();
                }else {
                    CollisionHandler.entityCollide();
                    CollisionHandler.doorCollide();
                }
            }
            //Controls for when in dialogue
            else if(gameState == dialogue){
                if(player.currentDialogue.isQuestionState()){
                    switch(key){
                        case KeyEvent.VK_UP -> player.currentDialogue.question().previous();
                        case KeyEvent.VK_DOWN -> player.currentDialogue.question().next();
                        case KeyEvent.VK_ENTER ->{
                            Decisions.outCome(player.currentDialogue.question().decision());
                        }
                    }
                }else
                    if(key == KeyEvent.VK_SPACE) {
                    if (!player.currentDialogue.hasNext()) {
                        player.currentDialogue.close();
                    }
                    player.currentDialogue.nextText();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_A, KeyEvent.VK_D -> {
                    dx=0; player.moving = false;
                }
                case KeyEvent.VK_W, KeyEvent.VK_S -> {
                    dy = 0; player.moving = false;
                }
            }
            if(dx == 0 && dy == 0) {
                player.animationTimer.stop();
                player.changeImage(player.getRestState(), player.getSsRow());
            }
        }
    };
    public void move(){

        if(dy != 0 && dx !=0){
            if(!AppPanel.mapScrollX && AppPanel.mapScrollY) {
                player.setX(dx/2);
                for(Tile[] tileList: currentMap.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setY(-dy / 2);
                        }
                    }
                }
                currentMap.moveDoorsY(-dy/2);
                for(Mob mob: currentMap.getNpcList()){
                    mob.setY(-dy/2);
                }
            }else if (!AppPanel.mapScrollY && AppPanel.mapScrollX){
                player.setY(dy/2);
                for(Tile[] tileList: currentMap.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setX(-dx / 2);
                        }
                    }
                }
                currentMap.moveDoorsX(-dx/2);
                for(Mob mob: currentMap.getNpcList()){
                    mob.setX(-dx/2);
                }
            }else if(AppPanel.mapScrollX){
                for(Tile[] tileList: currentMap.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setX(-dx / 2);
                            scenery.setY(-dy / 2);
                        }
                    }
                }
                currentMap.moveDoorsX(-dx/2);
                currentMap.moveDoorsY(-dy/2);
                for(Mob mob: currentMap.getNpcList()){
                    mob.setX(-dx/2);
                    mob.setY(-dy/2);
                }
            }else{
                player.setX(dx/2);
                player.setY(dy/2);
            }
        }else {
            if(!AppPanel.mapScrollX) {
                player.setX(dx);
            }else {
                for(Tile[] tileList: currentMap.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setX(-dx);
                        }
                    }
                }
                currentMap.moveDoorsX(-dx);
                for(Mob mob: currentMap.getNpcList()){
                    mob.setX(-dx);
                }
            }if(!AppPanel.mapScrollY) {
                player.setY(dy);
            }else{
                for(Tile[] tileList: currentMap.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setY(-dy);

                        }
                    }
                }
                currentMap.moveDoorsY(-dy);
                for(Mob mob: currentMap.getNpcList()){
                    mob.setY(-dy);
                }
            }
        }
    }

    public static void setDx(int newDx) {
        dx=newDx;
    }
    public static void setDy(int newDy) {
        dx=newDy;
    }
}

