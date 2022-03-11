package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Florist extends QuestGiver{
    public Florist(int x, int y, String name) {

        super(x, y, "Florist");
        dialogue = new DialogueBox(new String[] {"Hello.", "I would love to help you pick a bouquet ...", "But I am dealing with a personal matter.", "Can you help me?"},
                this, "Can you help me?", new String[] {"Yes", "No"});

        loadImage("src\\resources\\Male\\Male 13-2.png");

    }
}
