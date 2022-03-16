package entities.mobs.characters;

import entities.mobs.Npc;
import entities.mobs.characters.questGivers.QuestGiver;
import main.DialogueBox;
import util.Decisions;

import static main.AppPanel.player;
import static util.Decisions.*;


public class Crush extends QuestGiver {
    public Crush(int x, int y, String name) {
        super(x, y, "<3");
        dialogue = new DialogueBox(new String[]{"Hi..."}, this);

        loadImage("src\\resources\\Japanese school characters\\school uniform 1\\su1 Student fmale 08.png");
    }

    public void dialogueCheck() {
        if (Decisions.isQuestStep4()) {
            if (player.getInventory().contains(book)) {
                setDialogue(new String[]{"Wow... You didn't have to do this", "Thank you so much!",
                        "*your crush steps forward and...", "gives you a big kiss on the forehead.*", "<3 <3 <3"});

            }
            if (player.getInventory().contains(teddyBear)) {
                setDialogue(new String[] {"This is nice."});
            }
            if (player.getInventory().contains(cardGame)){
                setDialogue(new String [] {"Oh wow... how fun...", "Would you like to play this together?"});

            }

        }
    }
}
