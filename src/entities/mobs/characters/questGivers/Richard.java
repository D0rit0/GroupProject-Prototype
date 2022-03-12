package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Richard extends QuestGiver{
    public Richard(int x, int y, String name) {

        super(x, y, "Richard");

        dialogue = new DialogueBox(new String[]{"It's a beautiful day today.", "I like Valentine's Day.", "Hello."}, this);

        loadImage("src\\resources\\Male\\Male 06-3.png");

    }
    public void dialogueCheck() {

    }
}
