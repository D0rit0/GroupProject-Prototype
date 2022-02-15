package entities.scenery.groundTypes;

import entities.scenery.Scenery;

public abstract class Ground extends Scenery {
    private int tileID;
    public Ground(int x, int y){
        super(x,y);
    }
    public int getTileID(){
        return tileID;
    }
}
