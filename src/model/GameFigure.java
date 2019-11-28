package model;
import model.bee.Bee;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class GameFigure {
    public static final float UNIT_MOVE = 5f;
    public boolean done;
    public int hitCount;
    public Point2D.Float location;
    public Point2D.Float velocity;
    public Point2D.Float acceleration;
    public BufferedImage texture;
    protected Rectangle2D.Float bound;
    public int baseWidth;
    public int baseHeight;
    protected  int SCALE = 2;
    protected  float ty ;
    protected  float tx;




    public GameFigure(float x, float y, BufferedImage texture){
        location = new Point2D.Float(x, y);
        this.texture = texture;
        velocity = new Point2D.Float(0f,0f);
        acceleration = new Point2D.Float(0f,0f);
        tx = 0;
        ty = 0;

        done = false;
        if (texture != null){
            baseWidth = (this.texture.getWidth() );
            baseHeight = (this.texture.getHeight());
        }
        hitCount = 0;

    }

    public GameFigure(){
        this(0.0f, 0.0f, null);

    }

    public GameFigure(float x, float y){
        this(x, y, null);
    }

    public GameFigure(Point2D.Float pathLocation){
        this(pathLocation.x, pathLocation.y, null);
    }


    public void setLocationX(float tx){ location.x = tx;}

    public void setLocationY(float ty){ location.y = ty;}

    public void setPathLocation(Point2D.Float pathLocation){ this.location = pathLocation;}

    public void setVelocityX(float tx){ this.velocity.x = tx;}

    public void setVelocityY(float ty){ this.velocity.y = ty;}

    public void setBound(Rectangle2D.Float b){
        this.bound = b;
    }



    public Point2D.Float getLocation(){ return location;}


    public boolean collideWith(GameFigure o) {
        // method 1
        double dist = Math.abs(this.location.distance(o.location));



        if (dist <= this.getCollisionRadius() + o.getCollisionRadius()) {
            System.out.println("Dist less than radius " + this.getCollisionRadius() + o.getCollisionRadius() + " and dist =" + dist);
            o.done = true;
            return true;
        } else {
            return false;
        }
        //return (dist <= this.getCollisionRadius() + o.getCollisionRadius());

        // method 2
//        double overLap = .5f * (dist - this.getCollisionRadius() - o.getCollisionRadius());
//
//        System.out.println(overLap);

    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    public abstract double getCollisionRadius();



}
