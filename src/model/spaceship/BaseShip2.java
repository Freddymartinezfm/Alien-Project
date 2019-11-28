package model.spaceship;

import controller.GameManager;
import model.GameFigure;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class BaseShip2 extends GameFigure {

    public BaseShip2(int x, int y, BufferedImage texture){
        super(x, y, texture);

    }

    @Override
    public void update() {



    }

    @Override
    public void render(Graphics2D g) {


        g.drawImage(texture, (int)location.x, (int)location.y, null);

    }

    @Override
    public double getCollisionRadius() {
        return 0;
    }





}
