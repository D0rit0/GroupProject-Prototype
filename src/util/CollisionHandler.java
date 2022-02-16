package util;

import entities.mobs.Mob;
import entities.mobs.PlayerCharacter;

import main.AppPanel;
import main.PlayerController;
import world.Map;
import world.Tile;

import java.awt.*;

import static main.AppPanel.*;

public class CollisionHandler {

    private static final Tile[][] layerList = Map.getLayerList();

    //checks collision between play and the given entity
    public static void checkCollide(Tile tile) {
        Rectangle r1 = player.getBounds();
        Rectangle r2 = tile.getBounds();
        if (r2.intersects(r1) && PlayerController.getDx() == 2) {
            playerController.setDx(0);
            if (mapScrollX) {
                for (Tile[] list : layerList) {
                    for(Tile t: list) {
                        t.setX(2);
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
                for (Tile[] list : layerList) {
                    for(Tile t: list) {
                        t.setX(-2);
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
                for (Tile[] list : layerList) {
                    for(Tile t: list) {
                        t.setY(-2);
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
                for (Tile[] list : layerList) {
                    for(Tile t: list) {
                        t.setY(2);
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
}
