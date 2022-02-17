package main;

import entities.mobs.Mob;
import entities.mobs.PlayerCharacter;

import world.Tile;
import world.Map;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayerController{
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
                if(key == KeyEvent.VK_A){
                    dx = -2;
                    player.changeImage(player.getSsCol(), player.setSsRow(13));
                    player.moving = true;
                }
                else if(key == KeyEvent.VK_D){
                    dx = 2;
                    player.changeImage(player.getSsCol(), player.setSsRow(14));
                    player.moving = true;
                }
                if(key == KeyEvent.VK_W) {
                    player.changeImage(player.getSsCol(), player.setSsRow(15));
                    dy = -2;
                    player.moving = true;
                }
                else if(key == KeyEvent.VK_S) {
                    player.changeImage(player.getSsCol(), player.setSsRow(12));
                    dy = 2;
                    player.moving = true;
                }
                player.animationTimer.start();

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
            player.animationTimer.stop();
            player.changeImage(26, player.getSsRow());
        }
    };
    public void move(){

        if(dy != 0 && dx !=0){
            if(!AppPanel.mapScrollX && AppPanel.mapScrollY) {
                player.setX(dx/2);
                for(Tile[] tileList: Map.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setY(-dy / 2);
                        }
                    }
                }
                for(Mob mob: AppPanel.getMobList()){
                    if(!(mob instanceof PlayerCharacter)){
                        mob.setY(-dy/2);
                    }
                }
            }else if (!AppPanel.mapScrollY && AppPanel.mapScrollX){
                player.setY(dy/2);
                for(Tile[] tileList: Map.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setX(-dx / 2);
                        }
                    }
                }
                for(Mob mob: AppPanel.getMobList()){
                    if(!(mob instanceof PlayerCharacter)){
                        mob.setX(-dx/2);
                    }
                }
            }else if(AppPanel.mapScrollX){
                for(Tile[] tileList: Map.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setX(-dx / 2);
                            scenery.setY(-dy / 2);
                        }
                    }
                }
                for(Mob mob: AppPanel.getMobList()){
                    if(!(mob instanceof PlayerCharacter)){
                        mob.setX(-dx/2);
                        mob.setY(-dy/2);
                    }
                }
            }else{
                player.setX(dx/2);
                player.setY(dy/2);
            }
        }else {
            if(!AppPanel.mapScrollX) {
                player.setX(dx);
            }else {
                for(Tile[] tileList: Map.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setX(-dx);
                        }
                    }
                }
                for(Mob mob: AppPanel.getMobList()){
                    if(!(mob instanceof PlayerCharacter)){
                        mob.setX(-dx);
                    }
                }
            }if(!AppPanel.mapScrollY) {
                player.setY(dy);
            }else{
                for(Tile[] tileList: Map.getLayerList()) {
                    for (Tile scenery : tileList) {
                        if(scenery != null) {
                            scenery.setY(-dy);
                        }
                    }
                }
                for(Mob mob: AppPanel.getMobList()){
                    if(!(mob instanceof PlayerCharacter)){
                        mob.setY(-dy);
                    }
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

