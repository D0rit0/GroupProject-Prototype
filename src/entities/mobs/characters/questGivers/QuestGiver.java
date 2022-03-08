package entities.mobs.characters.questGivers;

import entities.mobs.Npc;
import main.DialogueBox;

abstract public class QuestGiver extends Npc {
    public QuestGiver(int x, int y, String name) {
        super(x, y, name);

        walkState1=1;
        walkState2=3;
        restState=2;
        leftFace=2;
        rightFace=3;
        centerFace=1;
        rearFace=4;

        interactable = true;
    }
}
