package combat;

import com.badlogic.gdx.math.MathUtils;
import com.myGdxGame.game.MyGdxGame;
import service.ScoreService;

/**
 * Created by Stefcio on 22.09.2017.
 */
public class Hero extends Character {
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


    public Hero(int herohealth, int herodamage) {
        health = herohealth;
        damage = herodamage;

    }

    public static void HeroAttack() {
        if (Hero.health > 0) {
            System.out.println("You did " + damage + " damage");
            Monster.health -= damage;
            System.out.println("        Monster have " + Monster.health + " health remaining!");
        }
    }
}
