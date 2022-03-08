package entities.mobs.characters.questGivers;

import main.DialogueBox;

public class Florist extends QuestGiver{
    public Florist(int x, int y, String name) {

        super(x, y, "Florist");

        dialogue= new DialogueBox( new String[] {"Hello", "I would love to help you with a bouquet, but I need to resolve a personal matter before I can help you."}, this);

        loadImage("src\\resources\\Male\\Male 13-2.png");

    }
    public void talk(){ }
}
