package main;

import entities.mobs.Npc;

import java.awt.*;
import java.util.Objects;

import static main.AppPanel.gameState;
import static main.AppPanel.running;

public class DialogueBox {

    private boolean shown;
    //private boolean
    private final Npc host;
    private boolean questionState = false;
    private boolean hasQuestion = false;
    private final String name;
    private int text = 0;
    private Question question = null;
    private String[] dialogue;


    public DialogueBox(String[] text, Npc host){

        this.host = host;
        name = host.getName();

        dialogue = text;
        shown = true;
    }
    public DialogueBox(String[] text, Npc host, String question, String[] options){

        this.host = host;
        name = host.getName();

        this.question = new Question(question,options);
        hasQuestion = true;

        dialogue = text;
        shown = true;
    }

    public void changeDialogueText(String[] dialogue){
        this.dialogue = dialogue;
        text = 0;
    }

    public void nextText(){
        if(hasNext()) {
            if(hasQuestion) {
                if (Objects.equals(dialogue[text + 1], question.question))
                    questionState = true;
            }
            else questionState = false;
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

    public void addQuestion(Question question){
        this.question=question;
    }

    public boolean isQuestionState(){
        return questionState;
    }
    public Question question(){
        return question;
    }
    public void setQuestionState(boolean h){
        questionState = h;
    }

    class Question{
        String question;
        String[] options;
        int numOptions;
        int selected = 0;

        public Question(String question, String[] options){
            this.question = question;
            this.options = options;
            numOptions = options.length;
        }

        public void next() {
            if(selected<numOptions-1)
                selected++;
            else selected = 0;
        }

        public void previous(){
            if(selected>0){
                selected--;
            }
            else selected = numOptions-1;
        }

        public String decision(){
            questionState = false;
            nextText();
            return host.getName()  +selected;
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
        if(questionState){
            g2.setColor(beige);
            g2.fillRoundRect(x +410, y-5,150, 156, 20,20);
            g2.setColor(transparentWhite);
            g2.fillRoundRect(x+415, y,140,146, 15,15);
            g2.setColor(beige);
            g2.setColor(Color.black);
            g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
            g2.drawString("<",x + 520, 20+y + question.selected*20);
            for(int i = 0; i < question.numOptions; i++){
                g2.drawString(question.options[i], x + 420, 20+y + i*20);
            }
        }
    }




}
