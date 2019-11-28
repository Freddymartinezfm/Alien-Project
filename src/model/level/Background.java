package model.level;

import controller.GameManager;
import model.GameFigure;
import model.menu.Text;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Background extends GameFigure {
    private boolean SCROLLING_UP = true;

    public Background(BufferedImage texture){
        super(0,(int) GameManager.win.canvas.getHeight(),texture);
        SCROLLING_UP = true;
        setVelocityY(-1);
    }

    @Override
    public void update() {
        location.x += velocity.x;
        location.y += velocity.y;
        if (SCROLLING_UP){
            setVelocityY(-3);
        }
        if (location.y <= 0){
            SCROLLING_UP = false;
            location.y = (int)GameManager.win.canvas.getHeight();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(texture, (int)location.x,-(int)location.y, GameManager.win.getWidth(), GameManager.win.getHeight()*2, null);


    }

    @Override
    public double getCollisionRadius() {
        return 0;
    }

}
