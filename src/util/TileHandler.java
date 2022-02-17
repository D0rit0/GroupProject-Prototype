package util;

import static util.imageRenderer.GraphicsHandler.textureMap;
import world.Map;
import world.Tile;

import static main.AppPanel.width;
import static main.AppPanel.height;

public class TileHandler {

    public static final int [] collideableTileIDs = new int[]{
        3,2,519,616,614,710,711,712,518,520,998,999,100,902,903,904,
            149,245,1014,1015,918,919,344,1001,1002,1003,1004,1005,1097,1011,1193,1194,1195,1196,
            617,618,619,620,621,713,717,809,813,905,906,907,908,909,41,42,43,44,45,137,141,
            233,234,235,236,237,329,330,331,332,425,428,521,522,523,524,148,241,242,197,293,97,98,193,194,289,290,
            99,100,195,196,291,292,6,5,4,609,610,611,612,613,705,709,801,805,897,898,899,900,901,
            321,322,323,324,417,420,513,514,515,516,33,34,35,36,37,129,133,225,226,227,228,229,993,994,995,996,997,1089,1093,
            1185,1186,1187,1188,1189,201,9,203,11,984,1265,1266,1267,1073,1075,977,978,979,1363,1364,538,154,441,346,249,443,
            153,821,822,823,581,582,583,584,677,680,773,774,775,776,1252,1253,1254,1255,1256,1348,1352,1444,1445,1446,1447,1448,
            868,869,870,871,872,964,968,1060,1064,1156,1157,1158,1159,1160,770,769,771,385,386,673,675,772,578,1259,1355,1358,1452,1454,
            1361,1009,1105,916,1109,577,579
    };

    //checks to see if tile should be loaded based on if its within view of the panel
    public static boolean shouldTileLoad(Tile tile){
        if(tile != null) {
            return tile.getX() > -48 && tile.getX() < width + 32
                    && tile.getY() > -32 && tile.getY() < height + 32;
        }
        return false;
    }


    //manages which tiles should be rendered and if a texture has been loaded onto a tile.
    public static void manageTiles(){
        for(Tile[] tileList: Map.getLayerList()) {
            for (Tile tile : tileList) {
                if(tile !=null) {
                    tile.setImageLoaded(shouldTileLoad(tile));
                    if (!shouldTileLoad(tile)) {
                        tile.setVisible(false);
                        tile.unloadImage();
                    } else {
                        if (tile.getImage() != null) {
                            tile.setVisible(true);
                        } else {
                            tile.setImage(textureMap.get(tile.getTileId()));
                        }
                    }
                }
            }
        }
    }
}
