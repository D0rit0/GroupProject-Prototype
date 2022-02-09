import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayerController implements Controller{
    protected int mouseX;
    protected int mouseY;
    private static int dx = 0;
    private static int dy = 0;


    private final PlayerCharacter player = PlayerCharacter.getInstance();


    MouseInputAdapter MyMouseAdapter = new MouseInputAdapter(){

        public void mouseMoved(MouseEvent e){
            mouseX = e.getX();
            mouseY = e.getY();
        }
        public void mouseEntered(MouseEvent e){
            System.out.println("mouse Entered");
        }
    };
    public static int getDx(){
        return dx;
    }
    public static int getDy(){
        return dy;
    }


    KeyAdapter MyKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
                if(key == KeyEvent.VK_A){
                    dx = -2;
                    player.changeImage(player.ssCol, player.ssRow=2);
                    player.moving = true;
                }
                else if(key == KeyEvent.VK_D){
                    dx = 2;
                    player.changeImage(player.ssCol,player.ssRow=3);
                    player.moving = true;
                    System.out.println("d");
                }
                if(key == KeyEvent.VK_W) {
                    player.changeImage(player.ssCol,player.ssRow=4);
                    dy = -2;
                    player.moving = true;
                }
                else if(key == KeyEvent.VK_S) {
                    player.changeImage(player.ssCol, player.ssRow=1);
                    dy = 2;
                    player.moving = true;
                    System.out.println("s");
                }
                player.animationTimer.start();

            }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_A, KeyEvent.VK_D -> {
                    dx=0; player.moving = false;
                }
                case KeyEvent.VK_W, KeyEvent.VK_S -> {
                    dy = 0; player.moving = false;
                }
            }
            player.animationTimer.stop();
            player.changeImage(2, player.ssRow);
        }
    };
    public void move(){
        if(dy != 0 && dx !=0){
            player.x += dx/2;
            player.y += dy/2;
        }else {
            player.x += dx;
            player.y += dy;
        }
    }

    public void setDx(int newDx) {
        dx=newDx;
    }
    public void setDy(int newDy) {
        dx=newDy;
    }
}

