public class BuildingVar2 extends Building{
    private char color;
    BuildingVar2(int x, int y, char color) {
        super(x, y);
        buildingW = 5;
        ssRow=4;
        if(color == 'r'){
            ssCol =32;
        }else{
            ssCol =40;
        }
    }
}
