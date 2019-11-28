package model.arrangement;


/**
 * COMPLETE
 */


import java.awt.geom.Point2D;
import java.util.ArrayList;
public  class Spline {
    public static  ArrayList<Point2D.Float> points;
    public  ArrayList<Point2D.Float> sampledPath;
    public ArrayList<Float> samples;

    public  Spline() {
        points = new ArrayList<>();
        sampledPath = new ArrayList<>();
        samples = new ArrayList<>();
    }

    public void addCurve(Point2D.Float p0, Point2D.Float p1, Point2D.Float p2, Point2D.Float p3){
        points.add(p0);
        points.add(p1);
        points.add(p2);
        points.add(p3);
    }

    public Point2D.Float getSplinePoint(float t) {
        int p0;
        int p1;
        int p2;
        int p3;

        p1 = (int)Math.floor(t) + 1;
        p2 = p1 + 1;
        p3 = p2 + 1;
        p0 = p1 - 1;
        t = t - (int)Math.floor(t);

        float tt = t * t;
        float ttt = tt * t;

        float q1 = -ttt + 2.0f * tt - t;
        float q2 = 3.0f * ttt - 5.0f * tt + 2.0f;
        float q3 = -3.0f * ttt + 4.0f * tt + t;
        float q4 = ttt - tt;

        float tx =  (.5f * points.get(p0).x * q1)
                    + .5f * (points.get(p1).x * q2)
                    + .5f * (points.get(p2).x * q3)
                    + .5f * (points.get(p3).x * q4);
        float ty =  (.5f * points.get(p0).y * q1)
                    + .5f * (points.get(p1).y * q2)
                    + .5f * (points.get(p2).y * q3)
                    + .5f * (points.get(p3).y * q4);
        return new Point2D.Float(tx,ty);
    }
}