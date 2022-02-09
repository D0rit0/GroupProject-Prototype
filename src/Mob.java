import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Mob extends Entity {
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

    private void animate(){
        if(ssCol == 2){
            ssCol=1;
        }else if (ssCol==1){
            ssCol=3;
        }else if(ssCol==3){
            ssCol=2;
        }
        changeImage(ssCol,ssRow);
    }

}
