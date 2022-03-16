package entities.mobs;

import entities.items.QuestItem;
import main.DialogueBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerCharacter extends Mob {
    //when this program is ran... an instance of entities.mobs.PlayerCharacter is created
    // using a private constructor allowing for only one instance to be created
    private String playerName;
    private static PlayerCharacter instance;
    private ArrayList<QuestItem> inventory =  new ArrayList<>();
    public DialogueBox currentDialogue;

    private PlayerCharacter(int x, int y){
        super(x,y);
        super.ssRow=12;
        super.ssCol=26;
        super.imagePath="src\\resources\\atlas1_32x.png";
        walkState1=25;
        walkState2=27;
        restState=26;
        leftFace=13;
        rightFace=14;
        centerFace=12;
        rearFace=15;
        loadImage(imagePath);
        grounded = false;
    }
    //found:https://www.javatpoint.com/singleton-design-pattern-in-java
    //this method allows for other classes to access this single instance of entities.mobs.PlayerCharacter
    //it is also in a synchronized block creating a thread safe environment
    public synchronized static PlayerCharacter getInstance(){
        if (instance == null){
            instance = new PlayerCharacter(400,400);
        }
        return instance;
    }
    public String getPlayerName(){
        return playerName;
    }

    public ArrayList<QuestItem> getInventory() {
        return inventory;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x+8,y+16,width-12,height-16);
    }
}
