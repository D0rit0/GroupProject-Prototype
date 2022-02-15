package imageRenderer;

import world.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {
    private static BufferedImage spriteSheet;
    private static int ssRow;
    private static int ssCol;
    public static Image loadImage(String path, int textureId){

        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage(path);
        }catch(IOException e){
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);

        setImagePoint(textureId);

        return ss.grabImage(ssCol, ssRow, 32,32);
    }
    private static void setImagePoint(int tileId){
        ssRow = tileId/96+1;
        ssCol = tileId - 96*(ssRow-1);
    }
}
