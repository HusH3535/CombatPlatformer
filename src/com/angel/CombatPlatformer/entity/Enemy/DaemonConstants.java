package com.angel.CombatPlatformer.entity.Enemy;

import com.angel.CombatPlatformer.util.Animation;
import com.angel.CombatPlatformer.util.Rect;
import com.angel.CombatPlatformer.util.Vector2D;
import com.angel.CombatPlatformer.window.WindowConstants;

public class DaemonConstants {

    public static final Vector2D size = new Vector2D(WindowConstants.SCREEN_UNIT*8,WindowConstants.SCREEN_UNIT*8);

    public static final double STARTING_SPEED = WindowConstants.SCREEN_UNIT*35;
    public static double MAX_GRAVITY_VELOCITY = WindowConstants.SCREEN_UNIT;
    public static double GRAVITY_ACCELERATION = MAX_GRAVITY_VELOCITY/2;

    public static double JUMPING_VELOCITY = -WindowConstants.SCREEN_UNIT * 0.2;

    public static final double  STARTING_ATTACKSPEED = 0.3;

    public final static String IDLE_A_ID = "idle";


    public final static int X_OFFSET = (int) (- 3 * WindowConstants.SCREEN_UNIT);
    public final static int Y_OFFSET = (int) (- 2.2  * WindowConstants.SCREEN_UNIT);
    public final static double SCALE_FACTOR =   WindowConstants.SCREEN_UNIT * .2 ;
    public final static String IDLE_ANIMATION_PATH = "src/com/angel/CombatPlatformer/assets/enemies/Daemon_SS.png";

    public final static Rect[] IDLE_ANIMATION_POS = {
            new Rect(64*0,0,64,64),
            new Rect(64*1,0,64,64),
            new Rect(64*2,0,64,64),
            new Rect(64*3,0,64,64),
            new Rect(64*4,0,64,64),
            new Rect(64*5,0,64,64)
    };

    public final static Animation IDLE_ANIMATION = new Animation(
            IDLE_ANIMATION_PATH,
            IDLE_ANIMATION_POS,
            X_OFFSET,
            Y_OFFSET,
            SCALE_FACTOR
    );



}
