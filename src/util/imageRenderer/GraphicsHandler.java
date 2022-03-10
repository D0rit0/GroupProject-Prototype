package util.imageRenderer;

import entities.mobs.Mob;
import entities.mobs.Npc;
import entities.mobs.PlayerCharacter;

import world.Map;
import world.Tile;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.HashMap;

import static main.AppPanel.*;

public class GraphicsHandler {
    public static java.util.Map<Integer, Image> textureMap = new HashMap<>();
    public static java.util.Map<Integer, Image> textureMap2 = new HashMap<>();
    static java.util.Map<Integer, Image> textures = new HashMap<>();

    private static final ImageObserver imageObserver = (img, infoflags, x, y, width, height) -> false;

    public static void render(Graphics2D g2){
        renderTiles(g2);
        renderMobs(g2);

        if(gameState==dialogue){
            player.currentDialogue.renderDialogueBox(g2);
        }

    }

    //Loads in textures into a map assigning the texture Id as the key
    public static void LoadTextures(int layer, int[][] dataArr){
        int tileMap;


        //loads in textures for specified layer
        for(int[] array: dataArr){
            for(int textureId: array){
                int subTextureId = textureId;
                if(currentMap == market) {
                    tileMap = 3;
                    if(textureId > 769) {
                        subTextureId = textureId - 768;
                        tileMap = 2;
                    }
                    if(!textureMap2.containsKey(textureId)&& textureId!=0) {
                        textureMap2.put(textureId, SpriteLoader.loadImage("src\\resources\\atlas"+tileMap+"_32x.png", subTextureId, tileMap));

                    }
                }else {
                    tileMap = 1;
                    if(!textureMap.containsKey(textureId)&& textureId!=0) {
                        textureMap.put(textureId, SpriteLoader.loadImage("src\\resources\\atlas"+tileMap+"_32x.png", subTextureId, tileMap));

                    }
                }
            }
        }
    }
    //renders all tiles
    public static void renderTiles(Graphics2D g2){
        //just checking each tile and seeing if it should be rendered
        for(Tile[] tileList: currentMap.getLayerList()) {
            for (Tile tile : tileList) {
                if (tile != null) {
                    if (tile.isImageLoaded() && tile.isVisible()) {
                        g2.drawImage(tile.getImage(), tile.getX(), tile.getY(), imageObserver);
                    }
                }
            }
        }
    }

    //renders all mobs
    public static void renderMobs(Graphics2D g2){
        for(Mob entity: mobList){
            if(entity instanceof Npc){
                g2.drawImage(entity.getImage(),entity.getX(),entity.getY(),imageObserver);
            }else
                g2.drawImage(entity.getImage(), entity.getX(), entity.getY(), imageObserver);
        }
    }
}
