package combat;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Stefcio on 20.09.2017.
 */
public class Monster extends Character{
    static int MonsterLevel;
    static int health;
    static int damage;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public Monster(int MonsterLevel, int monsterhealth, int monsterdamage) {
        this.MonsterLevel = MonsterLevel;
        health = monsterhealth;
        damage = monsterdamage;

    }

    public static void MonsterAttack() {
        if (Monster.health > 0) {
            System.out.println("        Monster attacks you for " + damage + " damage!");
            Hero.health -= damage;
            System.out.println("You have " + Hero.health + " health remaining!");

    }

}
}

