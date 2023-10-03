package com.angel.CombatPlatformer.entity.player;

import com.angel.CombatPlatformer.util.Rect;
import com.angel.CombatPlatformer.window.WindowConstants;

import java.awt.*;
import java.awt.geom.Point2D;

public class PlayerConstants {

    //region Dimension variables
    //==============================================================================

    public static final int PLAYER_HEIGHT = (int) (WindowConstants.SCREEN_WIDTH*(1.0/10));
    public static final int PLAYER_WIDTH = (int) (PLAYER_HEIGHT*(1.0/1.5));

    //endregion


    //region movement variables
    //==============================================================================
    public static final double PLAYER_SPEED = WindowConstants.SCREEN_WIDTH*(1.0/4);
    public static final Color characterColor = Color.GREEN;


    //endregion
    //200px per second





    //region sprite variables
    //==============================================================================

    public final static String idleAnimationID = "idle";
    public final static String Attack1AnimationID = "Attack1";


    public final static int idle_xOffset = -235;
    public final static int idle_yOffset = -200;
    public final static double idle_ScaleFactor = 3.8;
    public final static String idle_animationPath = "src/com/angel/CombatPlatformer/assets/mainCharacter/Idle.png";
    public final static Rect[] idle_animationPos = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111),
            new Rect(160*2,0,160,111),
            new Rect(160*3,0,160,111),
            new Rect(160*4,0,160,111),
            new Rect(160*5,0,160,111),
            new Rect(160*6,0,160,111),
            new Rect(160*7,0,160,111)
    };

    public final static int Attack1_xOffset = -235;
    public final static int Attack1_yOffset = -200;
    public final static double Attack1_ScaleFactor = 3.8;
    public final static String Attack1_animationPath = "src/com/angel/CombatPlatformer/assets/mainCharacter/Attack1.png";
    public final static Rect[] Attack1_animationPos = {
            new Rect(160*0,0,160,111),
            new Rect(160*1,0,160,111),
            new Rect(160*2,0,160,111),
            new Rect(160*3,0,160,111)
    };






    //endregion



}
