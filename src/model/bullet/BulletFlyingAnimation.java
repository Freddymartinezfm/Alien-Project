package model.bullet;

public class BulletFlyingAnimation implements BulletAnimationStrategy {
    Bullet context;


    public BulletFlyingAnimation(Bullet context) {this.context = context;}



    @Override
    public void animate() {
        double rad = Math.atan2(context.bullet_target.y - context.location.y , context.bullet_target.x - context.location.x); // the angle is returned from rectangular coordinates
        context.bullet_target.x += context.UNIT_MOVE * Math.cos(rad);
        context.bullet_target.y += context.UNIT_MOVE * Math.sin(rad);

    }
}
