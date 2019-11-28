package model.resources;

import controller.GameManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteSheet {
    private BufferedImage sheet;
    private HashMap<String, BufferedImage> textures;


    public SpriteSheet(String path){
          sheet = GameManager.loader.loadImageFile(path);
          textures = new HashMap<>();
    }

    public BufferedImage getTexture(String name){
        return textures.get(name);
    }

    public BufferedImage crop(String name, int x, int y, int w, int h){
        BufferedImage temp = sheet.getSubimage(x, y, w, h);
        textures.put(name, temp);
        return temp;
    }


}
