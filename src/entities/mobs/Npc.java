package entities.mobs;

import main.AppPanel;
import main.DialogueBox;

import static main.AppPanel.gameState;
import static main.AppPanel.player;

public class Npc extends Mob{
    public DialogueBox dialogue;
    private final String name;

    public Npc(int x, int y, String name, DialogueBox dialogue) {
        super(x, y);
        this.name = name;
        this.dialogue=dialogue;
        if(dialogue != null)
            interactable = true;
    }

    public String getName(){
        return name;
    }

    public void interact(){
        gameState = AppPanel.dialogue;
        player.currentDialogue = dialogue;
    }

    public void setDialogue(String [] dialogueText){
        dialogue.changeDialogueText(dialogueText);
    }
}
