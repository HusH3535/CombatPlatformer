package com.angel.CombatPlatformer.entity;

import com.angel.CombatPlatformer.component.Health;
import com.angel.CombatPlatformer.component.Transform;
import com.angel.CombatPlatformer.window.scenes.GameScene;

import java.awt.*;

public abstract class Entity {

    protected Transform transform = new Transform();
    protected Health health = null;

    public Transform getTransform(){
        return transform;
    }

    public Health getHealth(){
        return health;
    }

    public void destroy(){

        GameScene.enemies.remove(this);
    }

    public abstract void update(Double deltaTime);

    public abstract void draw(Graphics g);


}
