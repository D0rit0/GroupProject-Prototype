package util;

import entities.Entity;
import entities.items.loveLetterEntity;
import entities.mobs.Npc;
import world.DoorTrigger;
import world.Tile;

import java.awt.*;

import static java.lang.Math.abs;
import static main.AppPanel.*;

public class CollisionHandler {

    //Used this method to get a more accurate collision detection than what I came up with in the dungeon crawlers game
    //https://stackoverflow.com/search?q=user:11141508+collision-detection&s=4a7167c9-0b83-453a-ac02-e0e7db5e8b24
    //tweaked it a bit to better fit my code.
    public static void checkCollide(Tile tile){
        int w = 24;
        int h = 24;
        int dx = tile.getX() - player.getX();
        int dy = tile.getY() - player.getY();

        if (abs(dx) < w && abs(dy) < h)//collision
        {
            int wy = w * dy;
            int hx = h * dx;
            if (wy >= hx)
                if (wy >= -hx)//top of block
                    player.setTop(tile.getY()-17);//align edges
                else//right of block
                    player.setLeft(tile.getX()+32);
            else
                if (wy >= -hx)//left of block
                    player.setLeft(tile.getX()-17);

                else//bottom of block
                    player.setTop(tile.getY()+32);

        }
    }
    public static void doorCollide(){
        for(DoorTrigger e: currentMap.getDoorList()){
            Rectangle r1 = e.getBounds();
            Rectangle r2 = player.getBounds();
            if (r1.intersects(r2)) {
                e.trigger();
            }
        }

    }

    public static void entityCollide(){
        for(Npc e: currentMap.getNpcList()) {
            Rectangle r1 = e.getBounds();
            Rectangle r2 = player.getBounds();
            if (r1.intersects(r2) && e.isInteractable()) {
                e.interact();
            }
        }
        for(Entity e: currentMap.getItemList()){
            Rectangle r1 = e.getBounds();
            Rectangle r2 = player.getBounds();
            if (r1.intersects(r2) && e.isInteractable()) {
                if(e instanceof loveLetterEntity) {
                    ((loveLetterEntity) e).interact();
                }
            }
        }
    }
}
