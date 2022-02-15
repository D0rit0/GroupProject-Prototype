package entities.Scenery.Buildings;

public class BuildingVar3 extends Building {
    BuildingVar3(int x, int y, char color) {
        super(x, y, color, 2, 3, 5, 4,3);
        if(color == 'b' || color == 'r') {
            ssRow = 7;
        }
    }
}
