package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.myGdxGame.game.MyGdxGame;
import combat.Character;
import combat.Hero;
import combat.Monster;
import ui.BattleButton;
import ui.GameplayScreenButton;
import ui.IClickCallback;

/**
 * Created by Stefcio on 17.09.2017.
 */
public class BattleScreen extends AbstractScreen {
    private GameplayScreenButton gameplayScreenButton;
    private BattleButton battleButton;

    public BattleScreen(MyGdxGame game) {
        super(game);
        init();
    }
    private void init(){
        initGameplayScreenButton();
        initBattleButton();
    }

    private void initBattleButton() {
        battleButton = new BattleButton(new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("BattleButton", "Clicked");
                Battle();

            }



        });
        stage.addActor(battleButton);

    }

    public void Battle() {
        if (game.getScoreService().getDepth() < 1){
            game.getScoreService().setDepth(1);
        }

        System.out.println("    Current depth: " + game.getScoreService().getDepth());
        System.out.println("    Killed monsters: " +game.getScoreService().getKilledMonsters());

        int herodamage;
        int herohealth;
        int monsterdamage;
        int monsterhealth;

        monsterhealth = MathUtils.random(10, 20);
        monsterdamage = MathUtils.random(5, 10);

        Monster monster = new Monster(1, monsterhealth, monsterdamage);
        System.out.println("    Monster health: " + monster.getHealth());

        herohealth = MathUtils.random(10, 20);
        herodamage = MathUtils.random(5, 10);

        Hero hero = new Hero(herohealth, herodamage);
        System.out.println("    Hero health: " +hero.getHealth());

        while (hero.getHealth() > 0 && monster.getHealth() > 0){
            hero.HeroAttack();
            monster.MonsterAttack();
        }
        if (hero.getHealth() <= 0 && monster.getHealth() > 0){
            Character.BattleLose();
        }
        else {
            Character.BattleWin();
            game.getScoreService().addKilledMonsters();
            System.out.println("Killed monsters: " + game.getScoreService().getKilledMonsters());
            game.getScoreService().increaseDepth();
        }
        


        }



    private void initGameplayScreenButton() {
        gameplayScreenButton = new GameplayScreenButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.setScreen(new GameplayScreen(game));
            }
        });
        stage.addActor(gameplayScreenButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

    }
    private void update(){
        stage.act();
    }
}
