package util.imageRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoader {

    public BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }
}
