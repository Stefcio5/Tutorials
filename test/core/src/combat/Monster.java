package combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.myGdxGame.game.MyGdxGame;

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
        Monster.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        Monster.damage = damage;
    }


    public Monster(MyGdxGame game, int MonsterLevel, int monsterhealth) {
        super(game);
        Monster.MonsterLevel = MonsterLevel;
        health = monsterhealth;
    }

    public void MonsterAttack() {
        if (Monster.health > 0) {
            damage = MathUtils.random(5, 10)*MonsterLevel;
                System.out.println("        Monster attacks you for " + damage + " damage!");
                Hero.health -= damage;
                System.out.println("You have " + Hero.health + " health remaining!");

            }


    }

}


