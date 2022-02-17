package world;

import util.MapLoader;
import util.TileHandler;
import util.imageRenderer.GraphicsHandler;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private static final Tile[][] layerList = new Tile[7][100*100];
    private static final List<Tile> collideableList = new ArrayList<>();

    private static void calculateCollideables(){
        for(Tile[] list: layerList){
            for(Tile tile: list){
                if(tile != null && tile.isCollideable()){
                    boolean s1 = false;
                    boolean s2 = false;
                    boolean s3 = false;
                    boolean s4 = false;
                    for(Tile[] list2: layerList) {
                        for (Tile tile2 : list2) {
                            if (tile2 != null&& tile2.isCollideable()) {
                                if (tile2.getX() == tile.getX() - 32 && tile2.getY() == tile.getY()) {
                                    s1 = true;
                                } else if (tile2.getX() == tile.getX() + 32 && tile2.getY() == tile.getY()) {
                                    s2 = true;
                                } else if (tile2.getX() == tile.getX() && tile2.getY() == tile.getY() - 32) {
                                    s3 = true;
                                } else if (tile2.getX() == tile.getX() && tile2.getY() == tile.getY() + 32) {
                                    s4 = true;
                                }
                            }
                        }
                    }
                    if(!s1 || !s2 || !s3 || !s4){
                        collideableList.add(tile);
                    }
                }
            }
        }
    }
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
                            for(int z = 0; z < TileHandler.collideableTileIDs.length; z++){
                                if(TileHandler.collideableTileIDs[z]==mapData[y][x]){
                                    layerList[temp][i].setIsCollideable(true);
                                }
                            }
                        }
                        i++;
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        System.err.println("out of memory");
                    }
                }
            }
        }
        calculateCollideables();

        System.out.println("it worked");
    }
    public static Tile[][] getLayerList(){
        return layerList;
    }

    public static List<Tile> getCollideableList() {
        return collideableList;
    }
}
