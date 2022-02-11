package entities.Scenery;

import entities.Entity;

public abstract class Scenery extends Entity {
    protected Scenery(int x, int y){
        super(x,y);
    }
    Scenery(int x, int y, boolean isVisible){
        super(x,y,isVisible);
    }

    private int changeIn(int p1, int p2){
        return p2 - p1;
    }
}
