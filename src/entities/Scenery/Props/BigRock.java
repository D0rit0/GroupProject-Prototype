package entities.Scenery.Props;

public class BigRock extends Prop {
    public BigRock(int x, int y) {
        super(x, y);
        super.propH = 60;
        super.propW = 43;
        loadImage("src\\resources\\Texture\\TX Props-bigrock.png");
    }
}
