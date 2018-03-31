package combat;

import com.badlogic.gdx.math.MathUtils;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 20.09.2017.
 */
public class Monster extends Character{
    public enum Rarity{
        Normal,
        Rare,
        Legendary,
        Boss
        }



    static int monsterlevel;
    static int monsterhealth;
    static int monsterstrength;
    static int damage;
    static int monsterdexterity;
    static int monsterstamina;
    static int monstermaxHp;
    static double raritymultiplier;
    static String monsterrarity;
    static String monstername;
    public  String[] monsternames = {"Skeleton", "Zombie", "Spider"};


    public String getMonsternames() {
        int random = MathUtils.random(monsternames.length - 1);
        return monsternames[random];
    }
    public String getMonstername() {
        return monstername;
    }


    public int getMonsterLevel() {
        return monsterlevel;
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
    public String getMonsterRarity() {
        return monsterrarity;
    }
    public static double getRaritymultiplier() {
        return raritymultiplier;
    }


    public int getHealth() {
        return monsterhealth;
    }
    public int getDamage() {
        return damage;
    }

    public String setRarity() {

        int i = MathUtils.random(1,100);
        if (i <= 1) {
            monsterrarity = Rarity.Boss.toString();
            raritymultiplier = 5;
            return monsterrarity;
        }
        else if (i > 1 && i <= 6){
            monsterrarity = Rarity.Legendary.toString();
            raritymultiplier = 3;
            return monsterrarity;
        }
        else if (i > 6 && i <= 21){
            monsterrarity = Rarity.Rare.toString();
            raritymultiplier = 1.5;
            return monsterrarity;
        }
        else {
            monsterrarity = Rarity.Normal.toString();
            raritymultiplier = 1;
            return monsterrarity;
        }
    }



    public Monster(MyGdxGame game) {
        super(game);
        this.monstername = getMonsternames();
        this.monsterrarity = setRarity();
        this.monsterlevel = (int) (game.getScoreService().getDepth()* raritymultiplier);
        this.monsterstrength = MathUtils.random(1,5)* monsterlevel;
        this.monsterdexterity = MathUtils.random(1,5)* monsterlevel;
        this.monsterstamina = MathUtils.random(1,5)* monsterlevel;
        this.monsterhealth = monsterstamina*5;
        this.monstermaxHp = monsterhealth;
    }


    public void MonsterAttack() {
        if (this.monsterhealth > 0) {
            Hero.health -= MathUtils.random(1,2)*monsterstrength;
                System.out.println("        Monster attacks you for " + damage + " strength!");
                System.out.println("You have " + Hero.health + " health remaining!");

            }


    }
    public void MonsterHitChance(){
        int roll = MathUtils.random(0, 100);
        int hitChance = 40*(Monster.getMonsterdexterity())/Hero.getHerodexterity();
        System.out.println("Monster Hit chance: " +hitChance);

        if (roll<=hitChance){
            MonsterAttack();
        }
        else {
            System.out.println("Monster missed!");
        }
    }
}


