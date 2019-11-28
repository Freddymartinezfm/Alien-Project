package model.bee;

import controller.GameManager;

public class BeeHitAnimation implements BeeAnimStrategy {
    private Bee context;

    public BeeHitAnimation(Bee context) {
        this.context = context;
    }

    @Override
    public void animate() {

        GameManager gm  = GameManager.getInstance();
        context.texture =  GameManager.sheet.getTexture("Explode5");
        System.out.println("Bee explode");



    }
}
