package util;

import entities.items.Cake;
import entities.items.CardGame;
import entities.items.Envelope;
import entities.mobs.Npc;
import entities.mobs.characters.questGivers.Merchant;
import entities.mobs.characters.questGivers.QuestGiver;
import main.AppPanel;

import static main.AppPanel.*;

public class Decisions {


    public static void outCome(String d){
        if ("Merchant0".equals(d)) {
            player.getInventory().add(new CardGame());
            System.out.print("h");
            currentMap = florist;
        }
        if ("Florist0".equals(d)) {
            player.getInventory().add(new Envelope());
            System.out.print("y");
            Npc e = (Npc)mobList.get(1);
            e.setDialogue(new String []{"Really! that means so much thank you!"});
        }if ("Florist1".equals(d)) {
            System.out.print("y");
        }

        if ("Baker0".equals(d)) {
            player.getInventory().add(new Cake());
            System.out.print("y");
            Npc e = (Npc)mobList.get(1);
            e.setDialogue(new String []{"Such a sweetheart."});
        } if ("Baker1".equals(d)) {
            System.out.print("y");
        }

    }

}
