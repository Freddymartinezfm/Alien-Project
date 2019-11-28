package model;

import model.bullet.Bullet;

import java.util.ArrayList;
import java.util.HashMap;

public class GameData {
    private static GameData instance;

    public ArrayList<GameFigure> fixedObject = new ArrayList<>();
    public ArrayList<GameFigure> friendObject = new ArrayList<>();
    public ArrayList<GameFigure> enemyObject = new ArrayList<>();
    public ArrayList<GameFigure> remove  = new ArrayList<>();

    public static GameData getInstance(){
        if (instance == null){
            instance = new GameData();
        }
        return instance;
    }

    public void update(){
        for (var fig : fixedObject){
             if (fig.done) remove.add(fig);
            else fig.update();


        }
        fixedObject.removeAll(remove);
        remove.clear();

        for (var fig : friendObject){
            if (fig.done) remove.add(fig);
            else fig.update();

        }
        friendObject.removeAll(remove);
        remove.clear();

        for (var fig : enemyObject){
            if (fig.done) remove.add(fig);
            else {

                fig.update();
            }

        }
        enemyObject.removeAll(remove);
        remove.clear();
    }

    public void clear() {
        fixedObject.clear();
        friendObject.clear();
        enemyObject.clear();

    }


    private boolean maxBullets(Bullet bullet){
        return bullet.getNumberOfBullets() >= 2;
    }
}
