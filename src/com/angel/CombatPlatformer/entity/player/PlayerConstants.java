package com.angel.CombatPlatformer.entity.player;

import com.angel.CombatPlatformer.util.Animation;
import com.angel.CombatPlatformer.util.Rect;
import com.angel.CombatPlatformer.window.WindowConstants;

import java.awt.*;

public class PlayerConstants {

    //region Dimension variables
    //==============================================================================

    public static final int PLAYER_HEIGHT = (int) (WindowConstants.SCREEN_WIDTH*(1.0/10));
    public static final int PLAYER_WIDTH = (int) (PLAYER_HEIGHT*(1.0/1.5));

    //endregion


    //region movement variables
    //==============================================================================
    public static final double PLAYER_SPEED = WindowConstants.SCREEN_WIDTH*(0.35);
    public static final Color characterColor = Color.GREEN;


    public static double MAX_GRAVITY_VELOCITY = WindowConstants.SCREEN_UNIT;
    public static double GRAVITY_ACCELERATION = MAX_GRAVITY_VELOCITY/2;

    public static double JUMPING_VELOCITY = -WindowConstants.SCREEN_UNIT * 0.2;


    public static final double  STARTING_ATTACKSPEED = 0.3;

    //endregion
    //200px per second





    //region sprite variables
    //==============================================================================

    public final static String IDLE_ANIMATION_ID        = "idle";
    public final static String RUN_ANIMATION_ID         = "Run";
    public final static String ATTACK_1_ANIMATION_ID    = "Attack1";
    public final static String ATTACK_UP_ANIMATION_ID    = "AttackUp";
    public final static String JUMP_ANIMATION_ID       = "Jump";
    public final static String FALL_ANIMATION_ID       = "FALL";


    public final static int IDLE_X_OFFSET = (int) (- 10 * WindowConstants.SCREEN_UNIT);
    public final static int IDLE_Y_OFFSET = (int) (- 8  * WindowConstants.SCREEN_UNIT);
    public final static double IDLE_SCALE_FACTOR =   WindowConstants.SCREEN_UNIT / 6 ;
    public final static String IDLE_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/mainCharacter/Idle.png";
    public final static Rect[] IDLE_ANIMATION_POS = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111),
            new Rect(160*2,0,160,111),
            new Rect(160*3,0,160,111),
            new Rect(160*4,0,160,111),
            new Rect(160*5,0,160,111),
            new Rect(160*6,0,160,111),
            new Rect(160*7,0,160,111)
    };

    public final static int RUN_X_OFFSET = (int) (- 10 * WindowConstants.SCREEN_UNIT);
    public final static int RUN_Y_OFFSET = (int) (- 8  * WindowConstants.SCREEN_UNIT);
    public final static double RUN_SCALE_FACTOR = WindowConstants.SCREEN_UNIT / 6 ;
    public final static String RUN_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/mainCharacter/run.png";
    public final static Rect[] RUN_ANIMATION_POS = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111),
            new Rect(160*2,0,160,111),
            new Rect(160*3,0,160,111),
            new Rect(160*4,0,160,111),
            new Rect(160*5,0,160,111),
            new Rect(160*6,0,160,111),
            new Rect(160*7,0,160,111)
    };

    public final static int ATTACK_1_X_OFFSET = (int) (- 10 * WindowConstants.SCREEN_UNIT);
    public final static int ATTACK_1_Y_OFFSET = (int) (- 8  * WindowConstants.SCREEN_UNIT);;
    public final static double ATTACK_1_SCALE_FACTOR = WindowConstants.SCREEN_UNIT / 6 ;
    public final static String ATTACK_1_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/mainCharacter/Attack1.png";
    public final static Rect[] ATTACK_1_ANIMATION_POS = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111),
            new Rect(160*2,0,160,111),
            new Rect(160*3,0,160,111)
    };

    public final static int ATTACK_UP_X_OFFSET = (int) (- 10 * WindowConstants.SCREEN_UNIT);
    public final static int ATTACK_UP_Y_OFFSET = (int) (- 8  * WindowConstants.SCREEN_UNIT);;
    public final static double ATTACK_UP_SCALE_FACTOR = WindowConstants.SCREEN_UNIT / 6 ;
    public final static String ATTACK_UP_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/mainCharacter/Attack3.png";
    public final static Rect[] ATTACK_UP_ANIMATION_POS = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111),
            new Rect(160*2,0,160,111),
            new Rect(160*3,0,160,111)
    };

    public final static int JUMP_X_OFFSET = (int) (- 10 * WindowConstants.SCREEN_UNIT);
    public final static int JUMP_Y_OFFSET = (int) (- 8  * WindowConstants.SCREEN_UNIT);;
    public final static double JUMP_SCALE_FACTOR = WindowConstants.SCREEN_UNIT / 6 ;
    public final static String JUMP_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/mainCharacter/Jump.png";
    public final static Rect[] JUMP_ANIMATION_POS = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111)
    };

    public final static int FALL_X_OFFSET = (int) (- 10 * WindowConstants.SCREEN_UNIT);
    public final static int FALL_Y_OFFSET = (int) (- 8  * WindowConstants.SCREEN_UNIT);;
    public final static double FALL_SCALE_FACTOR = WindowConstants.SCREEN_UNIT / 6 ;
    public final static String FALL_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/mainCharacter/Fall.png";
    public final static Rect[] FALL_ANIMATION_POS = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111)
    };


    //==================================================================================================================

    public final static Animation IDLE_ANIMATION = new Animation(
            IDLE_ANIMATION_PATH,
            IDLE_ANIMATION_POS,
            IDLE_X_OFFSET,
            IDLE_Y_OFFSET,
            IDLE_SCALE_FACTOR
    );

    public final static Animation RUN_ANIMATION = new Animation(
            RUN_ANIMATION_PATH,
            RUN_ANIMATION_POS,
            RUN_X_OFFSET,
            RUN_Y_OFFSET,
            RUN_SCALE_FACTOR
    );

    public final static Animation ATTACK_1_ANIMATION = new Animation(
            ATTACK_1_ANIMATION_PATH,
            ATTACK_1_ANIMATION_POS,
            ATTACK_1_X_OFFSET,
            ATTACK_1_Y_OFFSET,
            ATTACK_1_SCALE_FACTOR
    );

    public final static Animation ATTACK_UP_ANIMATION = new Animation(
            ATTACK_UP_ANIMATION_PATH,
            ATTACK_UP_ANIMATION_POS,
            ATTACK_UP_X_OFFSET,
            ATTACK_UP_Y_OFFSET,
            ATTACK_UP_SCALE_FACTOR
    );

    public final static Animation JUMP_ANIMATION = new Animation(
            JUMP_ANIMATION_PATH,
            JUMP_ANIMATION_POS,
            JUMP_X_OFFSET,
            JUMP_Y_OFFSET,
            JUMP_SCALE_FACTOR);

    public final static Animation FALL_ANIMATION = new Animation(
            FALL_ANIMATION_PATH,
            FALL_ANIMATION_POS,
            FALL_X_OFFSET,
            FALL_Y_OFFSET,
            FALL_SCALE_FACTOR
    );



    //endregion



}
