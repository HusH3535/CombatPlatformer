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

    private boolean attacking = false;
    private double attackingTime = 0;
    private double attackDuration = 0.59;

    private Animator animator;

    public Player(){
        position = new Point2D.Double(WindowConstants.SCREEN_WIDTH/2.0 ,WindowConstants.SCREEN_HEIGHT/2.0);



        animator = new Animator();
        animator.createAnimation(
                PlayerConstants.IDLE_ANIMATION_ID,
                PlayerConstants.IDLE_ANIMATION_PATH,
                PlayerConstants.IDLE_ANIMATION_POS,
                PlayerConstants.IDLE_X_OFFSET,
                PlayerConstants.IDLE_Y_OFFSET,
                PlayerConstants.IDLE_SCALE_FACTOR
        );
        animator.createAnimation(
                PlayerConstants.RUN_ANIMATION_ID,
                PlayerConstants.RUN_ANIMATION_PATH,
                PlayerConstants.RUN_ANIMATION_POS,
                PlayerConstants.RUN_X_OFFSET,
                PlayerConstants.RUN_Y_OFFSET,
                PlayerConstants.RUN_SCALE_FACTOR
        );
        animator.createAnimation(
                PlayerConstants.ATTACK_1_ANIMATION_ID,
                PlayerConstants.ATTACK_1_ANIMATION_PATH,
                PlayerConstants.ATTACK_1_ANIMATION_POS,
                PlayerConstants.ATTACK_1_X_OFFSET,
                PlayerConstants.ATTACK_1_Y_OFFSET,
                PlayerConstants.ATTACK_1_SCALE_FACTOR
        );
    }



    private void HandleInput(double deltaTime) {
        

        switch (state) {
            case Attacking -> HandleAttackCD(deltaTime);



            case OffGround -> {}



            case Default -> {
                if (keyListener.isKeyDown(KeyEvent.VK_RIGHT)) {
                    animator.changeAnimationTo(PlayerConstants.ATTACK_1_ANIMATION_ID);
                    facingLeft = false;
                    state = Player_State.Attacking;
                } else if (keyListener.isKeyDown(KeyEvent.VK_LEFT)) {
                    animator.changeAnimationTo(PlayerConstants.ATTACK_1_ANIMATION_ID);
                    facingLeft = true;
                    state = Player_State.Attacking;
                }else{
                    HandleMovement(deltaTime);
                }
            }
        }
    }

    private void HandleAttackCD(double deltaTime){
        attackingTime += deltaTime;
        if (attackingTime>= attackDuration){
            animator.changeAnimationTo(PlayerConstants.IDLE_ANIMATION_ID);
            state = Player_State.Default;
            attackingTime = 0;
        }
    }

    private void HandleMovement(Double deltaTime){
        Point2D.Double movementVector = GetMovementVector();

        double previousPosX = position.x;
        double previousPosY = position.y;

        if(movementVector.x == 1.0 && movementVector.y == 1.0){
            double movementVectorMagnitude = Math.sqrt(movementVector.x * movementVector.x + movementVector.y * movementVector.y);

            movementVector.x = movementVector.x / movementVectorMagnitude;
            movementVector.y = movementVector.y / movementVectorMagnitude;
        }

        position.x += movementVector.x * PlayerConstants.PLAYER_SPEED * deltaTime;
        position.y += movementVector.y * PlayerConstants.PLAYER_SPEED * deltaTime;

        boolean ScreenBottomBound = position.y + PlayerConstants.PLAYER_HEIGHT >= WindowConstants.SCREEN_HEIGHT,
                ScreenTopBound    = position.y <= 0,
                ScreenLeftBound   = position.x <= 0,
                ScreenRightBound  = position.x + PlayerConstants.PLAYER_WIDTH >= WindowConstants.SCREEN_WIDTH;

        position.x = ScreenLeftBound   ? previousPosX: position.x;
        position.x = ScreenRightBound  ? previousPosX: position.x;
        position.y = ScreenTopBound    ? previousPosY: position.y;
        position.y = ScreenBottomBound ? previousPosY: position.y;


        running = movementVector.x != 0;

        if(running ){
            animator.changeAnimationNotReset(PlayerConstants.RUN_ANIMATION_ID);
        }else{
            animator.changeAnimationTo(PlayerConstants.IDLE_ANIMATION_ID);
        }

        facingLeft = movementVector.x != 0?  movementVector.x < 0:  facingLeft;
    }


    private Point2D.Double GetMovementVector(){

        Point2D.Double movementVector = new Point2D.Double();

        if(keyListener.isKeyDown(KeyEvent.VK_W)){
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
