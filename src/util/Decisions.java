package util;

import entities.items.*;
import entities.mobs.Npc;

import static main.AppPanel.*;

public class Decisions {

    public static Cake cake = new Cake();
    public static CardGame cardGame = new CardGame();
    public static loveLetter loveLetter = new loveLetter();
    public static Receipt receipt = new Receipt();
    public static Envelope envelope = new Envelope();
    public static PinkCake pinkCake = new PinkCake();
    public static Flowers flowers = new Flowers();
    public static Book book = new Book();
    public static TeddyBear teddyBear = new TeddyBear();

    public static int x = 0;

    private static boolean questStep2 = false;

    public static boolean isQuestStep2(){
        return questStep2;
    }
    public static void setQuestStep2(boolean questStep){
        questStep2 = questStep;
    }

    private static boolean questStep3 = false;
    public static boolean isQuestStep3(){
        return questStep3;
    }
    private static boolean questStep4 = false;
    public static boolean isQuestStep4(){
        return questStep4;
    }


    public static void outCome(String d){
        if ("Merchant0".equals(d)) {
            player.getInventory().add(cardGame);
        } if ("Merchant1".equals(d)){
            player.getInventory().add(teddyBear);
        } if ("Merchant2".equals(d)){
            player.getInventory().add(book);
        }

        if ("Florist0".equals(d)) {
            player.getInventory().add(envelope);
            Npc e = florist.getNpcList().get(0);
            e.setDialogue(new String[]{"Really! that means so much thank you!", "I have lost an important letter.", "Can you find it?"});
            questStep3 = true;
        }

        if ("Baker0".equals(d)) {
            player.getInventory().add(cake);
            Npc b = choco.getNpcList().get(0);
            b.setDialogue(new String[]{"Such a sweetheart.", "It's for Richard.", "Here's that cake for delivery!"});
            questStep2 = true;
        }

        if (player.getInventory().contains(flowers)){
            x++;
        }
        if (player.getInventory().contains(pinkCake)){
            x++;
        }
        if (player.getInventory().contains(book)){
            x++;
        }
        if (player.getInventory().contains(teddyBear)){
            x++;
        }
        if (player.getInventory().contains(cardGame)){
            x++;
        }
        if (x >= 3) {
            questStep4 = true;
        }






    }

}
