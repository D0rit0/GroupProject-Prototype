package entities.mobs;

import main.DialogueBox;

public class PeacefulAnimal extends Npc {
    public PeacefulAnimal(int x, int y, String type) {
        super(x, y, "peacefulAnimal",new DialogueBox(200,200, new String[]{"Hello my name is Jefferson", "Hello World 2"}, "Jefferson"));
        loadImage("src\\resources\\Animal\\"+type+" 01-1.png");
    }
    public void pet(){}
    public void feed(){}
}
