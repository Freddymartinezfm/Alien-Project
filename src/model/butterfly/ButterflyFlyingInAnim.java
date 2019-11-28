package model.butterfly;

import model.GameFigure;
import model.arrangement.EnemyPath;
import model.bee.Bee;
import model.bee.BeeAnimStrategy;

import java.awt.geom.Point2D;

public class ButterflyFlyingInAnim implements ButterflyAnimStrategy {
    private Butterfly context;

    ButterflyFlyingInAnim(Butterfly context){ this.context = context; }

    @Override
    public void animate() {
        Point2D.Float target = new Point2D.Float(EnemyPath.paths.get(context.currentPath).get(context.currentWaypoint + 1 ).x, EnemyPath.paths.get(context.currentPath).get(context.currentWaypoint + 1).y);
        double rad = Math.atan2(target.y - context.location.y, target.x - context.location.x);
        double dist = target.distance(context.location);
        if (target.distance(context.location) < 5) {
            context.currentWaypoint++;
         }
        context.location.x += GameFigure.UNIT_MOVE * Math.cos(rad);
        context.location.y += GameFigure.UNIT_MOVE * Math.sin(rad);
    }
}
