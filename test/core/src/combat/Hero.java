package combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 22.09.2017.
 */
public class Hero extends Character {
    static int health;
    static int damage;
    static int level;
    static int currentXp;
    static int requiredXp;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        Hero.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        Hero.damage = damage;
    }

    public static int getCurrentXp() {
        return currentXp;
    }

    public static void setCurrnetXp(int currnetXp) {
        Hero.currentXp = currnetXp;
    }



    public Hero(MyGdxGame game, int level, int currentXp, int requiredXp, int herohealth, int herodamage) {
        super(game);
        Hero.level = level;
        Hero.currentXp = currentXp;
        Hero.requiredXp = requiredXp;
        health = herohealth;
        damage = herodamage;

    }

    public void HeroAttack() {
        if (Hero.health > 0) {

                System.out.println("You did " + damage + " damage");
                Monster.health -= damage;
                System.out.println("        Monster have " + Monster.health + " health remaining!");



        }
    }
}