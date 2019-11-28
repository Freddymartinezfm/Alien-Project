package model.bee;

import model.arrangement.Formation;

import java.awt.geom.Point2D;

public class BeeInFormation implements BeeAnimStrategy {
    private Bee context;
    public Formation formation;

    BeeInFormation(Bee context){
        formation = new Formation();
        this.context = context;
    }

    @Override
    public void animate() {
        Point2D.Float formationPos = new Point2D.Float(150, 0);
        formationPos.y += (float) ( formation.gridLocation.y + formation.gridLocation.y  * 2 * (context.index/4) * (Math.pow(-1, (context.index % 2 + 1))));
        formationPos.x += (float) (formation.gridLocation.x * ((context.index % 4) / 2.0));

        Point2D.Float target = new Point2D.Float(Math.abs(formationPos.x), Math.abs(formationPos.y));
        double rad = Math.atan2(target.y - context.location.y , target.x - context.location.x );
        double dist = target.distance(context.location);
        if (formationPos.distance(context.location) < 25) {
            System.out.println("TEst");
            context.setState(context.MAIN_PLAY);
            context.location.x ++;
         }
        context.location.x += 2.0  * Math.cos(rad);
        context.location.y += 2.0  * Math.sin(rad);






//

    }
}
