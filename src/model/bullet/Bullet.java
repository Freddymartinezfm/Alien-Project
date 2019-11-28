package model.bullet;

import controller.GameManager;
import model.GameFigure;
import model.spaceship.BaseShip;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bullet extends GameFigure {

    public static final int UNIT_MOVE = 10;
    public static final int INIT_BULLET_SIZE = 0;
    public static final int MAX_MISSILE_SIZE = 6;
    public static final int MAX_MISSILE_SPEED = 6;


    public static final int FIRED_STATE = 0;
    private static final int EXPLODING_STATE = 1;
    private static final int DONE_STATE = 2;


    int state;
    private int numberOfBullets;
    private int size = INIT_BULLET_SIZE;
    Point2D.Float bullet_target;
    Color color;
    ArrayList<BufferedImage> frames;
    public int explodingFrames;
    public static int MAX_FRAME = 5;


    public Bullet(float tx, float ty) {
        super(0.0f, 0.0f, GameManager.sheet.getTexture("Bullet"));
        BaseShip baseShip = (BaseShip) GameManager.gameData.fixedObject.get(GameManager.SPACESHIP_INDEX);
        location.x = (float) baseShip.getWeapon().getX2(); // tip of ship
        location.y = (float) baseShip.getWeapon().getY2();
        texture = GameManager.sheet.getTexture("Bullet");
        baseWidth = (int) (baseWidth * .19);
        baseHeight = (int) (baseHeight * .40);
        numberOfBullets = 0;
        bullet_target = new Point2D.Float(tx, ty);
        size = INIT_BULLET_SIZE;
        bound = new Rectangle2D.Float(location.x -baseWidth, location.y -baseHeight, baseWidth,  baseHeight);
        color = Color.RED;
        loadFrames(frames);
        state = FIRED_STATE;
        explodingFrames = 1;
    }

    public int getNumberOfBullets() {
        return numberOfBullets;
    }

    public void loadFrames (ArrayList<BufferedImage> f){
        frames = new ArrayList<>();
        frames.add(texture);
        frames.add(GameManager.sheet.crop("Explode1", 234, 200, 16, 16));
        frames.add(GameManager.sheet.crop("Explode2", 256, 200, 16, 16));
        frames.add(GameManager.sheet.crop("Explode3", 282, 193, 32, 32));
        frames.add(GameManager.sheet.crop("Explode4", 320, 191, 32, 32));
        frames.add(GameManager.sheet.crop("Explode5", 211, 202, 32, 32));
        frames.add(GameManager.sheet.crop("Explode6", 211, 202, 32, 32));
    }

    @Override
    public void update() {
        location.x += velocity.x;
        location.y += velocity.y;
        bound.x = location.x;
        bound.y = location.y;
//
//        if (Math.abs(velocity.x * location.x + velocity.y * location.y) < 0.10f){
//            velocity.x = 0;
//            velocity.y = 0;
//            //done = true;
//            //setState(DONE_STATE);
//
//        }
        updateState();
    }

    private void handleFiredState() {
        double dist = bullet_target.distance(location);
        int count = 0;
        if (dist  <=  5.0f ) {
            setState(EXPLODING_STATE);
//            color = Color.YELLOW;
//            if (size <= MAX_MISSILE_SIZE) {
//                ++size;
//            } else {
//                 setState(DONE_STATE);
//            }
        } else {
            double rad = Math.atan2(bullet_target.y - location.y, bullet_target.x - location.x); // the angle is returned from rectangular coordinates
//            setVelocityX((float) ((float) UNIT_MOVE * (Math.cos(rad))));
//            setVelocityY((float)((float) UNIT_MOVE * Math.sin(rad)));
            super.location.x += (UNIT_MOVE ) * Math.cos(rad);
            super.location.y += (UNIT_MOVE ) * Math.sin(rad);
        }
    }

    public void handleDoneState() {
        done = true;
    }


    public void handleExplodingState() {
//        if (size <= MAX_MISSILE_SIZE) {
//            ++size;
//        }
//            setState(STATE_DONE);

        //color = Color.YELLOW;
        if (size <= MAX_MISSILE_SIZE ) {
            if (size > frames.size()){
                size = size % frames.size();
            }
            //texture = GameManager.sheet.getTexture("Explode0");
            texture = frames.get(size);
            ++size;
        } else {
            setState(DONE_STATE);
        }

    }


    private void updateState() {
        switch (state) {
            case FIRED_STATE:
                handleFiredState();
                break;
            case EXPLODING_STATE:
                handleExplodingState();
                break;
            case DONE_STATE:
                handleDoneState();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }


    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(.01f));
        g.drawImage(texture, (int) location.x - baseWidth , (int) location.y - baseHeight, baseWidth * 2, baseHeight*2,  null);
        //g.drawRect((int)getLocation().x- baseWidth, (int)getLocation().y- baseHeight, baseWidth * 2, baseHeight*2);
        //g.draw(bound);
    }

    public void setState(int state){
        this.state = state;
    }


    @Override
    public double getCollisionRadius() {
        return baseHeight/2f;
    }
}
