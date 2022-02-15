package entities.Scenery.Props;

public class Planter extends Prop {

    Planter(int x, int y, int var) {
        super(x, y);
        ssRow=1;
        if(var ==1){
            ssCol =4;
        }else if(var ==2){
            ssCol =5;
        }else if(var ==3){
            ssCol =6;
        }else if(var ==4){
            ssCol =7;
        }else if(var ==5){
            ssCol =8;
        }
        loadImage(imagePath);
    }
}
