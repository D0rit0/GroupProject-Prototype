package util.imageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {
    private static BufferedImage spriteSheet;
    private static int ssRow;
    private static int ssCol;
    public static Image loadImage(String path, int textureId, int tileMap){

        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage(path);
        }catch(IOException e){
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);

        if(tileMap == 2){
            setImagePoint2(textureId);
        }else if (tileMap ==3) {
            setImagePoint3(textureId);
        }
        else setImagePoint(textureId);

        return ss.grabImage(ssCol, ssRow, 32,32);
    }
    private static void setImagePoint(int tileId){
        ssRow = tileId/96+1;
        ssCol = tileId - 96*(ssRow-1);
    }
    private static void setImagePoint2(int tileId){
        ssRow = tileId/32+1;
        ssCol = tileId - 32*(ssRow-1);
    }
    private static void setImagePoint3(int tileId){
        ssRow = tileId/48+1;
        ssCol = tileId - 48*(ssRow-1);
    }
}
