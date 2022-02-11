package entities.mobs;

public class PeacefulAnimal extends Mob {
    public PeacefulAnimal(int x, int y, String type) {
        super(x, y);
        loadImage("src\\resources\\Animal\\"+type+" 01-1.png");
    }
    public void pet(){}
    public void feed(){}
}
