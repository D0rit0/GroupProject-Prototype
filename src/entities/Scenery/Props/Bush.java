package entities.Scenery.Props;

public class Bush extends Prop{
    Bush(int x, int y, int var) {
        super(x, y);
        ssRow = 1;
        if(var == 1) {
            ssCol = 2;
        }else if(var == 2){
            ssCol=3;
        }
        loadImage("src\\resources\\atlas_32x.png");
    }
}
