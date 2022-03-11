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
            currentMap = market;
        }
        if ("Florist0".equals(d)) {
            player.getInventory().add(new Envelope());
            System.out.print("y");
            currentMap = market;
        }
        // these need to be changed probably but they are placeholders
        if ("Baker0".equals(d)) {
            player.getInventory().add(new Cake());
            System.out.print("y");
            currentMap = market;
        }

    }

}
