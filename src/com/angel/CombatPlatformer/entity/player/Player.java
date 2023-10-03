package com.angel.CombatPlatformer.entity.player;

import com.angel.CombatPlatformer.component.Animator;
import com.angel.CombatPlatformer.util.io.KL;
import com.angel.CombatPlatformer.window.WindowConstants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;


public class Player {
    private KL keyListener = KL.getKeyListener();
    private Point2D.Double position = null;

    private boolean facingLeft = false;

    private boolean attacking = false;
    private double attackingTime = 0;
    private double attackDuration = 0.59;

    private Animator animator;

    public Player(){
        position = new Point2D.Double(WindowConstants.SCREEN_WIDTH/2.0 ,WindowConstants.SCREEN_HEIGHT/2.0);



        animator = new Animator();
        animator.createAnimation(
                PlayerConstants.idleAnimationID,
                PlayerConstants.idle_animationPath,
                PlayerConstants.idle_animationPos,
                PlayerConstants.idle_xOffset,
                PlayerConstants.idle_yOffset,
                PlayerConstants.idle_ScaleFactor
        );
        animator.createAnimation(
                PlayerConstants.Attack1AnimationID,
                PlayerConstants.Attack1_animationPath,
                PlayerConstants.Attack1_animationPos,
                PlayerConstants.Attack1_xOffset,
                PlayerConstants.Attack1_yOffset,
                PlayerConstants.Attack1_ScaleFactor
        );
    }




    private void HandleInput(double deltaTime){

        if(attacking){
            attackingTime += deltaTime;
            if (attackingTime>= attackDuration){
                animator.changeAnimationTo(PlayerConstants.idleAnimationID);
                attacking = false;
                attackingTime = 0;
            }
        }else if (keyListener.isKeyDown(KeyEvent.VK_RIGHT)){
            animator.changeAnimationTo(PlayerConstants.Attack1AnimationID);
            facingLeft = false;
            attacking = true;
        } else if (keyListener.isKeyDown(KeyEvent.VK_LEFT)) {
            animator.changeAnimationTo(PlayerConstants.Attack1AnimationID);
            facingLeft = true;
            attacking = true;
        } else{
            Point2D.Double movementVector = GetMovementVector();


            if(movementVector.x == 1.0 && movementVector.y == 1.0){
                double movementVectorMagnitude = Math.sqrt(movementVector.x * movementVector.x + movementVector.y * movementVector.y);

                movementVector.x = movementVector.x / movementVectorMagnitude;
                movementVector.y = movementVector.y / movementVectorMagnitude;
            }


            position.x += movementVector.x * PlayerConstants.PLAYER_SPEED * deltaTime;
            position.y += movementVector.y * PlayerConstants.PLAYER_SPEED * deltaTime;
            if (movementVector.x < 0){
                facingLeft = true;
            }
            if (movementVector.x > 0){
                facingLeft = false;
            }

        }



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
