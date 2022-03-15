package util;

import entities.items.*;
import entities.mobs.Npc;

import static main.AppPanel.*;

public class Decisions {

    public static Cake cake = new Cake();
    public static CardGame cardGame = new CardGame();
    public static Envelope loveLetter = new Envelope();
    public static Receipt receipt = new Receipt();
    public static PinkCake pinkCake = new PinkCake();

    private static boolean questStep2 = false;

    public static boolean isQuestStep2(){
        return questStep2;
    }

    private static boolean questStep3 = false;
    public static boolean isQuestStep3(){
        return questStep2;
    }


    public static void outCome(String d){
        if ("Merchant0".equals(d)) {
            player.getInventory().add(new CardGame());
            System.out.print("h");
            currentMap = choco;
        }
        if ("Florist0".equals(d)) {
            player.getInventory().add(new Envelope());
            Npc e = florist.getNpcList().get(0);
            e.setDialogue(new String []{"Really! that means so much thank you!", "I have lost an important letter.", "Can you find it?"});
        } if ("Florist1".equals(d)) {

        }// for when u turn in the quest item to florist.

        if ("Baker0".equals(d)) {
            player.getInventory().add(cake);
            Npc b = overWorld.getNpcList().get(1);
            b.setDialogue(new String []{"Such a sweetheart.", "Here's that cake for delivery."});
            questStep2 = true;
        } if ("Baker1".equals(d)) {
        }






    }

}
