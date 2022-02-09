import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Mob extends Entity {
    private BufferedImage spriteSheet =null;
    protected int ssCol = 2;
    protected int ssRow = 1;
    protected boolean moving = false;
    protected boolean grounded = true;

    public Mob(int x, int y){
        super(x,y);
    }
    public Mob(int x, int y, boolean isVisible){
        super(x,y,isVisible);
    }

    public void loadImage(String path){
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

    public boolean isMoving(){
        return moving;
    }
    public void setLeft(int newLeft){
        x = newLeft;
    }
    public void setRight(int newRight){
        x = newRight - width;
    }
    public void setTop(int newTop){
        y = newTop;
    }
    public void setBottom(int newBottom){
        y= newBottom-height;
    }

}
