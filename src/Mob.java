import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Mob extends Entity {
    protected int walkState1;
    protected int walkState2;
    protected int restState;
    protected int leftFace;
    protected int centerFace;
    protected int rightFace;
    protected int rearFace;

    protected boolean moving = false;
    protected boolean grounded = true;
    protected Timer animationTimer = new Timer(150, e -> animate());

    public Mob(int x, int y){
        super(x,y);
    }
    public Mob(int x, int y, boolean isVisible){
        super(x,y,isVisible);
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

    protected void animate(){
        if(ssCol == restState){
            ssCol=walkState1;
        }else if (ssCol==walkState1){
            ssCol=walkState2;
        }else if(ssCol==walkState2){
            ssCol=restState;
        }
        changeImage(ssCol,ssRow);
    }

}
