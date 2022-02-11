package entities.Scenery.Buildings;

import entities.Scenery.Scenery;

public abstract class Building extends Scenery {
    protected int doorX,doorY;
    protected int doorW,doorH;
    protected int buildingW, buildH;
    Building(int x, int y) {
        super(x, y);
    }
}
