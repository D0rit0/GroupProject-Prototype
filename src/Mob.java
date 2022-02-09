public abstract class Mob extends Entity {
    protected boolean moving = false;
    protected boolean grounded = true;

    public Mob(int x, int y){
        super(x,y);
    }
    public Mob(int x, int y, boolean isVisible){
        super(x,y,isVisible);
    }


    public boolean isMoving(){
        return moving;
    }
    public void setLeft(int newLeft){
        x = newLeft;
    }
    public void setRight(int newRight){
        x = newRight - width;
    }
    public void setTop(int newTop){
        y = newTop;
    }
    public void setBottom(int newBottom){
        y= newBottom-height;
    }

}
