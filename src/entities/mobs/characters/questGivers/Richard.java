package entities.mobs.characters.questGivers;

import entities.mobs.Npc;
import main.DialogueBox;
import util.Decisions;

import static main.AppPanel.overWorld;
import static main.AppPanel.player;
import static util.Decisions.*;

public class Richard extends QuestGiver{
    public Richard(int x, int y, String name) {

        super(x, y, "Richard");

        dialogue = new DialogueBox(new String[]{"Hello!","It's a beautiful day today.", "I like Valentine's Day."}, this);

        loadImage("src\\resources\\Male\\Male 06-3.png");

    }
    public void dialogueCheck() {
        if(Decisions.isQuestStep2()) {
            if(player.getInventory().contains(receipt)){
                setDialogue(new String[]{"You should head back to the bakery..."});
            }
            if(player.getInventory().contains(pinkCake)){
                setDialogue(new String[]{"That's a lovely cake..", "Is it for that girl in town?", "Well if it is...","I hear she like to read..."});
            }
            if (player.getInventory().contains(cake)) {
                player.getInventory().add(receipt);
                setDialogue(new String[]{"Wondrous. Here's proof of delivery.", "You know that girl in town?", "I hear she likes to read.", "Happy Valentine's Day."});
                player.getInventory().remove(cake);
            }
        }

    }
}
