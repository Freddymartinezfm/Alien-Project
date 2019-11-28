package model.arrangement;
import controller.GameManager;
import model.GameFigure;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class EnemyPath {

    public static ArrayList<ArrayList<Point2D.Float>> paths;


    public static void createPaths() {
        paths = new ArrayList<>();
        ArrayList<Point2D.Float> sampledPath = new ArrayList<>();
        ArrayList<Point2D.Float> sampledPath2 = new ArrayList<>();
        Spline path = new Spline();
        Spline path2 = new Spline();
        ////////////////////////////////////////////

        float midX = (float) (GameManager.win.canvas.getWidth() / 2.0);
        float midY = (float) (GameManager.win.canvas.getHeight() / 2.0);

//        path.addCurve(new Point2D.Float(-400, -10), new Point2D.Float(300, -125), new Point2D.Float(400, 500), new Point2D.Float(150, 325));
//        path.points.add(new Point2D.Float(150, 200));
//        path.points.add(new Point2D.Float(midX + 50, 100)); // bottom contr
//
//
//
//
//        for (float t = 0.0f; t < path.points.size() - 3.0f; t += 0.044) {
//            sampledPath.add(path.getSplinePoint(t));
//        }
//
//        paths.add(sampledPath);

        path.addCurve(new Point2D.Float(-400, -10), new Point2D.Float(300, -10), new Point2D.Float(400, 500), new Point2D.Float(150, 325));
        path.points.add(new Point2D.Float(150, 200));
        path.points.add(new Point2D.Float(midX + 50, 100)); // bottom contr
        for (float t = 0.0f; t < path.points.size() - 3.0f; t += 0.044) {
            sampledPath.add(path.getSplinePoint(t));
        }


        paths.add(sampledPath);


        path2 = new Spline();
        path2.points.add(new Point2D.Float(midX + 50, -10)); // top contrl
        path2.points.add(new Point2D.Float(midX + 50, 100)); // start point
        path2.points.add(new Point2D.Float(50, 30)); // end point
        path2.points.add(new Point2D.Float(midX + 50, 20)); // bottom contr
        path2.points.add(new Point2D.Float(-150, -10));
        for (float t = 0.0f; t < path2.points.size() - 3.0f; t += 0.044) {
            sampledPath2.add(path2.getSplinePoint(t));
        }
        paths.add(sampledPath2);

    }
}
