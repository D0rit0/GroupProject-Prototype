package world;

import java.awt.*;

import static util.imageRenderer.GraphicsHandler.*;


public class Tile {
    protected int tileId;
    protected int x;
    protected int y;
    protected int width = 32;
    protected int height = 32;
    protected boolean collideable;
    protected boolean isVisible = false;
    public Image image;
    protected boolean imageLoaded = false;

    public Tile(int x, int y, int tileId, String map){
        this.tileId = tileId;
        this.x=x;
        this.y=y;
        if(x < 832 && -32 < x
        && y < 832 && -32 < y){
            if(map.equals("src\\world\\Team Valentine World Map.tmx")) {
                image = textureMap.get(tileId);
            }else if(map.equals("src\\world\\generalStore.tmx")){
                image = textureMap2.get(tileId);
            }else if(map.equals("src\\world\\Florist.tmx")){
                image = textureMap3.get(tileId);
            }else if(map.equals("src\\world\\Chocolate_shop.tmx")){
                image = textureMap4.get(tileId);
            }
            /*imageLoaded=true;
            isVisible=true;*/
        }
        imageLoaded=true;
        isVisible=true;
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
    public boolean isCollideable(){
        return collideable;
    }
    public void setIsCollideable(boolean collideable){
        this.collideable = collideable;
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
