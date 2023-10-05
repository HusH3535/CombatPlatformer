package com.angel.CombatPlatformer.entity.player;

import com.angel.CombatPlatformer.component.Animator;
import com.angel.CombatPlatformer.util.io.KL;
import com.angel.CombatPlatformer.window.WindowConstants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;


public class Player {
    private KL keyListener = KL.getKeyListener();
    private Point2D.Double position = null;

    private Player_State state = Player_State.Default;

    private boolean facingLeft = false;

    private boolean running = false;

    private double y_Velocity = 0.0;

    private double attackingTime = 0;

    private double attackSpeed = PlayerConstants.STARTING_ATTACKSPEED;
    private double currentAttack_Speed = attackSpeed;

    private Animator animator;

    public Player(){
        position = new Point2D.Double(WindowConstants.SCREEN_WIDTH/2.0 ,WindowConstants.SCREEN_HEIGHT/2.0);

        animator = new Animator(0.075);

        animator.addAnimation(PlayerConstants.IDLE_ANIMATION,       PlayerConstants.IDLE_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.RUN_ANIMATION,        PlayerConstants.RUN_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.ATTACK_1_ANIMATION,   PlayerConstants.ATTACK_1_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.ATTACK_UP_ANIMATION,  PlayerConstants.ATTACK_UP_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.JUMP_ANIMATION,       PlayerConstants.JUMP_ANIMATION_ID);
        animator.addAnimation(PlayerConstants.FALL_ANIMATION,       PlayerConstants.FALL_ANIMATION_ID);

    }

    private void HandleInput(double deltaTime) {
        

        switch (state) {
            case Attacking -> HandleAttackCD(deltaTime);



            case OffGround -> {
                HandleMovement(deltaTime);
            }



            case Default -> {
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
        Point2D.Double movementVector = GetMovementVector();

        boolean playerGrounded = position.y + PlayerConstants.PLAYER_HEIGHT >= WindowConstants.SCREEN_HEIGHT;

        state = !playerGrounded ? Player_State.OffGround : Player_State.Default ;

        double previousPosX = position.x;
        double previousPosY = position.y;

        if(movementVector.x == 1.0 && movementVector.y == 1.0){
            double movementVectorMagnitude = Math.sqrt(movementVector.x * movementVector.x + movementVector.y * movementVector.y);

            movementVector.x = movementVector.x / movementVectorMagnitude;
            movementVector.y = movementVector.y / movementVectorMagnitude;
        }

        if (state == Player_State.OffGround){
            y_Velocity += PlayerConstants.GRAVITY_ACCELERATION * deltaTime;
        }else {
            y_Velocity = movementVector.y < 0 ? PlayerConstants.JUMPING_VELOCITY : y_Velocity + PlayerConstants.GRAVITY_ACCELERATION * deltaTime;
        }

        y_Velocity = Math.min(y_Velocity, PlayerConstants.MAX_GRAVITY_VELOCITY);



        position.x += movementVector.x * PlayerConstants.PLAYER_SPEED * deltaTime;
        position.y += y_Velocity * PlayerConstants.PLAYER_SPEED * deltaTime;

        boolean ScreenBottomBound = position.y + PlayerConstants.PLAYER_HEIGHT >= WindowConstants.SCREEN_HEIGHT,
                ScreenTopBound    = position.y <= WindowConstants.INSET_SIZE,
                ScreenLeftBound   = position.x <= 0,
                ScreenRightBound  = position.x + PlayerConstants.PLAYER_WIDTH >= WindowConstants.SCREEN_WIDTH;

        position.x = ScreenLeftBound   ? previousPosX: position.x;
        position.x = ScreenRightBound  ? previousPosX: position.x;
        position.y = ScreenTopBound    ? previousPosY: position.y;
        position.y = ScreenBottomBound ? WindowConstants.SCREEN_HEIGHT - PlayerConstants.PLAYER_HEIGHT: position.y;


        running = movementVector.x != 0;

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



    private Point2D.Double GetMovementVector(){

        Point2D.Double movementVector = new Point2D.Double();

        if(keyListener.isKeyDown(KeyEvent.VK_W) || keyListener.isKeyDown(KeyEvent.VK_SPACE)){
            movementVector.y -= 1.0;
        }
        if(keyListener.isKeyDown(KeyEvent.VK_S)){
            movementVector.y += 1.0;
        }
        if(keyListener.isKeyDown(KeyEvent.VK_A)){
            movementVector.x -= 1.0;
        }
        if(keyListener.isKeyDown(KeyEvent.VK_D)){
            movementVector.x += 1.0;
        }

        return movementVector;
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
        HandleInput(deltaTime);
        animator.update(deltaTime);
    }



}
