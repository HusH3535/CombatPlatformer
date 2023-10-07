package com.angel.CombatPlatformer.entity.Enemy;

import com.angel.CombatPlatformer.util.Animation;
import com.angel.CombatPlatformer.util.Rect;
import com.angel.CombatPlatformer.window.WindowConstants;

import java.awt.*;

public class Enemy_Constants {

    //region Dimension variables
    //==============================================================================

    public static final int DUMMY_HEIGHT = (int) (WindowConstants.SCREEN_WIDTH*(0.08));
    public static final int DUMMY_WIDTH = (int) (DUMMY_HEIGHT*(1.0/1.5));

    //endregion


    //region movement variables
    //==============================================================================
    public static final double DUMMY_SPEED = WindowConstants.SCREEN_WIDTH*(0.35);
    public static final Color characterColor = Color.RED;


    public static double MAX_GRAVITY_VELOCITY = WindowConstants.SCREEN_UNIT;
    public static double GRAVITY_ACCELERATION = MAX_GRAVITY_VELOCITY/2;

    public static double JUMPING_VELOCITY = -WindowConstants.SCREEN_UNIT * 0.2;


    public static final double  STARTING_ATTACKSPEED = 0.3;

    //endregion
    //200px per second





    //region sprite variables
    //==============================================================================

    public final static String DUMMY_IDLE_ANIMATION_ID        = "idle";
    public final static String DUMMY_RUN_ANIMATION_ID         = "Run";
    public final static String DUMMY_ATTACK_1_ANIMATION_ID    = "Attack1";
    public final static String DUMMY_ATTACK_UP_ANIMATION_ID    = "AttackUp";
    public final static String DUMMY_JUMP_ANIMATION_ID       = "Jump";
    public final static String DUMMY_FALL_ANIMATION_ID       = "FALL";

    public final static int DUMMY_IDLE_X_OFFSET = (int) (- 3 * WindowConstants.SCREEN_UNIT);
    public final static int DUMMY_IDLE_Y_OFFSET = (int) (- 2.2  * WindowConstants.SCREEN_UNIT);
    public final static double DUMMY_IDLE_SCALE_FACTOR =   WindowConstants.SCREEN_UNIT * .25 ;
    public final static String DUMMY_IDLE_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/dummy/Dummy_Idle.png";
    public final static Rect[] DUMMY_IDLE_ANIMATION_POS = {
            new Rect(48*0,0,48,48),
            new Rect(48*1,0,48,48),
            new Rect(48*2,0,48,48),
            new Rect(48*3,0,48,48),
            new Rect(48*4,0,48,48),
            new Rect(48*5,0,48,48),
            new Rect(48*6,0,48,48),
            new Rect(48*7,0,48,48),
            new Rect(48*8,0,48,48),
            new Rect(48*9,0,48,48)
    };

    public final static Animation DUMMY_IDLE_ANIMATION = new Animation(
            DUMMY_IDLE_ANIMATION_PATH,
            DUMMY_IDLE_ANIMATION_POS,
            DUMMY_IDLE_X_OFFSET,
            DUMMY_IDLE_Y_OFFSET,
            DUMMY_IDLE_SCALE_FACTOR
    );
}
