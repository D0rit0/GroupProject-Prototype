package entities.mobs;

import main.AppPanel;
import main.DialogueBox;

import static main.AppPanel.gameState;
import static main.AppPanel.player;

abstract public class Npc extends Mob{
    public DialogueBox dialogue;
    private final String name;

    public Npc(int x, int y, String name) {
        super(x, y);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void interact(){
        gameState = AppPanel.dialogue;
        player.currentDialogue = dialogue;

        int dx = Math.abs(player.getX()-x);
        int dy = Math.abs(player.getY()-y);
        if(dy > dx){
            if(player.getY() > y){
                ssRow = 1;
            }else ssRow = 4;
        }else{
            if(player.getX() > x){
                ssRow = 3;
            }else ssRow = 2;
        }
        ssCol=2;


        changeImage(ssCol,ssRow);
    }

    public void setDialogue(String [] dialogueText){
        dialogue.changeDialogueText(dialogueText);
    }
}
