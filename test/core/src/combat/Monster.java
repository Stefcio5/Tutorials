package combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 20.09.2017.
 */
public class Monster extends Character{

    static int monsterLevel;
    static int health;
    static int damage;



    static int maxHp;



    public int getMonsterLevel() {
        return monsterLevel;
    }
    public int getMaxHp() {
        return maxHp;
    }


    public int getHealth() {
        return health;
    }



    public Monster(MyGdxGame game, int monsterLevel, int maxHp, int health) {
        super(game);
      this.monsterLevel = monsterLevel;
      this.maxHp = maxHp;
      this.health = health;

    }

    public void MonsterAttack() {
        if (Monster.health > 0) {
            damage = MathUtils.random(5, 10)*monsterLevel;
                System.out.println("        Monster attacks you for " + damage + " damage!");
                Hero.health -= damage;
                System.out.println("You have " + Hero.health + " health remaining!");

            }


    }


}


