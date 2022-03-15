package entities.mobs.characters.questGivers;

import entities.mobs.Npc;
import main.DialogueBox;
import util.Decisions;

import static main.AppPanel.florist;
import static main.AppPanel.player;

public class Florist extends QuestGiver{
    public Florist(int x, int y, String name) {

        super(x, y, "Florist");
        dialogue = new DialogueBox(new String[] {"Hello.", "I would love to help you pick a bouquet ...", "But I am dealing with a personal matter.",
                "Can you help me?", "Aww Shucks..."},
                this, "Can you help me?", new String[] {"Yes", "No"});

        loadImage("src\\resources\\Male\\Male 13-2.png");


    }
    public void dialogueCheck() {
        if(Decisions.isQuestStep2()){
            if(player.getInventory().contains("loveLetter")) {
                player.getInventory().remove("Envelope");
                player.getInventory().remove("loveLetter");
                Npc e = florist.getNpcList().get(0);
                e.setDialogue(new String [] {"You didn't read this did you ...", "Here's your bouquet. Get out of here."});
            }


        }

    }
}
