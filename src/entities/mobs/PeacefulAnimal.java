package entities.mobs;

import main.DialogueBox;

public class PeacefulAnimal extends Npc {
    public PeacefulAnimal(int x, int y, String type) {
        super(x, y, "peacefulAnimal");

        dialogue = new DialogueBox(new String[]{"Hello my name is Jefferson", "Hello World 2"}, this);

        loadImage("src\\resources\\Animal\\"+type+" 01-1.png");

        walkState1=1;
        walkState2=3;
        restState=2;
        leftFace=2;
        rightFace=3;
        centerFace=1;
        rearFace=4;

        interactable = true;
    }
    public void pet(){}
    public void feed(){}
}
