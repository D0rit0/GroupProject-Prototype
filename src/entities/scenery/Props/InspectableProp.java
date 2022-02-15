package entities.scenery.Props;

public class InspectableProp extends Prop {
    protected boolean isInspectable;
    InspectableProp(int x, int y, boolean isVisible) {
        super(x, y, isVisible);
        loadImage(imagePath);
    }
    public void inspect(){}

    public boolean getInspectable(){
        return isInspectable;
    }

    public void setInspectable(boolean isInspectable){
        this.isInspectable = isInspectable;
    }
}
