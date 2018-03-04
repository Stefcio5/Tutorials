package combat;

import com.badlogic.gdx.math.MathUtils;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 22.09.2017.
 */
public class Hero extends Character {
    static int health;
    static int strength;
    static int level;
    static int herodexterity;
    static int herostamina;
    static int currentXp;
    static int requiredXp;
    static float expPercentage;

    public static int getExpPercentage() {
        return (int) expPercentage;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        Hero.health = health;
    }

    public int getDamage() {
        return strength;
    }

    public void setDamage(int damage) {
        Hero.strength = damage;
    }
    public static int getHerodexterity() {
        return herodexterity;
    }
    public int getHerostamina() {
        return herostamina;
    }

    public static int getCurrentXp() {
        return currentXp;
    }

    public static void setCurrnetXp(int currnetXp) {
        Hero.currentXp = currnetXp;
    }

    public static int getRequiredXp() {
        return requiredXp;
    }



    public Hero(MyGdxGame game, int level, int currentXp, int requiredXp, int herohealth, int strength, int herodexterity, int herostamina) {
        super(game);
        this.level = level;
        this.currentXp = currentXp;
        this.requiredXp = requiredXp;
        this.health = herohealth;
        this.strength = strength;
        this.herodexterity = herodexterity;
        this.herostamina = herostamina;
        this.expPercentage = ((currentXp*100.0f)/requiredXp);

    }

    public void HeroAttack() {
        if (Hero.health > 0) {
                 Monster.monsterhealth -= MathUtils.random(1,2)* strength;
                System.out.println("You did " + strength + " strength");
                System.out.println("        Monster have " + Monster.monsterhealth + " health remaining!");





        }
    }
    public void HeroHitChance(){
        int roll = MathUtils.random(0, 100);
        int hitChance = 40*(Hero.getHerodexterity())/Monster.getMonsterdexterity();
        System.out.println("Hero Hit chance: " +hitChance);
        if (roll<hitChance){
            HeroAttack();

        }
        else {
            System.out.println("You missed!");

        }
    }
}
