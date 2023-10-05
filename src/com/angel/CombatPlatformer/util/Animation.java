package com.angel.CombatPlatformer.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

    public class Animation {

        private ArrayList<BufferedImage> frames = new ArrayList<>();
        public int xOffset = 0;
        public int yOffset = 0;
        public double scaleFactor = 1.0;

        public Animation(String path, Rect rects[] ){
            try{
                BufferedImage spriteSheet = ImageIO.read(new File(path));
                for (Rect rect:rects) {
                    frames.add(spriteSheet.getSubimage(
                            rect.x,
                            rect.y,
                            rect.w,
                            rect.h));
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }

        public Animation(String path, Rect rects[], int xOffset, int yOffset, double scaleFactor  ){
            try{
                BufferedImage spriteSheet = ImageIO.read(new File(path));
                for (Rect rect:rects) {
                    frames.add(spriteSheet.getSubimage(
                            rect.x,
                            rect.y,
                            rect.w,
                            rect.h));
                }
                this.xOffset = xOffset;
                this.yOffset = yOffset;
                this.scaleFactor = scaleFactor;
            }catch(Exception e){
                e.printStackTrace();
            }

        }

        public BufferedImage getFrame(int index){
            return frames.get(index);
        }

        public int AnimationLength(){
            return frames.size();
        }
}
