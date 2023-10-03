package com.angel.CombatPlatformer.component;

import com.angel.CombatPlatformer.util.Animation;
import com.angel.CombatPlatformer.util.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Dictionary;
import java.util.Hashtable;

public class Animator extends Component{


    private Dictionary<String, Animation> Animations = new Hashtable<>();
    private Animation currentAnimation = null;
    private BufferedImage currentFrame = null;
    private int currentFrameIndex = 0;
    private double frameTime = 0.15;
    private double lastFrame = 0;


    public Animator(){
    }

    public void createAnimation(String AnimationName,String path,Rect rects[] ){

        Animations.put(AnimationName, new Animation(path, rects));
        if (currentAnimation == null){
            currentAnimation = Animations.get(AnimationName);
            currentFrame = currentAnimation.getFrame(currentFrameIndex);
        }
    }

    //Create linux module in a virtual machine
    public boolean hasAnimations(){
        return (!Animations.isEmpty());
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double deltaTime) {
        lastFrame += deltaTime;

        if (currentAnimation!=null){
            if(lastFrame>frameTime){
                boolean arrayOverFlow = currentFrameIndex + 1 > currentAnimation.AnimationLength();

                currentFrameIndex = arrayOverFlow ? 0 : currentFrameIndex + 1;
                currentFrame = currentAnimation.getFrame(currentFrameIndex);
            }
        }

    }

    @Override
    public void draw(Graphics g) {
        if (currentAnimation!= null){

        }
    }
}
