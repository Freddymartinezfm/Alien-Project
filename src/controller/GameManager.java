package controller;

import model.*;
import model.level.Target;
import model.helpers.ResourceLoader;
import model.level.Background;
import model.level.Level;
import model.menu.HUDItems;
import model.menu.InGameStats;
import model.menu.StartScreen;
import model.arrangement.EnemyPath;
import model.resources.SpriteSheet;
import model.spaceship.BaseShip;
import view.MainWindow;
import javax.swing.*;

public class GameManager {
    // git test
    public static boolean running;
    public static MainWindow win;
    public static GameData gameData;
    private static long FPS = 20;
    public static long timeSpent;
    public static long sleepTime;
    private static InputManager inputManager;
    public static ResourceLoader loader;
//    public static SpriteSheet sheet;
    public static  SpriteSheet sheet;
    public static GameManager instance;
    public static InGameStats inGameStats;
    public static final int inGameStatsIndex = 1;
    public static int SPACESHIP_INDEX = 2;
    public static int ENEMY_INDEX = 0;
    public static int TARGET_INDEX = 3;
    public static int FORMATION_INDEX = 5;
    public static Level level;

    private GameManager(){
    }



    public MainWindow getWin(){
        return win;
    }

    public void run()  {
        win = MainWindow.getInstance();
        win.init();
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setVisible(true);
        win.setSize(560, 760);
        gameData = GameData.getInstance();
        inputManager = new InputManager();
        loader = new ResourceLoader();
        sheet = new SpriteSheet("model/resources/sprites/sprite.png");
        loader.registerFont("model/resources/font/Emulogic-zrEw.ttf");

        //startScreen();
        initGame();
        gameLoop();
    }



    public static GameManager getInstance(){
        if (instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    static void startScreen()  {
        gameData.fixedObject.add(new StartScreen("Emulogic"));
        while (!running) {
            inputManager.processStartMenuEvents();
            win.canvas.render();
            gameData.update();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }
    }


    static void initGame()  {
        gameData.clear();
        EnemyPath.createPaths();


        sheet.crop("Spaceshipa",160,55, 16,17);
        sheet.crop("Spaceshipb",160,79, 16,17);
        sheet.crop("Enemy1a",160, 103, 16, 16);
        sheet.crop("Enemy1b",160, 127, 16, 16);
        sheet.crop("Enemy1c",160, 151, 16, 16);
        sheet.crop("Enemy1d",160, 175, 16, 16);
        sheet.crop("Bullet",374, 51, 16, 16);


        gameData.fixedObject.add(new Background(loader.loadImageFile("model/resources/352797_2_En_4_Fig2_HTML.jpg")));
        gameData.fixedObject.add(new InGameStats("Emulogic"));
        gameData.fixedObject.add(new BaseShip((float)GameManager.win.getWidth()/2, GameManager.win.canvas.getHeight() - 20,sheet.getTexture("Spaceshipa")));
        gameData.fixedObject.add(new Target(0,0));

        //////////////////////////////


        level = new Level(400, 300, 4);
        gameData.fixedObject.add(level);
    }





//        static void processCollisions() {
//        var ship = (BaseShip) GameManager.gameData.fixedObject.get(GameManager.SPACESHIP_INDEX); // 1 no error if not casted to (Shooter) but you should
//
//
//
//        for (var enemy : GameManager.gameData.enemyObject) {
//            if (ship.collideWith(enemy)) {
//                ++ship.hitCount;
//                ++enemy.hitCount;
//
//
//                level.score +=level.beePoints ;
//                System.out.println(level.score);
//
//            }
//
//        }
//        for (var friend : GameManager.gameData.friendObject){
//            for (var enemy : GameManager.gameData.enemyObject){
//                if (friend.collideWith(enemy)){
//                    ++friend.hitCount;
//                    ++enemy.hitCount;
//                }
//            }
//        }
//    }

    static void processCollisions() {
        var ship = (BaseShip) GameManager.gameData.fixedObject.get(GameManager.SPACESHIP_INDEX); // 1 no error if not casted to (Shooter) but you should
        var stats = (InGameStats) gameData.fixedObject.get(inGameStatsIndex);

        for (var friend : GameManager.gameData.friendObject){
            for (var enemy : GameManager.gameData.enemyObject){
                if (friend.collideWith(enemy)){
                    ++friend.hitCount;
                    ++enemy.hitCount;



                    level.score += Level.defaultScoreUnit;
                    System.out.println(level.score);
                    stats.setScore(level.score);
                    if (level.hitCount < level.totalEnemies){
                        level.levelComplete = true; // state pattern, next state or next level
                        level.done = true;
                        //level.createNewLevel();
                    }
                }
            }
        }

        for (var enemy : GameManager.gameData.enemyObject) {
            if (ship.collideWith(enemy)) {
                ++ship.hitCount;
                ++enemy.hitCount;
            }

        }

    }


    static void gameLoop() {
        running = true;
        while (running) {
            long startTime = System.currentTimeMillis();
            inputManager.processInputEvents();
            processCollisions();
            gameData.update();
            long endTime = System.currentTimeMillis();
            win.canvas.render();
            timeSpent = endTime - startTime; // nowTime - start time  = timeElapsed this is the delta
            sleepTime = (long) 1000.0 / FPS - timeSpent;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();

            }
        }
    }

//    public static void addEnemyWithListener(int x, int y){
//        var ufo = new Enemy(x, y);
//        ufo.attachListener(new UFOObserverAddNew());
//        gameData.enemyObject.add(ufo);
//    }

    //    public static void startNewLevelListener(int x, int y){
//        var level = new Level(x, y);
//        ufo.attachListener(new UFOObserverAddNew());
//        gameData.enemyObject.add(ufo);
//    }
}

