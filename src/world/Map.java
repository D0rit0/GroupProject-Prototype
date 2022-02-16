package world;

import util.MapLoader;
import util.imageRenderer.GraphicsHandler;

public class Map {

    private static final Tile[][] layerList = new Tile[7][100*100];
    //Loads the textures and creates the tile objects using the data given from our map loader class
    public static void init() {
        int[][] mapData;
        for (int temp = 0; temp < 7; temp++) {
            mapData = MapLoader.loadLayerArray(MapLoader.getLayer(temp+1));
            GraphicsHandler.LoadTextures(temp+1, mapData);
            int i = 0;
            for (int y = 0; y < mapData.length; y++) {
                for (int x = 0; x < mapData[y].length; x++) {
                    try {
                        if(mapData[y][x]!=0) {
                            layerList[temp][i] = new Tile(x * 32, y * 32, mapData[y][x]);
                        }
                        i++;
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        System.err.println("out of memory");
                    }
                }
            }
        }
        System.out.println("it worked");
    }
    public static Tile[][] getLayerList(){
        return layerList;
    }
}
