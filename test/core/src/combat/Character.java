package combat;

import com.badlogic.gdx.utils.Timer;
import com.myGdxGame.game.MyGdxGame;
import screens.BattleScreen;
import service.ScoreService;

/**
 * Created by Stefcio on 20.09.2017.
 */
public abstract class Character {

    protected MyGdxGame game;


    public Character(MyGdxGame game){
        this.game = game;
    }



    public void BattleWin() {

        System.out.println("You win!");
        System.out.println("Current depth: " + game.getScoreService().getDepth());
        game.getScoreService().addXp();
        System.out.println("Current xp: " + game.getScoreService().getXp());
        System.out.println("Required xp: " + game.getScoreService().getRequiredxp());
        game.getScoreService().addLevel();
        System.out.println("Your level: " + game.getScoreService().getLevel());
        game.getScoreService().addKilledMonsters();
        System.out.println("Killed monsters: " + game.getScoreService().getKilledMonsters());
        game.getScoreService().increaseDepth();

    }

    public void BattleLose(){
        System.out.println("You lose!");
    }

}
