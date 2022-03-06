package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Chocolatier extends QuestGiver {
    public Chocolatier(int x, int y, String name) {

        super(x, y, "Chocolatier", new DialogueBox(200, 200, new String[] {"Hello"} , "Chocolatier"));
        loadImage("src\\resources\\Female\\Female 19-1.png");
    }
}
