package entities.Scenery.groundTypes;

import entities.Scenery.Scenery;

public abstract class Ground extends Scenery {
    private int tileID;
    public Ground(int x, int y){
        super(x,y);
    }
    public int getTileID(){
        return tileID;
    }
}
