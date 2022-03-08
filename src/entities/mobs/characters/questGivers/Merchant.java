package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Merchant extends QuestGiver{
    public Merchant(int x, int y, String name) {

        super(x, y, "Merchant");

        dialogue = new DialogueBox(new String[] {"I have many wares for sale.", "Would you like to browse?"}, this);

        loadImage("src\\resources\\Male\\Male 12-1.png");
    }
}
