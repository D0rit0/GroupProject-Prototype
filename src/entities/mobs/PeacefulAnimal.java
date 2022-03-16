package entities.mobs;

import main.DialogueBox;

public class PeacefulAnimal extends Npc {
    public PeacefulAnimal(int x, int y, String type) {
        super(x, y, "Cat");

        dialogue = new DialogueBox(new String[] {"the cat meows at you expectantly.", "MEOW", "Pet the cat?", "meow"},
                this, "Pet the cat?", new String[] {"Yes", "No"});

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

}
