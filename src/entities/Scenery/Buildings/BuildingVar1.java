package entities.Scenery.Buildings;

public class BuildingVar1 extends Building {
    public BuildingVar1(int x, int y, char color) {
        super(x, y, color,2,2,5,3,1);
        if(color == 'b' || color == 'r') {
            ssRow = 1;
        }
    }
}
