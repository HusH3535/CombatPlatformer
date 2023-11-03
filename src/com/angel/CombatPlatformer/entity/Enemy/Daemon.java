package com.angel.CombatPlatformer.entity.Enemy;

import com.angel.CombatPlatformer.component.Animator;
import com.angel.CombatPlatformer.component.Health;
import com.angel.CombatPlatformer.component.Transform;
import com.angel.CombatPlatformer.entity.Entity;
import com.angel.CombatPlatformer.util.Vector2D;
import com.angel.CombatPlatformer.window.WindowConstants;
import com.angel.CombatPlatformer.window.scenes.GameScene;

import java.awt.*;
import java.util.Random;

public class Daemon extends Entity {

    private double unit = WindowConstants.SCREEN_UNIT;
    private enum AIState{
        Chasing,
        Attacking,
        Dying
    }


    private AIState state = AIState.Chasing;
    private double iframeTime = 0.0;
    private double aTimeRemaining = 0.0;

    private boolean facingLeft = false;
    private final Animator animator;
    private Transform playerTransform;


    public Daemon(Transform player){
        Random rn = new Random();

        playerTransform  = player;

        Vector2D position = new Vector2D(rn.nextInt(WindowConstants.SCREEN_WIDTH )+1, rn.nextInt(WindowConstants.SCREEN_HEIGHT - Enemy_Constants.DUMMY_HEIGHT)+1);
        Vector2D rotation = new Vector2D();
        Vector2D size = new Vector2D(DaemonConstants.size.x,DaemonConstants.size.y);
        transform = new Transform(position, rotation, size);

        animator = new Animator(0.15);
        animator.addAnimation(DaemonConstants.IDLE_ANIMATION,DaemonConstants.IDLE_A_ID);

        health = new Health(
                100.0,
                (int) (unit * 0.4),
                (int) -unit,
                this
        );

    }

    @Override
    public void takeDamage(double d) {
        if (iframeTime <= 0) {
            health.takeDamage(d);
            iframeTime = 0.3;
            aTimeRemaining = 0.3;
        }
    }

    @Override
    public void destroy(){

        GameScene.enemies.remove(this);
    }

    @Override
    public void update(Double deltaTime) {
        animator.update(deltaTime);
        facingLeft = playerTransform.position.x < transform.position.x;
        iframeTime -= deltaTime;
        //todo: chase players not working
        if (state == AIState.Chasing){
            Vector2D tM = new Vector2D(
                    this.transform.position.x+(this.transform.size.x/2),
                    this.transform.position.y+(this.transform.size.y/2)
            );
            Vector2D pM = new Vector2D(
                    playerTransform.position.x+(playerTransform.size.x/2),
                    playerTransform.position.y+(playerTransform.size.y/2)
            );
            Vector2D tToP = tM.getVectorTo(pM);
            double distanceToPlayer = tToP.getMagnitude();
            if (distanceToPlayer > unit * 8){
                tToP.normalize();
                tToP.multiply(deltaTime*DaemonConstants.STARTING_SPEED);
                this.transform.position.x += tToP.x;
                this.transform.position.y += tToP.y;

            }


        }
        if (iframeTime <= 0 && state != AIState.Chasing) {
            state = AIState.Chasing;
            animator.changeAnimationTo(Enemy_Constants.DUMMY_IDLE_ANIMATION_ID);
        }
    }

    @Override
    public void draw(Graphics g) {

        if (animator.hasAnimations()) {
            if (!facingLeft) {
                animator.RenderCurrentSprite(g, (int) transform.position.x, (int) transform.position.y);
            } else {
                animator.RenderCurrentSpriteFlipVer(g, (int) transform.position.x, (int) transform.position.y);
            }
        }

        g.setColor(Color.red);

        g.drawRect( (int)transform.position.x,
                    (int)transform.position.y,
                    (int)transform.size.x,
                    (int)transform.size.y
        );

        health.drawHealthBar(g, (int) transform.position.x, (int) transform.position.y);

    }


}
