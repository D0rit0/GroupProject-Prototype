package main;

import entities.mobs.Npc;

import java.awt.*;

import static main.AppPanel.gameState;
import static main.AppPanel.running;

public class DialogueBox {

    private boolean shown;
    //private boolean
    private Npc host;
    private int questionSlide;
    private boolean questionState = false;
    private boolean hasQuestion = false;
    private final String name;
    private int text = 0;
    private String[] dialogue;


    public DialogueBox(String[] text, Npc host){

        this.host = host;
        name = host.getName();

        dialogue = text;
        shown = true;
    }
    /*public DialogueBox(String[] text, Npc host, Question question){

    }*/

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
        host.changeImage(host.setSsRow(host.getRestState()),host.setSsCol(host.getCenterFace()));
        shown = false;
    }

    public void setShown(boolean shown){
        this.shown = shown;
    }

    class Question{
        String question;
        String[] options;
        int numOptions;
        int selected = 0;

        Question(String question, String[] options){
            this.question = question;
            this.options = options;
            numOptions = options.length;
        }

        public void next() {
            if(selected<numOptions-1)
                selected++;
            else selected = 0;
        }

        public int decision(){
            return selected;
        }
    }

    public void renderDialogueBox(Graphics2D g2){
        int x = 200;
        int nameX = x +5;
        int y = 200;
        int nameY = y +20;

        Color beige = new Color(111,78,55);
        Color transparentWhite = new Color(234,221,202,75);

        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        g2.setColor(beige);
        g2.fillRoundRect(x -5, y -5,410, 78, 20,20);
        g2.setColor(transparentWhite);
        g2.fillRoundRect(x, y,400,68, 15,15);
        g2.setColor(beige);
        g2.setColor(Color.BLACK);
        g2.drawString(name,nameX+1,nameY+1);
        g2.setColor(beige);
        g2.drawString(name,nameX,nameY);
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 19));
        g2.setColor(Color.BLACK);
        g2.drawString(dialogue[text], x +25, y +43);
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        g2.setColor(beige);
        g2.drawString("press space...", x +315, y +65);
        //if()
    }




}
