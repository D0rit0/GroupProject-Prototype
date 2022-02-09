import java.awt.*;
import java.util.ArrayList;

public class PlayerCharacter extends Mob{
    private final String imagePath = "src\\resources\\Male\\Male 01-1.png";
    //when this program is ran... an instance of PlayerCharacter is created
    // using a private constructor allowing for only one instance to be created
    private static PlayerCharacter instance;
    private final PlayerController playerController = AppPanel.getPlayerController();
    //InputController playerController = new InputController();

    private PlayerCharacter(int x, int y){
        super(x-32,y);
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
}
