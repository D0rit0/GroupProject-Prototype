import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage spriteSheet=null;
    protected int ssCol= 1, ssRow=1;
    protected boolean collideable;
    protected boolean isVisible;
    protected String imagePath;
    protected Image image;

    Entity(int x, int y){
        this.x = x;
        this.y = y;
        this.isVisible = true;

    }
    Entity(int x, int y, boolean isVisible){
        this.x = x;
        this.y = y;
        this.isVisible = isVisible;
    }

    protected void getImageDimensions(){
        this.width=image.getWidth(null);
        this.height=image.getHeight(null);
    }
    protected void loadImage(String path){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage(path);
        }catch(IOException e){
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.image = ss.grabImage(ssCol, ssRow, 32,32);
        getImageDimensions();
    }
    public void changeImage(int ssCol, int ssRow) {
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.image = ss.grabImage(ssCol, ssRow, 32,32);
    }

    public Image getImage(){
        return image;
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
    public int getLeft(){
        return x;
    }
    public int getRight(){
        return x+width;
    }
    public int getTop(){
        return y;
    }
    public int getBottom(){
        return y+height;
    }
    public Point getCenter(){
        return new Point((int)(x+width*.5), (int)(y+height*.5));
    }

    public void checkCollide(){}


}
