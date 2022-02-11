package entities.Scenery.Props;

import entities.Scenery.Scenery;
import imageRenderer.BufferedImageLoader;
import imageRenderer.SpriteSheet;

import java.io.IOException;

public abstract class Prop extends Scenery {
    protected int propW, propH;
    Prop(int x, int y) {
        super(x, y);
    }
    protected Prop(int x, int y, boolean visible) {
        super(x, y);
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
