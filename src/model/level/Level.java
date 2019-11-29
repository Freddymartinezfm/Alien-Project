package model.level;

import model.GameFigure;
import model.bee.Bee;
import model.butterfly.Butterfly;

import java.awt.*;
import java.util.ArrayList;

import static controller.GameManager.*;

public class Level extends GameFigure implements Subject {
    public int score;
    int startingEnemies = 4 ;
    public  static int defaultScoreUnit = 20;
    public boolean levelComplete;
    boolean levelStarted;
    int numberOfBees;
    int numberOfButterflies;
    public int totalEnemies;
    int kills;

    ArrayList<Observer> listeners;


    public Level(int x, int y, int e){
        super(x, y);
        startingEnemies = e;
        score = 0;
        kills = 0;
        startLevel();
        spawn();
        levelComplete = false;
        levelStarted = false;

    }

    public void spawn(){
        for (int i = 0; i < startingEnemies; i++) {
            gameData.enemyObject.add(new Bee(i, i * 50, i, 1 ));
            numberOfBees++;
            totalEnemies++;
        }

        for (int i = 0; i < startingEnemies; i++) {
            gameData.enemyObject.add(new Butterfly(i * 50, i, i, 0 ));
            numberOfButterflies++;
            totalEnemies++;
        }
    }

    public void startLevel(){


    }




    @Override
    public void update() {
        // observer update menu
        var bee = gameData.fixedObject.get(SPACESHIP_INDEX);

    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public double getCollisionRadius() {
        return 0;
    }

    @Override
    public void attachListener(Observer o) {
        listeners.add(o);

    }

    @Override
    public void detachListener(Observer o) {
        listeners.remove(o);



    }

    @Override
    public void notifyEvent() {
        for (var o : listeners) {
            o.eventRecieved();
        }

    }
}
