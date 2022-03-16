package entities.mobs.characters.questGivers;

import entities.mobs.Npc;
import main.DialogueBox;
import static main.AppPanel.overWorld;
import static main.AppPanel.player;
import static util.Decisions.*;

public class Baker extends QuestGiver {
    public Baker(int x, int y, String name) {

        super(x, y, "Baker");

        dialogue = new DialogueBox(new String[]{"Hello, I am leaving for a delivery.",
                "Actually... if you would deliver this on", "my behalf, I can help you straight away!", "Can you help me?", "Well, have a good day."},
                this, "Can you help me?", new String[]{"Yes", "No"});

        loadImage("src\\resources\\Female\\Female 19-1.png");

    }

    public void dialogueCheck() {
        if (player.getInventory().contains(receipt)) {
            setDialogue(new String[]{"Thank you! Here's a pink Cake on the house."});
            player.getInventory().add(pinkCake);
            player.getInventory().remove(receipt);
        } else if (player.getInventory().contains(cake)) {
            setDialogue(new String[]{"Ok see you when you get back."});
        } else if (player.getInventory().contains(pinkCake)){
            setDialogue(new String [] {"Happy Valentine's day!"});
        }

    }

}
