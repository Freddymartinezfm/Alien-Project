package model.spaceship;

import javax.sound.sampled.FloatControl;

public class SpaceShipFlying implements Animation {
    private BaseShip context;

    public SpaceShipFlying(BaseShip context){
        this.context = context;
    }

    @Override
    public void animate() {

        context.weapon_len += 5;


    }
}
