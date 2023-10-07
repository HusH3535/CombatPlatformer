package com.angel.CombatPlatformer.window.scenes;

import com.angel.CombatPlatformer.entity.player.Dummy;
import com.angel.CombatPlatformer.entity.player.Player;
import com.angel.CombatPlatformer.util.io.KL;
import com.angel.CombatPlatformer.window.Window;
import com.angel.CombatPlatformer.window.WindowConstants;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;


public class GameScene extends Scene{


    private int frameRate = 0;
    private String displayInfo = "";
    private Player player = new Player();
    private Dummy dummy = new Dummy(player.transform);



    public GameScene(){

    }

    @Override
    public void update(double deltaTime) {
        frameRate = (int) (1/deltaTime);
        displayInfo = String.format("%d FPS (%.3f)", frameRate,deltaTime);

        player.update(deltaTime);
        dummy.update(deltaTime);

        if(KL.getKeyListener().isKeyDown(KeyEvent.VK_ESCAPE)){
            Window.getWindow().changeState(WindowConstants.MENU_SCENE);
        }

    }

    @Override
    public void draw(Graphics g) {



        g.drawImage(WindowConstants.BACKGROUND,
                0,
                0,
                WindowConstants.SCREEN_WIDTH,
                WindowConstants.SCREEN_HEIGHT,
                0,
                (int) (WindowConstants.BACKGROUND.getHeight(null) - WindowConstants.BACKGROUND.getHeight(null) * 0.5625),
                WindowConstants.BACKGROUND.getWidth(null),
                WindowConstants.BACKGROUND.getHeight(null),
                null
        );


        g.setColor(Color.GREEN);
        g.drawString(displayInfo,10, (int) (WindowConstants.INSET_SIZE*1.5));



        dummy.draw(g);
        player.draw(g);


    }
}
