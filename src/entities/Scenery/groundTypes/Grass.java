package entities.Scenery.groundTypes;

public class Grass extends Ground{
    public Grass(int x, int y) {
        super(x, y);
        ssCol = 2;
        ssRow = 8;
        loadImage(imagePath);
        collideable=false;
    }
}
