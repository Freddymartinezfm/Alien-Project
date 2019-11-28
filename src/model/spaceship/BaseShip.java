package model.spaceship;

import controller.GameManager;
import model.GameFigure;
import model.level.Target;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class BaseShip extends GameFigure {
    public static final int STATE_FLYING = 0;
    public static final int STATE_EXPLODING = 1;

    public static final float UNIT_MOVE = 2.5f;
    private Line2D.Float weapon;
    public int weapon_len;
    protected Animation animation;
    public Target target;


    public BaseShip(float x, float y, BufferedImage texture){
        super(x, y, texture);
        weapon_len = baseHeight/2;
        bound = new Rectangle2D.Float(location.x - (float)baseWidth/2,location.y - (float)baseHeight/2, baseWidth, baseHeight);
        weapon = new Line2D.Float(location.x - (float)baseWidth/2, location.y - (float)baseHeight/2, location.x, location.y -  weapon_len);


    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(.01f));
        //g.draw(bound);
        g.drawImage(texture, (int)location.x - baseWidth, (int) ((int)location.y - baseHeight), baseWidth*2, baseHeight*2, null);
        g.drawRect((int)location.x - baseWidth, (int) ((int)location.y - baseHeight), baseWidth*2, baseHeight*2);
        g.drawLine((int)weapon.x1, (int)weapon.y1, (int)weapon.x2, (int)weapon.y2);
    }

    @Override
    public void update() {
        target  = (Target) GameManager.gameData.fixedObject.get(GameManager.TARGET_INDEX);
        float tx = target.location.x;
        float ty = target.location.y;
        double rad = Math.atan2(ty - location.getY() , tx - location.getX()); // h / b

        float gun_x = (float) (weapon_len * Math.cos(rad));
        float gun_y = (float) (weapon_len * Math.sin(rad));

        weapon.x1 = location.x ; // beginning of weapon starts where the user is , in this case, the spacesip
        weapon.y1 = location.y ;

        weapon.x2 = location.x + gun_x; // then updates to point at this point, the angle from current location and target, plus current location
        weapon.y2 = location.y + gun_y;

        bound.x = location.x - (float)baseWidth /2;
        bound.y = location.y - (float)baseHeight /2;


        int canvasOffset = GameManager.getInstance().getWin().getHeight() -  GameManager.win.canvas.getHeight();


        if (location.x > GameManager.win.canvas.getWidth() - baseWidth)
            setVelocityX(-1f * UNIT_MOVE);
        else if (location.x - baseWidth < 0)
            setVelocityX(1f * UNIT_MOVE);
        if (location.y > GameManager.win.canvas.getHeight() - baseHeight)
            setVelocityY(-1f * UNIT_MOVE);
        else if (location.y - baseHeight < 0)
            setVelocityY(1f * UNIT_MOVE);

        if (location.y >= (GameManager.win.getHeight() - canvasOffset)){
            System.out.println(GameManager.win.getHeight() -  GameManager.win.canvas.getHeight());
            setVelocityY(-1);
        }
        location.x += velocity.x * UNIT_MOVE;
        location.y += velocity.y * UNIT_MOVE;
    }




    public Line2D.Float getWeapon(){ return weapon; }

    public int getBaseWidth(){
        return baseWidth;
    }

    public int getBaseHeight(){
        return baseHeight;
    }

    @Override
    public double getCollisionRadius() {
        return baseWidth;
    }

}
