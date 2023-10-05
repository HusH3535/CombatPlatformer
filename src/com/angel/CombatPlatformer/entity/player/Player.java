package com.angel.CombatPlatformer.entity.player;

import com.angel.CombatPlatformer.component.Animator;
import com.angel.CombatPlatformer.util.Vector2D;
import com.angel.CombatPlatformer.util.io.KL;
import com.angel.CombatPlatformer.window.WindowConstants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;


public class Player {
    //Transform
    private Vector2D position = null;
    //components
    private KL keyListener = KL.getKeyListener();
    private Player_State state = Player_State.Default;

    //animation variables
    private boolean facingLeft = false;
    private boolean running = false;

    //movement variables
    private double y_Velocity = 0.0;
    private Vector2D movementVector = new Vector2D();


    //attacking variables
    private double attackingTime = 0;
    private double attackSpeed = PlayerConstants.STARTING_ATTACKSPEED;
    private double currentAttack_Speed = attackSpeed;

    private Animator animator;

    public Player(){
        position = new Vector2D(WindowConstants.SCREEN_WIDTH/2.0 ,WindowConstants.SCREEN_HEIGHT/2.0);

        animator = new Animator(0.075);

        animator.addAnimation(PlayerConstants.IDLE_ANIMATION,       PlayerConstants.IDLE_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.RUN_ANIMATION,        PlayerConstants.RUN_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.ATTACK_1_ANIMATION,   PlayerConstants.ATTACK_1_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.ATTACK_UP_ANIMATION,  PlayerConstants.ATTACK_UP_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.JUMP_ANIMATION,       PlayerConstants.JUMP_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.FALL_ANIMATION,       PlayerConstants.FALL_ANIMATION_ID);

    }

    private void HandleInput(double deltaTime) {

        if (keyListener.isKeyDown(KeyEvent.VK_RIGHT)) {

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
        boolean playerGrounded = position.y + PlayerConstants.PLAYER_HEIGHT >= WindowConstants.SCREEN_HEIGHT;

        //Checks If the player is grounded and sets State
        state = !playerGrounded ? Player_State.OffGround : Player_State.Default ;

        //If The player is Grounded Allows to jump
        if (state == Player_State.Default && movementVector.y < 0){
            y_Velocity = PlayerConstants.JUMPING_VELOCITY;
        }

        position.x += movementVector.x * PlayerConstants.PLAYER_SPEED * deltaTime;
        running = movementVector.x != 0;

        HandleGravity(deltaTime);
        HandleScreenBounds(deltaTime);


        //Chooses animation depending on movement
        switch (state) {
            case Default -> {
                if(running ){
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

    private void HandleScreenBounds(Double deltaTime){
        boolean ScreenBottomBound = position.y + PlayerConstants.PLAYER_HEIGHT >= WindowConstants.SCREEN_HEIGHT,
                ScreenTopBound    = position.y <= WindowConstants.INSET_SIZE,
                ScreenLeftBound   = position.x <= 0,
                ScreenRightBound  = position.x + PlayerConstants.PLAYER_WIDTH >= WindowConstants.SCREEN_WIDTH;

        position.x = ScreenLeftBound   ? 0                                                              : position.x;
        position.x = ScreenRightBound  ? WindowConstants.SCREEN_WIDTH - PlayerConstants.PLAYER_WIDTH    : position.x;
        position.y = ScreenTopBound    ? 0                                                              : position.y;
        position.y = ScreenBottomBound ? WindowConstants.SCREEN_HEIGHT - PlayerConstants.PLAYER_HEIGHT  : position.y;
    }

    private void HandleGravity(Double deltaTime){
        y_Velocity += PlayerConstants.GRAVITY_ACCELERATION * deltaTime;
        y_Velocity = Math.min(y_Velocity, PlayerConstants.MAX_GRAVITY_VELOCITY);

        position.y += y_Velocity * PlayerConstants.PLAYER_SPEED * deltaTime;
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
        g.drawRect((int) position.x, (int) position.y, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);

        if(animator.hasAnimations()){
            if (!facingLeft){
                animator.RenderCurrentSprite(g, (int) position.x, (int) position.y);
            }else{
                animator.RenderCurrentSpriteFlipVer(g, (int) position.x, (int) position.y);
            }
        }


    }

    public void update(double deltaTime){

        UpdateMovementVector();

        switch (state) {
            case Attacking  ->  HandleAttackCD(deltaTime);

            case OffGround  ->  HandleMovement(deltaTime);

            case Default    ->  HandleInput(deltaTime);

        }

        animator.update(deltaTime);
    }
}
