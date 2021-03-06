package util.imageRenderer;

import entities.Entity;
import entities.items.loveLetterEntity;
import entities.mobs.Mob;
import entities.mobs.Npc;
import entities.mobs.PlayerCharacter;

import world.DoorTrigger;
import world.Map;
import world.Tile;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.HashMap;

import static main.AppPanel.*;

public class GraphicsHandler {
    public static java.util.Map<Integer, Image> textureMap = new HashMap<>();
    public static java.util.Map<Integer, Image> textureMap2 = new HashMap<>();
    public static java.util.Map<Integer, Image> textureMap3 = new HashMap<>();
    public static java.util.Map<Integer, Image> textureMap4 = new HashMap<>();
    static java.util.Map<Integer, Image> textures = new HashMap<>();

    private static final ImageObserver imageObserver = (img, infoflags, x, y, width, height) -> false;

    public static void render(Graphics2D g2){
        renderTiles(g2);
        renderMobs(g2);

        if(gameState==dialogue){
            player.currentDialogue.renderDialogueBox(g2);
        }
        /*g2.setColor(Color.RED);
        g2.drawString(player.getX()+", " + player.getY(), 20, 20);
        for(DoorTrigger e :currentMap.getDoorList()){
            g2.fillRect(e.getX(),e.getY(),32,32);
        }*/

    }

    //Loads in textures into a map assigning the texture Id as the key
    public static void LoadTextures(int[][] dataArr){
        int tileMap;


        //loads in textures for specified layer
        for(int[] array: dataArr){
            for(int textureId: array){
                int subTextureId = textureId;
                if (currentMap == market) {
                    tileMap = 3;
                    if(textureId > 769) {
                        subTextureId = textureId - 768;
                        tileMap = 2;
                    }
                    if(!textureMap2.containsKey(textureId)&& textureId!=0) {
                        textureMap2.put(textureId, SpriteLoader.loadImage("src\\resources\\atlas"+tileMap+"_32x.png", subTextureId, tileMap));

                    }
                }
                else if(currentMap == overWorld) {
                    tileMap = 1;
                    if(!textureMap.containsKey(textureId)&& textureId!=0) {
                        textureMap.put(textureId, SpriteLoader.loadImage("src\\resources\\atlas"+tileMap+"_32x.png", subTextureId, tileMap));

                    }
                }
                else if(currentMap == florist){
                    tileMap = 3;
                    if(textureId > 769 && textureId < 1281){
                        subTextureId = textureId - 768;
                        tileMap = 2;
                    }else if(textureId > 1281){
                        subTextureId = textureId - 1280;
                        tileMap = 1;
                    }
                    if(!textureMap3.containsKey(textureId)&&textureId!=0){
                        textureMap3.put(textureId, SpriteLoader.loadImage("src\\resources\\atlas"+tileMap+"_32x.png", subTextureId , tileMap));
                    }
                }
                else if(currentMap == choco){
                    tileMap = 3;
                    if(textureId > 769 && textureId < 1281){
                        subTextureId = textureId - 768;
                        tileMap = 2;
                    }else if(textureId > 1281 && textureId < 2705){
                        subTextureId = textureId - 1280;
                        tileMap = 5;
                    }else if(textureId > 2705 && textureId < 2807){
                        subTextureId = textureId - 2704;
                        tileMap = 4;
                    }else if(textureId > 2807){
                        subTextureId = textureId - 2806;
                        tileMap = 1;
                    }
                    if(!textureMap4.containsKey(textureId)&&textureId!=0){
                        textureMap4.put(textureId,
                                SpriteLoader.loadImage("src\\resources\\atlas"+tileMap+"_32x.png", subTextureId , tileMap));
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
        for(Entity e: currentMap.getItemList()){
            if(e instanceof loveLetterEntity){
                if(e.isVisible()) {
                    g2.drawImage(e.getImage(), e.getX(), e.getY(), imageObserver);
                }
            }
        }
    }

    //renders all mobs
    public static void renderMobs(Graphics2D g2){
        for(Mob entity: currentMap.getNpcList()){
            if(entity instanceof Npc){
                g2.drawImage(entity.getImage(),entity.getX(),entity.getY(),imageObserver);
            }
        }
        g2.drawImage(player.getImage(),player.getX(),player.getY(),imageObserver);
    }
}
