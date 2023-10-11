package com.angel.CombatPlatformer.component;

import com.angel.CombatPlatformer.entity.Entity;
import com.angel.CombatPlatformer.util.Attack;
import com.angel.CombatPlatformer.util.Rect;
import com.angel.CombatPlatformer.window.scenes.GameScene;

import java.awt.*;
import java.util.ArrayList;

public class MeleeAttackManager extends Component implements Monobehavior{

    public boolean Renderhitbox = true;
    private ArrayList<Attack> Attacks;
    private double activeCoolDown;
    private Attack activeAttack;
    private int x, y;

    ArrayList<Entity> enemiesHit = new ArrayList<>();



    public MeleeAttackManager(){
        Attacks = new ArrayList<>();
        activeCoolDown = 0;
    }

    public void performAttack(int attack_Id, int x, int y){
        activeAttack = Attacks.get(attack_Id);
        activeCoolDown = activeAttack.getCooldown();
        this.x = activeAttack.getxOffSet() + x;
        this.y = activeAttack.getyOffSet() + y;

    }

    public void performAttackFlip(int attack_Id, int x, int y){
        activeAttack = Attacks.get(attack_Id);
        activeCoolDown = activeAttack.getCooldown();
        this.x = activeAttack.getxOffSet() + x + activeAttack.getWidth();
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
            enemiesHit.clear();
        }
        if (activeAttack != null){
            checkHit();
        }

    }

    private void checkHit() {
        Entity currentEnemy;
        Collider attackHB = new Collider(x,y, activeAttack.getWidth(), activeAttack.getHeight());
        Collider currentEnemyCollider;
        for (int i = 0; i < GameScene.enemies.size(); i++) {
            currentEnemy = GameScene.enemies.get(i);
            if (currentEnemy == null){continue;}

            if(enemiesHit.contains(currentEnemy)){continue;}

            currentEnemyCollider = new Collider(
                    (int) currentEnemy.getTransform().position.x,
                    (int) currentEnemy.getTransform().position.y,
                    (int) currentEnemy.getTransform().size.x,
                    (int) currentEnemy.getTransform().size.y
            );

            if(attackHB.overlaps(currentEnemyCollider)){
                currentEnemy.getHealth().takeDamage(activeAttack.getDamage());
                enemiesHit.add(currentEnemy);
            }

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
