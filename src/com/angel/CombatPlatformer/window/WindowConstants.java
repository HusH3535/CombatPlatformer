package com.angel.CombatPlatformer.window;

import com.angel.CombatPlatformer.util.io.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowConstants {

    private static final String BACKGROUND_PATH = "src/com/angel/CombatPlatformer/assets/Background/Background.png";
    public static int SCREEN_WIDTH = 1920/2;
    public static int SCREEN_HEIGHT = (int) (SCREEN_WIDTH * 0.5625);

    public static double SCREEN_UNIT = SCREEN_WIDTH * 0.01;

    public static int INSET_SIZE = 0;

    public static final String SCREEN_TITLE = "CombatPlatformer";



    //=====================================window.Scene Constants=====================================
    public static final int MENU_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int EDITOR_SCENE = 2;

    //=====================================GameScene_BACKGROUND Constants==============================


    public static final Image BACKGROUND = ImageLoader.loadImage(BACKGROUND_PATH);

    private static final String[] backgroundPaths = {
            "src/com/angel/CombatPlatformer/assets/Background/Layer_0.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_1.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_2.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_3.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_4.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_5.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_6.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_7.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_8.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_9.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_10.png",
            "src/com/angel/CombatPlatformer/assets/Background/Layer_11.png",
    };

    public static final Image[] BACKGROUNDLAYERS = new Image[]{
                ImageLoader.loadImage(backgroundPaths[0]),
                ImageLoader.loadImage(backgroundPaths[1]),
                ImageLoader.loadImage(backgroundPaths[2]),
                ImageLoader.loadImage(backgroundPaths[3]),
                ImageLoader.loadImage(backgroundPaths[4]),
                ImageLoader.loadImage(backgroundPaths[5]),
                ImageLoader.loadImage(backgroundPaths[6]),
                ImageLoader.loadImage(backgroundPaths[7]),
                ImageLoader.loadImage(backgroundPaths[8]),
                ImageLoader.loadImage(backgroundPaths[9]),
                ImageLoader.loadImage(backgroundPaths[10]),
                ImageLoader.loadImage(backgroundPaths[11]),
    };





}
