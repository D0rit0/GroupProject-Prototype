package entities.Scenery.Buildings;

import entities.Scenery.Scenery;
import imageRenderer.BufferedImageLoader;
import imageRenderer.SpriteSheet;

import java.awt.*;
import java.io.IOException;

public abstract class Building extends Scenery {
    protected char color;
    protected int doorX,doorY;
    protected int buildingW, buildingH;
    Building(int x, int y, char color, int doorOfSetX, int doorOfSetY, int buildingW, int buildingH){
        super(x,y);
        this.color=color;
        if(color == 'r'){
            ssCol =33;
        }else{
            ssCol =41;
        }
        this.buildingW=buildingW;
        this.buildingH=buildingH;
        doorX=x+doorOfSetX*32;
        doorY=y+doorOfSetY*32;
        loadImage("src\\resources\\atlas_32x.png");
    }
    protected void loadImage(String path){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage(path);
        }catch(IOException e){
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        this.image = ss.grabImage(ssCol, ssRow, buildingW*32,buildingH*32);
        getImageDimensions();
    }
    public Rectangle getDoorBounds(){
        return new Rectangle(doorX,doorY,32,32);
    }
}
