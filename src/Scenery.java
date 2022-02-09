import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Scenery extends Entity {
    protected int ssCol = 1;
    protected int ssRow = 1;
    protected BufferedImage spriteSheet = null;
    Scenery(int x, int y){
        super(x,y);
    }
    Scenery(int x, int y, boolean isVisible){
        super(x,y,isVisible);
    }

    private int changeIn(int p1, int p2){
        return p2 - p1;
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

    public void checkCollide(){
        Rectangle r1 = getBounds();
        Rectangle r2 = AppPanel.player.getBounds();
        /*if(r1.intersects(r2)){
            if(AppPanel.playerController.>=0 && AppPanel.playerController.velocityY >0 &&
                    changeIn(getLeft(), AppPanel.player.getRight())>changeIn(AppPanel.player.getBottom(), getTop())){
                //AppPanel.player.grounded=true;
                AppPanel.playerController.velocityY=0;
                AppPanel.player.setBottom(getTop());
                System.out.println("collide1");
            }else if(AppPanel.playerController.velocityX>=0 && AppPanel.playerController.velocityY >0 &&
                    changeIn(getLeft(), AppPanel.player.getRight())<changeIn(AppPanel.player.getBottom(), getTop())) {
                //AppPanel.player.moving=false;
                AppPanel.playerController.velocityX = 0;
                AppPanel.player.setRight(getLeft());
                System.out.println("collide2");
            }else if(AppPanel.playerController.velocityX<=0 && AppPanel.playerController.velocityY>0&&
                    changeIn(AppPanel.player.getLeft(),getRight())>changeIn(AppPanel.player.getBottom(), getTop())){
                //AppPanel.player.grounded=true;
                AppPanel.playerController.velocityY=0;
                AppPanel.player.setBottom(getTop());
                System.out.println("collide3");
            }else if(AppPanel.playerController.velocityX<=0 && AppPanel.playerController.velocityY>0&&
                    changeIn(AppPanel.player.getLeft(),getRight())<changeIn(AppPanel.player.getBottom(), getTop())){
                AppPanel.player.moving=false;
                AppPanel.playerController.velocityX = 0;
                AppPanel.player.setRight(getLeft());
                System.out.println("collide4");
            }if(AppPanel.playerController.velocityX>0 && AppPanel.playerController.velocityY ==0){
                AppPanel.player.setRight(getLeft());
            }else if(AppPanel.playerController.velocityX<0 && AppPanel.playerController.velocityY ==0){
                AppPanel.player.setLeft(getRight());
            }*/
            /*if(AppPanel.playerController.velocityY >= 0 &&
                    AppPanel.playerController.velocityX >= 0){
                if ((getLeft() - AppPanel.player.getRight()) > (-getTop() + AppPanel.player.getBottom())) {
                    AppPanel.player.grounded=true;
                    AppPanel.playerController.velocityY=0;
                    AppPanel.player.setBottom(getTop());
                } else {
                    AppPanel.player.moving=false;
                    AppPanel.playerController.velocityX=0;
                    AppPanel.player.setRight(getLeft());
                }

            }else if (AppPanel.playerController.velocityY >= 0 &&
                    AppPanel.playerController.velocityX <= 0) {
                if ((AppPanel.player.getLeft() - getRight()) > (-getTop() + AppPanel.player.getBottom())) {
                    AppPanel.player.grounded=true;
                    AppPanel.playerController.velocityY=0;
                    AppPanel.player.setBottom(getTop());
                } else {
                    AppPanel.player.moving=false;
                    AppPanel.playerController.velocityX=0;
                    AppPanel.player.setLeft(getRight());
                }
            }
            else if(AppPanel.playerController.velocityY <= 0 &&
                    AppPanel.playerController.velocityX >= 0){
                if ((getLeft() - AppPanel.player.getRight()) > (getBottom() - AppPanel.player.getTop())) {
                    AppPanel.player.setTop(getBottom());
                } else {
                    AppPanel.player.moving=false;
                    AppPanel.playerController.velocityX=0;
                    AppPanel.player.setRight(getLeft());
                }

            }else if (AppPanel.playerController.velocityY <= 0 &&
                    AppPanel.playerController.velocityX <= 0) {
                if ((AppPanel.player.getLeft() - getRight()) > (getBottom() - AppPanel.player.getTop())) {
                    AppPanel.player.setTop(getBottom());
                } else {
                    AppPanel.player.moving=false;
                    AppPanel.playerController.velocityX=0;
                    AppPanel.player.setLeft(getRight());

                }
            }*/
        //}
    }
}
