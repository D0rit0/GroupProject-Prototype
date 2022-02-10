import java.awt.*;
import java.util.ArrayList;

public class PlayerCharacter extends Mob{
    //when this program is ran... an instance of PlayerCharacter is created
    // using a private constructor allowing for only one instance to be created
    private static PlayerCharacter instance;
    private final PlayerController playerController = AppPanel.getPlayerController();
    //InputController playerController = new InputController();

    private PlayerCharacter(int x, int y){
        super(x-32,y);
        super.ssRow=12;
        super.ssCol=26;
        super.imagePath="src\\resources\\atlas_32x.png";
        walkState1=25;
        walkState2=27;
        restState=26;
        //String imagePath = "src\\resources\\Male\\Male 01-1.png";
        loadImage(imagePath);
        grounded = false;
    }
    //this method allows for other classes to access this single instance of PlayerCharacter
    //it is also in a synchronized block creating a thread safe environment
    public synchronized static PlayerCharacter getInstance(){
        if (instance == null){
            instance = new PlayerCharacter(400,400);
        }
        return instance;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x+8,y+16,width-12,height-16);
    }
}
