import java.io.IOException;

public class Prop extends Scenery{
    Prop(int x, int y) {
        super(x, y);
        loadImage("src\\resources\\Texture\\TX Props-bigrock.png");
    }
    public void loadImage(String path) {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.image = ss.grabImage(ssCol, ssRow, 60, 43);
        getImageDimensions();
    }
}
