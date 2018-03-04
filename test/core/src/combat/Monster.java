package combat;

import com.badlogic.gdx.math.MathUtils;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 20.09.2017.
 */
public class Monster extends Character{

    static int monsterLevel;
    static int monsterhealth;
    static int monsterstrength;
    static int damage;
    static int monsterdexterity;
    static int monsterstamina;
    static int monstermaxHp;



    public int getMonsterLevel() {
        return monsterLevel;
    }
    public int getMaxHp() {
        return monstermaxHp;
    }
    public int getMonsterstrength() {
        return monsterstrength;
    }
    public static int getMonsterdexterity() {
        return monsterdexterity;
    }
    public int getMonsterstamina() {
        return monsterstamina;
    }


    public int getHealth() {
        return monsterhealth;
    }
    public int getDamage() {
        return damage;
    }



    public Monster(MyGdxGame game, int monsterLevel, int monsterstrength, int monsterdexterity, int monsterstamina, int monstermaxHp, int monsterhealth) {
        super(game);
      this.monsterLevel = monsterLevel;
      this.monsterstrength = monsterstrength;
      this.monsterdexterity = monsterdexterity;
      this.monsterstamina = monsterstamina;
      this.monstermaxHp = monstermaxHp;
      this.monsterhealth = monsterhealth;

    }


    public void MonsterAttack() {
        if (Monster.monsterhealth > 0) {
            Hero.health -= MathUtils.random(1,2)*monsterstrength;
                System.out.println("        Monster attacks you for " + damage + " strength!");
                System.out.println("You have " + Hero.health + " health remaining!");

            }


    }
    public void MonsterHitChance(){
        int roll = MathUtils.random(0, 100);
        int hitChance = 40*(Monster.getMonsterdexterity())/Hero.getHerodexterity();
        System.out.println("Monster Hit chance: " +hitChance);

        if (roll<hitChance){
            MonsterAttack();
        }
        else {
            System.out.println("Monster missed!");
        }
    }


}


