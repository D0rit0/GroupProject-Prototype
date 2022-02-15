package world;

public class WorldChunk {
    protected int chunkW;
    protected int chunkH;
    protected int chunkX;
    protected int chunkY;

    protected int[][] chunkMap;

    public WorldChunk(int x,int y){
        chunkX=x;
        chunkY=y;
    }

    public int getChunkH() {
        return chunkH;
    }

    public int getChunkW() {
        return chunkW;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }
    public void setChunkMap(int[][] chunkMap){
        this.chunkMap = chunkMap;
    }
    public int[][] getChunkMap(){
        return chunkMap;
    }
    public void dumpChunkMap(){
        chunkMap =null;
    }
}
