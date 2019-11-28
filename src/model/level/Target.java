package model.level;

import model.GameFigure;

import java.awt.*;

public class Target extends GameFigure {
    public static final float SIZE = 10f;
    public Target(float x, float y){
        super(x, y);
    }

    public Target(){
        this(0f, 0f);
    }

    @Override
    public void update() {
        location.x += velocity.x;
        location.y += velocity.y;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawLine((int)location.x - (int)SIZE, (int)location.y,
                (int)location.x + (int)SIZE , (int)location.y);
        g.drawLine((int)location.x, (int)location.y - (int)SIZE,
                (int)location.x, (int)location.y + (int)SIZE);
    }

    @Override
    public double getCollisionRadius() {
        return 0;
    }
}
