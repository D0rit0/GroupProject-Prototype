import javax.swing.*;
import java.awt.*;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
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
    protected void loadImage(String Image){
        ImageIcon ii = new ImageIcon(Image);
        this.image = ii.getImage();
        getImageDimensions();
    }
    public void changeImage(String newImage){
        loadImage(newImage);
        getImageDimensions();
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

    public abstract void checkCollide();


}
