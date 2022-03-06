package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Merchant extends QuestGiver{
    public Merchant(int x, int y, String name) {

        super(x, y, "Merchant", new DialogueBox(200, 200, new String[] {"I have many wares for sale"}, "Merchant"));
        loadImage("src\\resources\\Male\\Male 12-1.png");
    }
}
