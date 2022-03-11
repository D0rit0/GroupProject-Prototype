package world;

import static main.AppPanel.currentMap;
import static main.AppPanel.player;

public class DoorTrigger extends Tile{
    protected String mapLocation;
    protected Map map;
    protected DoorTrigger linkedDoor;

    public DoorTrigger(int x, int y, int tileId, String mapLocation, Map map, DoorTrigger linkedDoor) {
        super(x, y, tileId, mapLocation);
        this.tileId = 0;
        this.map = map;
        this.mapLocation = mapLocation;
        collideable = true;
        imageLoaded = true;
        isVisible = true;
        this.linkedDoor = linkedDoor;

    }

    public void trigger(){
        currentMap = map;
        player.setLeft(linkedDoor.getX());
        player.setTop(linkedDoor.getY());
    }

    public void setLinkedDoor(DoorTrigger linkedDoor){
        this.linkedDoor = linkedDoor;
    }

    public boolean isInteractable(){
        return (!mapLocation.equals(currentMap.getMapLocation()));
    }
}
