package com.angel.CombatPlatformer.util;

public class Attack {

    private double damageMultiplier;
    private double cooldown;
    private Rect[] hitboxes;

    public Attack(double damageMultiplier,double cooldown, Rect[] hitboxes){
        this.damageMultiplier = damageMultiplier;
        this.cooldown = cooldown;
        this.hitboxes = hitboxes;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }
    public double getCooldown() {
        return cooldown;
    }

    public Rect[] getHitboxes(){
        return hitboxes;
    }

}
