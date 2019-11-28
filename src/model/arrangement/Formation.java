package model.arrangement;

import controller.GameManager;

import java.awt.geom.Point2D;

public class Formation {
    public Point2D.Float gridLocation;

    public Formation() {
        gridLocation = new Point2D.Float(GameManager.win.canvas.getWidth() * .30f ,GameManager.win.canvas.getHeight() * .25f);
    }
}
