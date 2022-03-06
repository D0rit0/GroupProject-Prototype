package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Florist extends QuestGiver{
    public Florist(int x, int y, String name) {

        super(x, y, "Florist", new DialogueBox(200, 200, new String[] {"Hello"} , "Florist"));
        loadImage("src\\resources\\Male\\Male 13-2.png");

    }
    public void talk(){ }
}
