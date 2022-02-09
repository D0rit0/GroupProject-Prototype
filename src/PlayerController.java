import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayerController implements Controller{
    protected int mouseX;
    protected int mouseY;
    private int dx = 0;
    private int dy = 0;

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


    KeyAdapter MyKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
                if(key == KeyEvent.VK_A){
                    dx = -2;
                    player.moving = true;
                }
                else if(key == KeyEvent.VK_D){
                    dx = 2;
                    player.moving = true;
                    System.out.println("d");
                }
                if(key == KeyEvent.VK_W) {
                    dy = -2;
                    player.moving = true;
                }
                else if(key == KeyEvent.VK_S) {
                    dy = 2;
                    player.moving = true;
                    System.out.println("s");
                }
            }
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
        }
    };
    public void move(){
        if(dy != 0 && dx !=0){
            dx /=2;
            dy /=2;
        }
        player.x += dx;
        player.y += dy;
    }
}

