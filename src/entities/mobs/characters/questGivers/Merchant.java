package entities.mobs.characters.questGivers;

import main.DialogueBox;
import util.Decisions;

import static main.AppPanel.player;
import static util.Decisions.*;
import static util.Decisions.pinkCake;

public class Merchant extends QuestGiver{
    public Merchant(int x, int y, String name) {

        super(x, y, "Merchant");

        dialogue = new DialogueBox(new String[] {"I have many wares for sale.", "What would you like to purchase?", "Thank you for your business!"},
                this, "What would you like to purchase?", new String[] {"Card game", "Teddy bear", "Book"});

        loadImage("src\\resources\\Male\\Male 12-1.png");
    }
    public void dialogueCheck() {
        if (player.getInventory().contains(book)) {
            setDialogue(new String[]{"Thank you for your business."});
        } else if (player.getInventory().contains(teddyBear)) {
            setDialogue(new String[]{"Have a nice day."});
        } else if (player.getInventory().contains(cardGame)){
            setDialogue(new String [] {"Happy Valentine's day!"});
        }

    }
}
