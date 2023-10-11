package com.angel.CombatPlatformer.entity.player;

import com.angel.CombatPlatformer.component.Animator;
import com.angel.CombatPlatformer.component.AttackManager;
import com.angel.CombatPlatformer.component.Health;
import com.angel.CombatPlatformer.component.Transform;
import com.angel.CombatPlatformer.entity.Entity;
import com.angel.CombatPlatformer.util.Attack;
import com.angel.CombatPlatformer.util.Rect;
import com.angel.CombatPlatformer.util.Vector2D;
import com.angel.CombatPlatformer.util.io.KL;
import com.angel.CombatPlatformer.window.WindowConstants;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player implements Entity {
    //private unit
    private double unit = WindowConstants.SCREEN_UNIT;
    //Transform
    public Transform transform = null;
    //components
    private final KL keyListener = KL.getKeyListener();
    private Player_State state = Player_State.Default;
    private AttackManager attackManager;
    private Health health;


    //animation variables
    private boolean facingLeft = false;
    private final Animator animator;

    //movement variables
    private double y_Velocity = 0.0;
    private Vector2D movementVector = new Vector2D();


    //attacking variables
    private double attackingTime = 0;
    private double attackSpeed = PlayerConstants.STARTING_ATTACKSPEED;
    private double currentAttack_Speed = attackSpeed;



    public Player(){
        Vector2D position = new Vector2D(WindowConstants.SCREEN_WIDTH/2.0 ,WindowConstants.SCREEN_HEIGHT/2.0);
        Vector2D rotation = new Vector2D();
        Vector2D size = new Vector2D(PlayerConstants.PLAYER_WIDTH,PlayerConstants.PLAYER_HEIGHT);
        
        transform = new Transform(position, rotation, size);

        animator = new Animator(0.075);

        animator.addAnimation(PlayerConstants.IDLE_ANIMATION,       PlayerConstants.IDLE_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.RUN_ANIMATION,        PlayerConstants.RUN_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.ATTACK_1_ANIMATION,   PlayerConstants.ATTACK_1_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.ATTACK_UP_ANIMATION,  PlayerConstants.ATTACK_UP_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.JUMP_ANIMATION,       PlayerConstants.JUMP_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.FALL_ANIMATION,       PlayerConstants.FALL_ANIMATION_ID);

        health = new Health(
                100.0,
                (int) (unit * 0.4),
                (int) - unit,
                this
        );

        attackManager = new AttackManager();
        attackManager.addAttack(
                0,
                new Attack(
                        (int)unit * 6,
                        (int)unit * 2,
                        (int)unit * 9,
                        (int)unit * 6,
                        30,
                        0.3
                )
        );

    }

    private void HandleInput(double deltaTime) {

        if (keyListener.isKeyDown(KeyEvent.VK_RIGHT)) {
            attackManager.performAttack(0, (int)transform.position.x,(int) transform.position.y);
            SetAttacking(
                    PlayerConstants.ATTACK_1_ANIMATION_ID,
                    (attackSpeed /PlayerConstants.ATTACK_1_ANIMATION_POS.length) * 1.1

            );

            currentAttack_Speed = attackSpeed;
            facingLeft = false;
        } else if (keyListener.isKeyDown(KeyEvent.VK_LEFT)) {

            SetAttacking(
                    PlayerConstants.ATTACK_1_ANIMATION_ID,
                    (attackSpeed /PlayerConstants.ATTACK_1_ANIMATION_POS.length) * 1.1
            );

            currentAttack_Speed = attackSpeed;
            facingLeft = true;
        }else if(keyListener.isKeyDown(KeyEvent.VK_UP)) {

            SetAttacking(
                    PlayerConstants.ATTACK_UP_ANIMATION_ID,
                    (attackSpeed * 1.5 / PlayerConstants.ATTACK_UP_ANIMATION_POS.length) * 1.1
            );

            currentAttack_Speed = attackSpeed * 1.5;

        }else{
            HandleMovement(deltaTime);
        }
    }

    private void SetAttacking(String animationID, double frameTime){
        animator.changeAnimationTo(animationID,frameTime);
        state = Player_State.Attacking;
    }

    private void HandleAttackCD(double deltaTime){
        attackingTime += deltaTime;
        if (attackingTime>= currentAttack_Speed){
            animator.changeAnimationTo(PlayerConstants.IDLE_ANIMATION_ID);
            state = Player_State.Default;
            attackingTime = 0;
        }
    }

    private void HandleMovement(Double deltaTime){
        boolean playerGrounded = transform.position.y + transform.size.y >= WindowConstants.SCREEN_HEIGHT;

        //Checks If the player is grounded and sets State
        state = !playerGrounded ? Player_State.OffGround : Player_State.Default ;

        //If The player is Grounded Allows to jump
        if (state == Player_State.Default && movementVector.y < 0){
            y_Velocity = PlayerConstants.JUMPING_VELOCITY;
        }

        transform.position.x += movementVector.x * PlayerConstants.PLAYER_SPEED * deltaTime;
        boolean running = movementVector.x != 0;

        HandleGravity(deltaTime);
        HandleScreenBounds();


        //Chooses animation depending on movement
        switch (state) {
            case Default -> {
                if(running){
                    animator.changeAnimationNotReset(PlayerConstants.RUN_ANIMATION_ID);
                }else{
                    animator.changeAnimationTo(PlayerConstants.IDLE_ANIMATION_ID);
                }
            }

            case OffGround -> {
                if (y_Velocity < 0) {
                    animator.changeAnimationTo(PlayerConstants.JUMP_ANIMATION_ID);
                } else {
                    animator.changeAnimationTo(PlayerConstants.FALL_ANIMATION_ID);
                }
            }
        }


        facingLeft = movementVector.x != 0?  movementVector.x < 0:  facingLeft;
    }

    private void HandleScreenBounds(){
        boolean ScreenBottomBound = transform.position.y + transform.size.y >= WindowConstants.SCREEN_HEIGHT,
                ScreenTopBound    = transform.position.y <= WindowConstants.INSET_SIZE,
                ScreenLeftBound   = transform.position.x <= 0,
                ScreenRightBound  = transform.position.x + transform.size.x >= WindowConstants.SCREEN_WIDTH;

        transform.position.x = ScreenLeftBound   ? 0                                                              : transform.position.x;
        transform.position.x = ScreenRightBound  ? WindowConstants.SCREEN_WIDTH - transform.size.x    : transform.position.x;
        transform.position.y = ScreenTopBound    ? 0                                                              : transform.position.y;
        transform.position.y = ScreenBottomBound ? WindowConstants.SCREEN_HEIGHT - transform.size.y  : transform.position.y;
    }

    private void HandleGravity(Double deltaTime){
        y_Velocity += PlayerConstants.GRAVITY_ACCELERATION * deltaTime;
        y_Velocity = Math.min(y_Velocity, PlayerConstants.MAX_GRAVITY_VELOCITY);

        transform.position.y += y_Velocity * deltaTime;
    }


    private void  UpdateMovementVector(){

        Vector2D newMovementVector = new Vector2D();

        if(keyListener.isKeyDown(KeyEvent.VK_W) || keyListener.isKeyDown(KeyEvent.VK_SPACE)){
            newMovementVector.y -= 1.0;
        }
        if(keyListener.isKeyDown(KeyEvent.VK_S)){
            newMovementVector.y += 1.0;
        }
        if(keyListener.isKeyDown(KeyEvent.VK_A)){
            newMovementVector.x -= 1.0;
        }
        if(keyListener.isKeyDown(KeyEvent.VK_D)){
            newMovementVector.x += 1.0;
        }
        this.movementVector = newMovementVector;
    }

    public void draw(Graphics g){
        g.setColor(PlayerConstants.characterColor);
        g.drawRect((int) transform.position.x, (int) transform.position.y, (int) transform.size.x, (int) transform.size.y);

        if(animator.hasAnimations()){
            if (!facingLeft){
                animator.RenderCurrentSprite(g, (int) transform.position.x, (int) transform.position.y);
            }else{
                animator.RenderCurrentSpriteFlipVer(g, (int) transform.position.x, (int) transform.position.y);
            }
        }

        health.drawHealthBar(g, (int) transform.position.x, (int) transform.position.y);
        attackManager.draw(g);

    }

    @Override
    public void update(Double deltaTime){

        UpdateMovementVector();

        switch (state) {
            case Attacking  ->  HandleAttackCD(deltaTime);

            case OffGround  ->  HandleMovement(deltaTime);

            case Default    ->  HandleInput(deltaTime);

        }


        animator.update(deltaTime);
        attackManager.update(deltaTime);
    }
}
