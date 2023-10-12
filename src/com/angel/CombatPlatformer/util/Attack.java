package com.angel.CombatPlatformer.util;

public class Attack {

    private double damageMultiplier;
    private int width, height, xOffSet, yOffSet;
    private double cooldown;

    public Attack(int xOffSet,int yOffSet,int width, int height, double damageMultiplier,double cooldown){
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.width = width;
        this.height = height;
        this.damageMultiplier = damageMultiplier;
        this.cooldown = cooldown;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
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
