public class Ground extends Scenery{
    private PlayerCharacter player = PlayerCharacter.getInstance();
    private int tileID;
    Ground(int x, int y, int tileID){
        super(x,y);
        this.tileID = tileID;
        loadImage("src\\resources\\1 Tiles\\IndustrialTile_"+tileID+".png");
    }
}
