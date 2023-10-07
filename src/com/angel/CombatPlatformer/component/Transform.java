package com.angel.CombatPlatformer.component;

import com.angel.CombatPlatformer.util.Vector2D;

public class Transform extends Component {
    public Vector2D position;
    public Vector2D rotation;
    public Vector2D size;


    public Transform(){
        position = new Vector2D();
        rotation = new Vector2D();
        size = new Vector2D();
    }

    public Transform(Vector2D position){
        this.position = position;
    }
    public Transform(Vector2D position, Vector2D rotation){
        this.position = position;
        this.rotation = rotation;
    }
    public Transform(Vector2D position, Vector2D rotation, Vector2D size){
        this.position = position;
        this.rotation = rotation;
        this.size = size;
    }


}
