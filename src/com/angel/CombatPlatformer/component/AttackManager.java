package com.angel.CombatPlatformer.component;

import com.angel.CombatPlatformer.util.Attack;
import com.angel.CombatPlatformer.util.Rect;

import java.awt.*;
import java.util.ArrayList;

public class AttackManager extends Component implements Monobehavior{

    public boolean Renderhitbox = true;

    private ArrayList<Attack> Attacks;
    private double activeCoolDown;
    private Attack activeAttack;
    private int x, y;



    public AttackManager(){
        Attacks = new ArrayList<>();
        activeCoolDown = 0;
    }

    public void performAttack(int attack_Id, int x, int y){
        activeAttack = Attacks.get(attack_Id);
        activeCoolDown = activeAttack.getCooldown();
        this.x = activeAttack.getxOffSet() + x;
        this.y = activeAttack.getyOffSet() + y;

    }

    public void addAttack(int attack_Id, Attack attack) {
        Attacks.add(attack_Id, attack);
    }


    @Override
    public void init() {

    }

    @Override
    public void update(double deltaTime) {
        activeCoolDown = activeCoolDown < 0 ? 0 : (activeCoolDown - deltaTime);

        if (activeCoolDown == 0){
            activeAttack = null;
        }

    }

    @Override
    public void draw(Graphics g) {

        if (Renderhitbox && activeAttack!= null){
            Rect gizmo = new Rect(x, y, activeAttack.getWidth(), activeAttack.getHeight());
            g.setColor(Color.red);
            g.drawRect(gizmo.x, gizmo.y, gizmo.w, gizmo.h);
        }

    }
}
