package entities.Scenery.Buildings;

public class BuildingVar2 extends Building {
    BuildingVar2(int x, int y, char color) {
        super(x, y, color,1,2,4,3,2);
        if(color == 'b' || color == 'r') {
            ssRow = 4;
        }
    }
}
