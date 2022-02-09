public class Ground extends Scenery{
    private int tileID;
    Ground(int x, int y, int ssCol, int ssRow){
        super(x,y);
        this.ssCol=ssCol;
        this.ssRow=ssRow;
        loadImage("src\\resources\\Texture\\TX Tileset Grass.png");
    }
    public int getTileID(){
        return tileID;
    }
}
