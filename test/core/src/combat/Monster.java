package combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.myGdxGame.game.MyGdxGame;

import java.util.ArrayList;

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


    static int monsterLevel;
    static int monsterhealth;
    static int monsterstrength;
    static int damage;
    static int monsterdexterity;
    static int monsterstamina;
    static int monstermaxHp;
    static double rarityMultiplier;
    static String monsterRarity;



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
    public String getMonsterRarity() {
        return monsterRarity;
    }
    public static double getRarityMultiplier() {
        return rarityMultiplier;
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
            monsterRarity = Rarity.Boss.toString();
            rarityMultiplier = 5;
            return monsterRarity;
        }
        else if (i > 1 && i <= 6){
            monsterRarity = Rarity.Legendary.toString();
            rarityMultiplier = 3;
            return monsterRarity;
        }
        else if (i > 6 && i <= 21){
            monsterRarity = Rarity.Rare.toString();
            rarityMultiplier = 1.5;
            return monsterRarity;
        }
        else {
            monsterRarity = Rarity.Normal.toString();
            rarityMultiplier = 1;
            return monsterRarity;
        }
    }



    public Monster(MyGdxGame game) {
        super(game);
        this.monsterRarity = setRarity();
      this.monsterLevel = (int) (game.getScoreService().getDepth()*rarityMultiplier);
      this.monsterstrength = MathUtils.random(1,5)*monsterLevel;
      this.monsterdexterity = MathUtils.random(1,5)*monsterLevel;
      this.monsterstamina = MathUtils.random(1,5)*monsterLevel;
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


