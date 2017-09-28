package combat;

import com.myGdxGame.game.MyGdxGame;
import service.ScoreService;

/**
 * Created by Stefcio on 20.09.2017.
 */
public abstract class Character {


    public static void BattleWin() {

        System.out.println("You win!");

    }

    public static void BattleLose(){
        System.out.println("You lose!");
    }
}
