package util;

import entities.items.Cake;
import entities.items.CardGame;
import entities.items.Envelope;
import entities.items.pinkCake;
import entities.mobs.Npc;
import entities.mobs.characters.questGivers.Florist;
import entities.mobs.characters.questGivers.Merchant;
import entities.mobs.characters.questGivers.QuestGiver;
import main.AppPanel;

import static main.AppPanel.*;

public class Decisions {

    static Cake cake = new Cake();
    static CardGame cardGame = new CardGame();
    static Envelope loverLetter = new Envelope();


    public static void outCome(String d){
        if ("Merchant0".equals(d)) {
            player.getInventory().add(new CardGame());
            System.out.print("h");
            currentMap = florist;
        }
        if ("Florist0".equals(d)) {
            player.getInventory().add(new Envelope());
            System.out.print("y");
            Npc e = florist.getNpcList().get(0);
            e.setDialogue(new String []{"Really! that means so much thank you!"});
        }if ("Florist1".equals(d)) {
            System.out.print("y");
        }
        if(player.getInventory().contains(loverLetter)) {
            player.getInventory().remove("Envelope");
            player.getInventory().remove(loverLetter);
            Npc e = florist.getNpcList().get(0);
            e.setDialogue(new String [] {"You didn't read this did you ...", "Here's your bouquet. Get out of here."});
        } // for when u turn in the quest item to florist.

        if ("Baker0".equals(d)) {
            player.getInventory().add(cake);
            System.out.print("y");
            Npc b = overWorld.getNpcList().get(1);
            b.setDialogue(new String []{"Such a sweetheart."});
        } if ("Baker1".equals(d)) {
            System.out.print("y");
        }
        if (player.getInventory().contains("Receipt")){
            Npc b = overWorld.getNpcList().get(1);
            b.setDialogue(new String [] {"Thank you!, here's a pink Cake on the house."});
            player.getInventory().add(new pinkCake()); // i dont think this will work but it is fixable
        }
        if (player.getInventory().contains(cake)){
            //richard gives u receipt for the delivery of cake and says
            //"Thanks. You know that girl in town? i think she likes reading."
            // player.getInventory().remove("Cake");
        }




    }

}
