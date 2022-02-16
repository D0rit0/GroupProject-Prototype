package util;

import static util.imageRenderer.GraphicsHandler.textureMap;
import world.Map;
import world.Tile;

import static main.AppPanel.width;
import static main.AppPanel.height;

public class TileHandler {

    //checks to see if tile should be loaded based on if its within view of the panel
    private static boolean shouldTileLoad(Tile tile){
        if(tile != null) {
            return tile.getX() > -32 && tile.getX() < width + 32
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
