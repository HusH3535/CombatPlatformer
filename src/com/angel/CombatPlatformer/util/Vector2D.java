package com.angel.CombatPlatformer.util;

public class Vector2D{
    public double x;
    public double y;

    public Vector2D(){
        x = 0;
        y = 0;
    }
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void normalize(){
        double magnitude = Math.sqrt(x * x + y * y);

        x = magnitude != 0 ? x / magnitude: x;
        y = magnitude != 0 ? y / magnitude: y;
    }

    public Vector2D GetNormalize(){
        Vector2D result = new Vector2D(x,y);
        result.normalize();
        return result;
    }

    public void add(Vector2D v){
        x += v.x;
        y += v.y;
    }

    public void subtract(Vector2D v){
        x -= v.x;
        y -= v.y;
    }

    public void multiply(double s){
        x *= s;
        y *= s;
    }


    public double getMagnitude(){
        return Math.abs(Math.sqrt(x * x + y * y));
    }

    public Vector2D getVectorTo(Vector2D v){
        Vector2D ret = new Vector2D(v.x - x, v.y - y);

        return ret;
    }

    public Vector2D getVectorToNormalize(Vector2D v){
        Vector2D ret = new Vector2D(v.x - x, v.y - y);
        ret.normalize();

        return ret;
    }


}
