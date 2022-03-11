package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Baker extends QuestGiver {
    public Baker(int x, int y, String name) {

        super(x, y, "Baker");

        dialogue = new DialogueBox(new String[] {"Hello, I am leaving for a delivery.",
                "Actually... if you would deliver this on" ,"my behalf, I can help you straight away!"},
                this, "Can you help me?", new String[] {"Yes", "No"});

        loadImage("src\\resources\\Female\\Female 19-1.png");

    }
}
