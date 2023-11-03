package com.angel.CombatPlatformer.entity.Enemy;

import com.angel.CombatPlatformer.component.Animator;
import com.angel.CombatPlatformer.component.Health;
import com.angel.CombatPlatformer.component.Transform;
import com.angel.CombatPlatformer.entity.Enemy.Enemy_Constants;
import com.angel.CombatPlatformer.entity.Entity;
import com.angel.CombatPlatformer.util.Vector2D;
import com.angel.CombatPlatformer.window.WindowConstants;
import com.angel.CombatPlatformer.window.scenes.GameScene;

import java.awt.*;

public class Dummy extends Entity {

    private enum State{
        Dying,
        Alive
    }

    //Player TransformRef
    Transform playerTransform = null;


    //animation variables
    private boolean facingLeft = false;
    private final Animator animator;

    //movement variables
    private double y_Velocity = 0.0;
    private Vector2D movementVector = new Vector2D();

    private double iframeTime = 0.f;
    private State state = State.Alive;


    public Dummy(Transform playerTransform) {
        this.playerTransform = playerTransform;

        Vector2D position = new Vector2D(WindowConstants.SCREEN_WIDTH / 2, WindowConstants.SCREEN_HEIGHT - Enemy_Constants.DUMMY_HEIGHT);
        Vector2D rotation = new Vector2D();
        Vector2D size = new Vector2D(Enemy_Constants.DUMMY_WIDTH, Enemy_Constants.DUMMY_HEIGHT);

        transform = new Transform(position, rotation, size);


        animator = new Animator(0.075);
        animator.addAnimation(Enemy_Constants.DUMMY_IDLE_ANIMATION, Enemy_Constants.DUMMY_IDLE_ANIMATION_ID);
        animator.addAnimation(Enemy_Constants.DUMMY_HURT_ANIMATION, Enemy_Constants.DUMMY_HURT_ANIMATION_ID);

        health = new Health(
                100.0,
                (int) (WindowConstants.SCREEN_UNIT * 0.4),
                (int) -WindowConstants.SCREEN_UNIT,
                this
        );

    }

    @Override
    public void destroy(){

        GameScene.enemies.remove(this);
    }

    @Override
    public void takeDamage(double d) {
        if (iframeTime <= 0) {
            health.takeDamage(d);
            iframeTime = 0.3;
            animator.changeAnimationTo(Enemy_Constants.DUMMY_HURT_ANIMATION_ID);
        }
    }

    public void init() {

    }

    @Override
    public void update(Double deltaTime) {
        animator.update(deltaTime);
        facingLeft = playerTransform.position.x < transform.position.x;
        iframeTime -= deltaTime;
        if (iframeTime <= 0) {
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


        health.drawHealthBar(g, (int) transform.position.x, (int) transform.position.y);
    }
}