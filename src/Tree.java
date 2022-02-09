import java.io.IOException;

public class Tree extends Scenery{
    private int width, height;
    Tree(int x, int y, int size) {
        super(x, y);
        imagePath = "src\\resources\\atlas_32x.png";
        switch (size){
            case 1 ->{
                ssCol=1;
                ssRow=2;
                width=64;
                height=96;
            }
            case 2 ->{
                ssCol=3;
                ssRow=2;
                width=64;
                height=96;
            }
            case 3 ->{
                ssCol=5;
                ssRow=3;
                width=32;
                height=64;
            }
        }
        loadImage(imagePath);
    }
    public void loadImage(String path){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage(path);
        }catch(IOException e){
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.image = ss.grabImage(ssCol, ssRow, width,height);
        getImageDimensions();
    }
}
