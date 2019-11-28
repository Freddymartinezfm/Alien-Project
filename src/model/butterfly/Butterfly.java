package model.butterfly;

import controller.GameManager;
import model.GameFigure;
import model.arrangement.EnemyPath;

import java.awt.*;
import java.awt.geom.Point2D;

public class Butterfly extends GameFigure {
    public static final int FLYING_IN_STATE = 0;
    private static final int FLYING_IN_COMPLETE = 1;
    private static final int STATE_HIT = 2;
    private static final int IN_FORMATION = 3;
    public static final int MAIN_PLAY = 4;
    public int state;
     int currentWaypoint = 0;
    int currentPath;
    int index;
    ButterflyAnimStrategy animStrategy;
    public static int numberOfButterFlies = 0;
    public static final int MAX_NUMBER_ButterFlies = 8;


    public Butterfly(int x, int y, int i, int p){
        super(x, y, GameManager.sheet.getTexture("Butterfly"));
        currentPath = p;
        currentWaypoint = 0;
        index = i;
        location.x += EnemyPath.paths.get(currentPath).get(0).x;
        location.y += EnemyPath.paths.get(currentPath).get(0).y;
        animStrategy = new ButterflyFlyingInAnim(this);
        ++numberOfButterFlies;

    }

    @Override
    public void update() {
        updateStates();
    }


    private void updateStates() {
        velocity.x += acceleration.x;
        velocity.y += acceleration.y;
        location.x += velocity.x;
        location.y += velocity.y;

        switch (state){
            case FLYING_IN_STATE:
                animStrategy.animate();
                handleFlyingInState();
                break;
            case FLYING_IN_COMPLETE:
                animStrategy = new ButterFlyingInComplete(this);
                handleFlyingInComplete();
                break;
            case STATE_HIT:
                //animStrategy = new BeeHitAnimation(this);
                handleHitState();
                break;
            case IN_FORMATION:
                //animStrategy = new BeeInFormation(this);
                handleInFormationState();
                break;
            case MAIN_PLAY:
                location.x ++;
                        if (location.x > GameManager.win.canvas.getWidth() - baseWidth)
                            setVelocityX(-1f * GameFigure.UNIT_MOVE);
                        else if (location.x - baseWidth < 0)
                            setVelocityX(1f * GameFigure.UNIT_MOVE);
                        if (location.y > GameManager.win.canvas.getHeight() - baseHeight)
                            setVelocityY(-1f * GameFigure.UNIT_MOVE);
                        else if (location.y - baseHeight < 0)
                            setVelocityY(1f * GameFigure.UNIT_MOVE);

                break;

        }
    }

    private void handleHitState() {
        animStrategy.animate();

    }

    private void handleInFormationState(){
        animStrategy.animate();


    }

    public void handleFlyingInState(){
        if (currentWaypoint >= EnemyPath.paths.get(currentPath).size() - 3) {
            setState(FLYING_IN_COMPLETE);
        }
        if (currentWaypoint <= EnemyPath.paths.get(currentPath).size() - 3) {
            //Point2D.Float target = new Point2D.Float(EnemyPath.paths.get(currentPath).get(currentWaypoint +1 ).x, EnemyPath.paths.get(currentPath).get(currentWaypoint + 1).y);
//            if (hitCount > 0) {
//                setState(STATE_HIT);
//            }
                animStrategy.animate();
            }
    }

    public void setState(int state){
        this.state = state;
    }

    public void handleFlyingInComplete(){
        Point2D.Float target =  new Point2D.Float(100, 100); // shouold take a index to seperate out
//        returnValue.x = (float) ( formation.gridLocation.x + formation.gridLocation.x  * 2 * (i/4) * (Math.pow(-1, (i % 2 + 1))));
//        returnValue.y = (float) (formation.gridLocation.y * ((i % 4) / 2.0));
//        System.out.println(target);
//
//        if (hitCount > 0 ){
//            setState(STATE_HIT);
//        }
        double dist = target.distance(location);
        if (target.distance(location) < 10){

            setState(IN_FORMATION);
        }

        double rad = Math.atan2(target.y - location.y, target.x - location.x);
        location.x +=  UNIT_MOVE  * Math.cos(rad);
        location.y += UNIT_MOVE *  Math.sin(rad);


    }

    @Override
    public void render(Graphics2D g) {
//        for (var e : GameManager.gameData.enemyObject) {
//            g.drawImage(e.texture, (int)e.location.x,(int) e.location.y, baseWidth * 2, baseHeight * 2, null);
//        }

        g.drawImage(texture,(int)location.x - baseWidth, (int)location.y - baseHeight, baseWidth * 2, baseHeight * 2, null );

//        for (int i = 0; i < paths.get(currentPath).size() - 3 ; i++) {
//            if (i == paths.get(currentPath).size()){
//                g.setColor(Color.WHITE);
//            }
//            g.fillOval((int) (int) paths.get(0).get(i).x, (int)(int) paths.get(0).get(i).y, 4, 4);
//        }





    }

    @Override
    public double getCollisionRadius() {
        return baseWidth;
    }


}
