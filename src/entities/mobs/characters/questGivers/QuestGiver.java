package entities.mobs.characters.questGivers;

import entities.mobs.Npc;
import main.DialogueBox;

abstract public class QuestGiver extends Npc {
    public QuestGiver(int x, int y, String name, DialogueBox dialogue) {
        super(x, y, name, dialogue);
    }
}
