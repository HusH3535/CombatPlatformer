package com.angel.CombatPlatformer.util;

import com.angel.CombatPlatformer.component.Collider;

public class Attack {

    private double damage;
    private int width, height, xOffSet, yOffSet;
    private double cooldown;

    public Attack(int xOffSet,int yOffSet,int width, int height, double damage,double cooldown){
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.cooldown = cooldown;
    }

    public double getDamage() {
        return damage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getCooldown() {
        return cooldown;
    }

    public int getxOffSet() {
        return xOffSet;
    }

    public int getyOffSet() {
        return yOffSet;
    }
}
