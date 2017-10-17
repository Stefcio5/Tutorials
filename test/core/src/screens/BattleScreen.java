package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myGdxGame.game.MyGdxGame;
import combat.Hero;
import combat.Monster;
import ui.*;
import com.badlogic.gdx.utils.Timer;

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
    private StrengthLabel levelLabel;
    private StrengthLabel strengthLabel;
    private StrengthLabel hpLabel;
    private HpBar hpBar;
    private StrengthLabel monsterLevelLabel;
    private HpBar monsterHpBar;
    private StrengthLabel monsterHpLabel;
    private Hero hero;
    private Monster monster;

//    private int herolevel;
//    private int currentXp;
//    private int requiredXp;
//    private int herodamage;
//    private int herohealth5;
//
//    private int monsterlevel;
//    private int monsterdamage;
//    private int monsterhealth;


    public BattleScreen(MyGdxGame game) {
        super(game);
        init();

    }
    private void init(){
        initTable();
        initGameplayScreenButton();
        //initBattle();
        //initBattleButton();
    }



    private void initTable() {
        roottable = new Table();
        roottable.defaults().pad(10).top().left();
        roottable.setFillParent(true);
        roottable.debug();
        roottable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        depthlabel = new Label("Depth: " +game.getScoreService().getDepth(), labelStyle);

        hpBar = new HpBar(0.0f, 100.0f, 1.0f, false);
        hpBar.setAnimateDuration(0.25f);

        hpLabel = new StrengthLabel();
        levelLabel = new StrengthLabel();
        strengthLabel = new StrengthLabel();



        playerstats = new Table();
        playerstats.defaults().pad(5).left();
        playerstats.add(levelLabel);
        playerstats.row();
        playerstats.add(hpBar);
        playerstats.row();
        playerstats.add(hpLabel);
        playerstats.row();
        playerstats.add(strengthLabel);
        playerstats.debug();

        monsterLevelLabel = new StrengthLabel();
        monsterHpLabel = new StrengthLabel();
        monsterHpBar = new HpBar(0.0f, 100.0f, 1.0f, false);
        monsterHpBar.setAnimateDuration(0.25f);

        monsterstats = new Table();
        monsterstats.defaults().pad(5).left();
        monsterstats.add(monsterLevelLabel);
        monsterstats.row();
        monsterstats.add(monsterHpBar);
        monsterstats.row();
        monsterstats.add(monsterHpLabel);
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
                battleButton.setTouchable(Touchable.disabled);
            }

        });
        return battleButton;

    }

    public void Battle() {
        Thread battlethread = new Thread(new Runnable() {
            @Override
            public void run() {
                initBattle();
                while (hero.getHealth() > 0 && monster.getHealth() > 0) {
                    hero.HeroAttack();
                    UpdateStats();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    monster.MonsterAttack();
                    UpdateStats();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (hero.getHealth() <= 0 && monster.getHealth() > 0) {
                    hero.BattleLose();
                    battleButton.setTouchable(Touchable.enabled);

                } else if (hero.getHealth() > 0 && monster.getHealth() <= 0) {
                    hero.BattleWin();
                    battleButton.setTouchable(Touchable.enabled);

                }

            }
        });
        battlethread.start();
    }



    public void initBattle() {

        //TODO Refactor Monster and Hero classes
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


        monster = new Monster(game, monsterlevel, monsterhealth);
        System.out.println("    Monster health: " + monster.getHealth());


        hero = new Hero(game, herolevel, currentXp, requiredXp, herohealth, herodamage);
        System.out.println("    Hero health: " +hero.getHealth() + " Hero damage: " + herodamage);

        UpdateStats();
    }

    public void UpdateStats() {
        levelLabel.setText("Level: " +game.getScoreService().getLevel());
        hpLabel.setText("Hp: " +hero.getHealth());
        hpBar.setValue((hero.getHealth()*100)/(hero.getDamage()*5));
        strengthLabel.setText("Strength: "+game.getScoreService().getStrength());

        monsterLevelLabel.setText("Level: " +game.getScoreService().getDepth());
        monsterHpBar.setValue(monster.getHealth());
        monsterHpLabel.setText("Hp: "+monster.getHealth());

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

        if (hero != null) {

           // hpLabel.setText("Hp: " + hero.getHealth());
        }

        stage.act();
    }

    private void setDepthLevel() {
        if (game.getScoreService().getDepth() < 1){
            game.getScoreService().setDepth(1);
        }
    }
}