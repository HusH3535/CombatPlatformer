package com.angel.CombatPlatformer.entity;

import com.angel.CombatPlatformer.component.Transform;

import java.awt.*;

public interface Entity {

    Transform transform = null;

    public void update(Double deltaTime);

    public void draw(Graphics g);


}
