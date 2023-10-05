package com.angel.CombatPlatformer.component;

import com.angel.CombatPlatformer.util.Rect;

import java.awt.*;

public class Collider extends Component{

    public Rect ColliderBounds;

    public Collider(Rect rect){
        this.ColliderBounds = rect;
    }

    public Collider(int x, int y, int w, int h){
        this.ColliderBounds = new Rect(x,y,w,h);
    }



    public void update(double deltaTime) {

    }

    public void draw(Graphics g) {

    }

    public void init() {

    }

    public boolean overlaps(Collider collider){

        Boolean isC1ToTheLeftOfC2 = (collider.ColliderBounds.x + collider.ColliderBounds.w < this.ColliderBounds.x),
                isC2ToTheLeftOfC1 = (this.ColliderBounds.x + this.ColliderBounds.w < collider.ColliderBounds.x),
                isC1AboveC2 = (collider.ColliderBounds.y + collider.ColliderBounds.h < this.ColliderBounds.y),
                isC2AboveC1 = (this.ColliderBounds.y + this.ColliderBounds.h < collider.ColliderBounds.y);

        if (isC1ToTheLeftOfC2 || isC2ToTheLeftOfC1 ){
            return false;
        }
        if (isC1AboveC2 || isC2AboveC1 ){
            return false;
        }
        return true;
    }

}
