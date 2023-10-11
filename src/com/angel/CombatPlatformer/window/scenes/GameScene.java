package com.angel.CombatPlatformer.window.scenes;

import com.angel.CombatPlatformer.entity.Enemy.Dummy;
import com.angel.CombatPlatformer.entity.Entity;
import com.angel.CombatPlatformer.entity.player.Player;
import com.angel.CombatPlatformer.util.io.KL;
import com.angel.CombatPlatformer.window.Window;
import com.angel.CombatPlatformer.window.WindowConstants;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class GameScene extends Scene{


    private int frameRate = 0;
    private String displayInfo = "";

    public static ArrayList<Entity> enemies = new ArrayList<>();
    private Player player = new Player();



    public GameScene(){
        enemies.add(new Dummy(player.getTransform()));
    }

    @Override
    public void update(double deltaTime) {
        frameRate = (int) (1/deltaTime);
        displayInfo = String.format("%d FPS (%.3f)", frameRate,deltaTime);

        player.update(deltaTime);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(deltaTime);
        }

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



        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        player.draw(g);


    }
}
