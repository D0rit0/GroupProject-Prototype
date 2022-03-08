package entities.mobs.characters;

import entities.mobs.Npc;
import entities.mobs.characters.questGivers.QuestGiver;
import main.DialogueBox;

public class Crush extends QuestGiver {
    public Crush(int x, int y, String name) {
        super(x, y,"<3");
        dialogue = new DialogueBox(new String[] {"Hi..."}, this);

        loadImage("src\\resources\\Japanese school characters\\school uniform 1\\su1 Student fmale 08.png");
    }
}
