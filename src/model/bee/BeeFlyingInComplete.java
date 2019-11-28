package model.bee;


public class BeeFlyingInComplete implements BeeAnimStrategy{
    Bee context;

    public BeeFlyingInComplete(Bee context){
        this.context = context;
    }

    @Override
    public void animate() {
        context.location.x++;
    }
}
