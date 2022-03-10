package util;

import entities.items.CardGame;
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

    }

}
