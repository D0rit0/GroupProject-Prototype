package entities.items;

import entities.Entity;
import util.Decisions;

import static main.AppPanel.player;

public class loveLetterEntity extends Entity {
    public loveLetterEntity(int x, int y) {
        super(x, y);
        loadImage("src\\resources\\loveLetter.png");
        interactable = true;
    }
    public void interact(){
        if(interactable) {
            player.getInventory().add(Decisions.loveLetter);
            interactable = false;
            isVisible = false;
        }

    }
}
