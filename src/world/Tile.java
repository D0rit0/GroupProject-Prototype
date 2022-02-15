package world;

import imageRenderer.MapLoader;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {
    protected int tileId;
    protected int x;
    protected int y;
    protected int width = 32;
    protected int height = 32;
    protected BufferedImage spriteSheet=null;
    protected int ssCol= 1, ssRow=1;
    protected boolean collideable;
    protected boolean isVisible = false;
    public Image image;
    protected boolean imageLoaded = false;

    public Tile(int x, int y, int tileId){
        this.tileId = tileId;
        this.x=x;
        this.y=y;
        if(x < 832 && -32 < x
        && y < 832 && -32 < y){
            image= MapLoader.textureMap.get(tileId);
            imageLoaded=true;
            isVisible=true;
        }
    }
    public void unloadImage(){
        image=null;
    }
    public boolean isImageLoaded(){
        return imageLoaded;
    }


    public Image getImage(){
        return image;
    }
    public void setImage(Image image){
        this.image=image;
    }
    public boolean isVisible(){
        return isVisible;
    }
    public void setVisible(Boolean isVisible){
        this.isVisible=isVisible;
    }
    public int getTileId(){
        return tileId;
    }
    public int getSsCol(){
        return ssCol;
    }
    public int getSsRow(){
        return ssRow;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setY(int increment){
        y+=increment;
    }
    public void setX(int increment){
        x+=increment;
    }

    public void setImageLoaded(boolean isImageLoaded) {
        this.imageLoaded=isImageLoaded;
    }
}
