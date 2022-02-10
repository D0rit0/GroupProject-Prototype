public class BuildingVar1 extends Building{
    BuildingVar1(int x, int y, char color) {
        super(x, y);
        buildingW = 5;
        ssRow=1;
        if(color == 'r'){
            ssCol =32;
        }else{
            ssCol =40;
        }
    }
}
