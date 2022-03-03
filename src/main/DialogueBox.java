package main;

import java.awt.*;

import static main.AppPanel.gameState;
import static main.AppPanel.running;

public class DialogueBox {

    private final int x;
    private final int y;
    private boolean shown;
    //private boolean
    private int questionSlide;
    private final String name;
    private int text = 0;
    private String[] dialogue;


    public DialogueBox(int x, int y, String[] text ,String name){
        this.x = x;
        this.y = y;
        this.name=name;

        dialogue = text;
        shown = true;
    }

    public void changeDialogueText(String[] dialogue){
        this.dialogue = dialogue;
        text = 0;
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
        int nameX = x+5;
        int nameY = y+20;
        Color beige = new Color(111,78,55);
        Color transparentWhite = new Color(234,221,202,75);
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        g2.setColor(beige);
        g2.fillRoundRect(x-5,y-5,410, 78, 20,20);
        g2.setColor(transparentWhite);
        g2.fillRoundRect(x,y,400,68, 15,15);
        g2.setColor(beige);
        g2.setColor(Color.BLACK);
        g2.drawString(name,nameX+1,nameY+1);
        g2.setColor(beige);
        g2.drawString(name,nameX,nameY);
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 19));
        g2.setColor(Color.BLACK);
        g2.drawString(dialogue[text],x+25,y+43);
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        g2.setColor(beige);
        g2.drawString("press space...",x+315,y+65);
        //if()
    }




}
