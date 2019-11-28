package model.butterfly;


import model.bee.Bee;
import model.bee.BeeAnimStrategy;

public class ButterFlyingInComplete implements ButterflyAnimStrategy {
    Butterfly context;

    public ButterFlyingInComplete(Butterfly context){
        this.context = context;
    }

    @Override
    public void animate() {
        context.location.x++;
    }
}
