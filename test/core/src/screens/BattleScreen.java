package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myGdxGame.game.MyGdxGame;
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
    private Table roottable;
    private Table playerstats;
    private Table monsterstats;
    private Label depthlabel;
    private Hero hero;
    private Monster monster;

    public BattleScreen(MyGdxGame game) {
        super(game);
        init();
    }
    private void init(){
        initTable();
        initGameplayScreenButton();
        //initBattleButton();
    }

    private void initTable() {
        roottable = new Table();
        roottable.defaults().pad(10);
        roottable.setFillParent(true);
        roottable.debug();
        roottable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        depthlabel = new Label("Depth: " +game.getScoreService().getDepth(), labelStyle);


        playerstats = new Table();
        playerstats.debug();

        monsterstats = new Table();
        monsterstats.debug();



        roottable.add(depthlabel).fill();
        roottable.add(initBattleButton()).fill().height(40);
        roottable.row();
        roottable.add(playerstats).expand().uniform();
        roottable.add(monsterstats).expand().uniform();
        roottable.row();
        roottable.add().height(40);


        stage.addActor(roottable);




    }

    private BattleButton initBattleButton() {
        battleButton = new BattleButton(new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("BattleButton", "Clicked");
                Battle();

            }



        });
        return battleButton;

    }

    public void Battle() {


        System.out.println("    Current depth: " + game.getScoreService().getDepth());
        System.out.println("    Killed monsters: " +game.getScoreService().getKilledMonsters());

        int herolevel = game.getScoreService().getLevel();
        int currentXp = game.getScoreService().getXp();
        int requiredXp = game.getScoreService().getRequiredxp();
        int herodamage = game.getScoreService().getStrength();
        int herohealth = herodamage*5;

        int monsterlevel = game.getScoreService().getDepth();
        int monsterdamage = MathUtils.random(5, 10)*monsterlevel;
        int monsterhealth = monsterdamage*monsterlevel;






        monster = new Monster(game, monsterlevel, monsterhealth, monsterdamage);
        System.out.println("    Monster health: " + monster.getHealth());


        hero = new Hero(game, herolevel, currentXp, requiredXp, herohealth, herodamage);
        System.out.println("    Hero health: " +hero.getHealth() + " Hero damage: " + herodamage);

        while (hero.getHealth() > 0 && monster.getHealth() > 0){
            hero.HeroAttack();
            monster.MonsterAttack();
        }
        if (hero.getHealth() <= 0 && monster.getHealth() > 0){
            hero.BattleLose();
        }
        else {
            hero.BattleWin();

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
        setDepthLevel();

        depthlabel.setText("Depth: " + game.getScoreService().getDepth());

        stage.act();
    }

    private void setDepthLevel() {
        if (game.getScoreService().getDepth() < 1){
            game.getScoreService().setDepth(1);
        }
    }
}
