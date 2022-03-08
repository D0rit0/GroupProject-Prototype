package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Chocolatier extends QuestGiver {
    public Chocolatier(int x, int y, String name) {

        super(x, y, "Chocolatier");

        dialogue = new DialogueBox(new String[] {"Hello"}, this);

        loadImage("src\\resources\\Female\\Female 19-1.png");

    }
}
