package world;

import entities.Entity;
import entities.mobs.Npc;
import util.MapLoader;
import util.TileHandler;
import util.imageRenderer.GraphicsHandler;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private final Tile[][] layerList;
    private final List<Tile> collideableList = new ArrayList<>();
    private final ArrayList<DoorTrigger> DoorList = new ArrayList<>();
    private final String mapLocation;
    private final int mapX;
    private final int mapY;
    private final int layers;
    private final MapLoader mapLoader;

    private ArrayList<Npc> npcList = new ArrayList<Npc>();
    private ArrayList<Entity> itemList = new ArrayList<>();

    public Map(String mapLocation, int mapX, int mapY, int layers){
        this.mapLocation = mapLocation;
        this.mapX = mapX;
        this.mapY = mapY;
        this.layers = layers;

        mapLoader = new MapLoader(mapLocation);
        layerList = new Tile[layers][mapX*mapY];
    }

    private void calculateCollideables(){
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
    public void init() {
        int[] collideableTileIDs = new int[0];
        int[][] mapData;
        switch (mapLocation) {
            case "src\\world\\Team Valentine World Map.tmx" -> collideableTileIDs = TileHandler.collideableTileIDsMap1;
            case "src\\world\\generalStore.tmx" -> collideableTileIDs = TileHandler.collideableTileIDsMap2;
            case "src\\world\\Florist.tmx" -> collideableTileIDs = TileHandler.collideableTileIDsMap3;
            case "src\\world\\Chocolate_shop.tmx" -> collideableTileIDs = TileHandler.collideableTileIDsMap4;
        }
        for (int temp = 0; temp < layers; temp++) {
            mapData = mapLoader.loadLayerArray(mapLoader.getLayer(temp+1), mapX, mapY);
            GraphicsHandler.LoadTextures(mapData);
            int i = 0;
            for (int y = 0; y < mapData.length; y++) {
                for (int x = 0; x < mapData[y].length; x++) {
                    try {
                        if(mapData[y][x]!=0) {
                            layerList[temp][i] = new Tile(x * 32, y * 32, mapData[y][x], mapLocation);
                            for (int collideableTileID : collideableTileIDs) {
                                if (collideableTileID == mapData[y][x]) {
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
    public Tile[][] getLayerList(){
        return layerList;
    }

    public List<Tile> getCollideableList() {
        return collideableList;
    }

    public String getMapLocation(){
        return mapLocation;
    }

    public void moveDoorsX(int dx){
        for(DoorTrigger e: DoorList){
            e.setX(dx);
        }

    }
    public void moveDoorsY(int dy){
        for(DoorTrigger e: DoorList){
            e.setY(dy);
        }

    }

    public ArrayList<DoorTrigger> getDoorList(){
        return DoorList;
    }

    public ArrayList<Npc> getNpcList(){
        return npcList;
    }

    public ArrayList<Entity> getItemList() {
        return itemList;
    }

    public void shift(int x, int y){
        for(Tile[] e: layerList){
            for(Tile t: e){
                if(t != null) {
                    t.setX(x);
                    t.setY(y);
                }
            }
        }
        for(DoorTrigger d: DoorList){
            d.setX(x);
            d.setY(y);
        }
        TileHandler.manageTiles();
    }
}
