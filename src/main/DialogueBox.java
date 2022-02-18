package main;

import java.awt.*;

import static main.AppPanel.gameState;
import static main.AppPanel.dialogue;
import static main.AppPanel.running;

public class DialogueBox {

    private final int x;
    private final int y;
    private boolean shown;
    private int text = 0;
    private final String[] dialogue;


    public DialogueBox(int x, int y, String[] text){
        this.x = x;
        this.y = y;

        gameState=AppPanel.dialogue;
        dialogue = text;
        shown = true;
    }

    public void nextText(){
        if(hasNext()) {
            text++;
        }else{
            text = 0;
        }
    }

    public boolean hasNext(){
        return text < dialogue.length-1;
    }

    public void close(){
        gameState=running;
        shown = false;
    }

    public void setShown(boolean shown){
        this.shown = shown;
    }

    public void renderDialogueBox(Graphics2D g2){
        g2.drawString(dialogue[text],x,y);
    }




}
