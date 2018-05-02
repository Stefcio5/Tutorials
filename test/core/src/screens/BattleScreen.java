package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.myGdxGame.game.MyGdxGame;
import combat.Hero;
import combat.Monster;
import ui.*;

/**
 * Created by Stefcio on 17.09.2017.
 */
public class BattleScreen extends AbstractScreen {

    private Skin skin;
    private GameplayScreenButton gameplayScreenButton;
    private BattleButton battleButton;
    private DepthButton depthButton;
    private Table playerstats;
    private Table monsterstats;
    private Label depthlabel;
    private AttributeLabel levelLabel;
    private AttributeLabel experienceLabel;
    private AttributeLabel strenghtLabel;
    private AttributeLabel hpLabel;
    private AttributeLabel dexterityLabel;
    private AttributeLabel staminaLabel;
    private HpBar hpBar;
    private AttributeLabel monsterLevelLabel;
    private AttributeLabel monsterNameLabel;
    private HpBar monsterHpBar;
    private AttributeLabel monsterHpLabel;
    private AttributeLabel monsterStrengthLabel;
    private AttributeLabel monsterDexterityLabel;
    private AttributeLabel monsterStaminaLabel;
    private Hero hero;
    private Monster monster;
    private boolean loopbattle = false;
    private DamageIndicatorLabel monsterDamageIndicatorLabel;
    private DamageIndicatorLabel heroDamageIndicatorLabel;
    private AttributeLabel rewardLabel;


    public BattleScreen(MyGdxGame game) {
        super(game);
        init();

    }
    private void init(){
        initSkin();
        initTable();
        initGameplayScreenButton();
        //initBattle();
        //initBattleButton();
    }

    private void initSkin() {
        skin = game.assets.manager.get(game.assets.uiSkin);
    }


    private void initTable() {
        Texture roottabletexture = new Texture(Gdx.files.internal("roottable.png"));
        Drawable roottabledrawable = new TextureRegionDrawable(new TextureRegion(roottabletexture));
        Table roottable = new Table();
        roottable.setBackground(roottabledrawable);
        roottable.defaults().pad(10).top().left();
        roottable.setFillParent(true);
        roottable.setDebug(true);
        roottable.setSize(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

//        Label.LabelStyle labelStyle = new Label.LabelStyle();
//        labelStyle.font = new BitmapFont();
        depthlabel = new Label("Depth: " +game.getScoreService().getDepth(), skin, "title");

        hpBar = new HpBar(0.0f, 100.0f, 1.0f, false);
        hpBar.setAnimateDuration(0.25f);

        hpLabel = new AttributeLabel(skin, "subtitle");
        levelLabel = new AttributeLabel(skin, "subtitle");
        experienceLabel = new AttributeLabel(skin, "subtitle");
        strenghtLabel = new AttributeLabel(skin, "subtitle");
        dexterityLabel = new AttributeLabel(skin, "subtitle");
        staminaLabel = new AttributeLabel(skin, "subtitle");

        rewardLabel = new AttributeLabel(skin, "subtitle");

        monsterDamageIndicatorLabel = new DamageIndicatorLabel();
        heroDamageIndicatorLabel = new DamageIndicatorLabel();

        Table rewardTable = new Table();



        playerstats = new Table();
        playerstats.defaults().pad(5).left().height(20);
        playerstats.add(levelLabel);
        playerstats.row();
        playerstats.add(experienceLabel);
        playerstats.row();
        playerstats.add(hpBar);
        playerstats.add(monsterDamageIndicatorLabel);
        playerstats.row();
        playerstats.add(hpLabel);
        playerstats.row();
        playerstats.add(strenghtLabel);
        playerstats.row();
        playerstats.add(dexterityLabel);
        playerstats.row();
        playerstats.add(staminaLabel);
        playerstats.row();
        playerstats.add(rewardLabel);
        playerstats.add(rewardTable);

        playerstats.setDebug(true);
        playerstats.setVisible(false);

        monsterNameLabel = new AttributeLabel(skin, "subtitle");
        monsterLevelLabel = new AttributeLabel(skin, "subtitle");
        monsterHpLabel = new AttributeLabel(skin, "subtitle");
        monsterHpBar = new HpBar(0.0f, 100.0f, 1.0f, false);
        monsterHpBar.setAnimateDuration(0.25f);
        monsterStrengthLabel = new AttributeLabel(skin, "subtitle");
        monsterDexterityLabel = new AttributeLabel(skin, "subtitle");
        monsterStaminaLabel = new AttributeLabel(skin, "subtitle");

        monsterstats = new Table();
        monsterstats.defaults().pad(5).left().height(20);
        monsterstats.add(monsterLevelLabel);
        monsterstats.row();
        monsterstats.add(monsterNameLabel);
        monsterstats.row();
        monsterstats.add(monsterHpBar);
        monsterstats.add(heroDamageIndicatorLabel);
        monsterstats.row();
        monsterstats.add(monsterHpLabel);
        monsterstats.row();
        monsterstats.add(monsterStrengthLabel);
        monsterstats.row();
        monsterstats.add(monsterStaminaLabel);
        monsterstats.row();
        monsterstats.add(monsterDexterityLabel);
        monsterstats.row();
        monsterstats.add(initDepthButton()).fillX().height(50);
        monsterstats.setDebug(true);
        monsterstats.setVisible(false);





        roottable.add(depthlabel).fill();
        roottable.add(initBattleButton()).fill();
        roottable.row();
        roottable.add(playerstats).expand().uniform();
        roottable.add(monsterstats).expand().uniform();
        roottable.row();
        roottable.add().height(40);


        stage.addActor(roottable);





    }
//    Depth Button init

    private DepthButton initDepthButton() {
        depthButton = new DepthButton(skin, "default", new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("DepthBtn", "DepthButton clicked");
                game.getScoreService().increaseDepth();
            }
        });
        return depthButton;
    }

    private BattleButton initBattleButton() {
        battleButton = new BattleButton(skin, "default", new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("BattleButton", "Clicked");
                battleButton.setTouchable(Touchable.disabled);
                gameplayScreenButton.setTouchable(Touchable.disabled);
                playerstats.setVisible(true);
                monsterstats.setVisible(true);

                if (loopbattle){
                    loopbattle = false;
                }
                else {
                    loopbattle = true;
                }
                Battle();
            }

        });
        return battleButton;

    }

    public void Battle() {

            Thread battlethread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (loopbattle){

                    initBattle();
                    while (hero.getHealth() > 0 && monster.getHealth() > 0) {
                        hero.HeroHitChance();
                        UpdateStats();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        monster.MonsterHitChance();
                        UpdateStats();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    if (hero.getHealth() <= 0 && monster.getHealth() > 0) {
                        hero.BattleLose();
                        battleButton.setTouchable(Touchable.enabled);
                        gameplayScreenButton.setTouchable(Touchable.enabled);

                    } else if (hero.getHealth() > 0 && monster.getHealth() <= 0) {
                        hero.BattleWin();
                        battleButton.setTouchable(Touchable.enabled);
                        gameplayScreenButton.setTouchable(Touchable.enabled);

                    }

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
        int herostrength = game.getScoreService().getStrength();
        int herodexterity = game.getScoreService().getDexterity();
        int herostamina = game.getScoreService().getStamina();
        int herohealth = herostamina*5;

//        int monsterlevel = game.getScoreService().getDepth();
//        int monsterstrength = MathUtils.random(1,5)*monsterlevel;
//        int monsterdexterity = MathUtils.random(1,5)*monsterlevel;
//        int monsterstamina = MathUtils.random(1,5)*monsterlevel;
//        int monsterhealth = monsterstamina*5;



        monster = new Monster(game);

        System.out.println("    Monster health: " + monster.getHealth());
        System.out.println("    Monster rarity: " + monster.getMonsterRarity());


        hero = new Hero(game, herolevel, currentXp, requiredXp, herohealth, herostrength, herodexterity, herostamina);
        System.out.println("    Hero health: " +hero.getHealth() + " Hero damage: " + herostrength);

        UpdateStats();
    }

    public void UpdateStats() {
        levelLabel.setText("Level: " +game.getScoreService().getLevel());
        experienceLabel.setText("Exp: " +Hero.getCurrentXp() + "/" + Hero.getRequiredXp() + " (" + Hero.getExpPercentage() + "%)");
        hpLabel.setText("Hp: " +hero.getHealth());
        hpBar.setValue((hero.getHealth()*100)/(hero.getHerostamina()*5));
        strenghtLabel.setText("Strength: "+game.getScoreService().getStrength());
        dexterityLabel.setText("Dexterity: " + game.getScoreService().getDexterity());
        staminaLabel.setText("Stamina: " + game.getScoreService().getStamina());
        rewardLabel.setText("Reward: " +game.getScoreService().getGainedxp() + " xp");

        monsterLevelLabel.setText("Level: " +monster.getMonsterLevel());

        // change monster name label color depend of monster rarity

        if (monster.getMonsterRarity().equals(Monster.Rarity.Boss.toString()) ){
            monsterNameLabel.setColor(skin.getColor("red"));
        }
        else if (monster.getMonsterRarity().equals(Monster.Rarity.Legendary.toString())){
            monsterNameLabel.setColor(skin.getColor("brown"));
        }
        else if (monster.getMonsterRarity().equals(Monster.Rarity.Rare.toString())){
            monsterNameLabel.setColor(skin.getColor("yellow"));
        }
        else {
            monsterNameLabel.setColor(skin.getColor("white"));
        }
        monsterNameLabel.setText("" + monster.getMonsterRarity() + " " + monster.getMonstername());
        monsterHpBar.setValue((monster.getHealth()*100) / monster.getMaxHp());
        monsterHpLabel.setText("Hp: "+monster.getHealth());
        monsterStrengthLabel.setText("Strength: " + monster.getMonsterstrength());
        monsterDexterityLabel.setText("Dexterity: " +monster.getMonsterdexterity());
        monsterStaminaLabel.setText("Stamina: " + monster.getMonsterstamina());
        monsterDamageIndicatorLabel.setText(" -" + monster.getDamage());
        heroDamageIndicatorLabel.setText(" -" +hero.getDamage());

    }


    private void initGameplayScreenButton() {
        gameplayScreenButton = new GameplayScreenButton(skin, "round", new IClickCallback() {
            @Override
            public void onClick() {
                if (loopbattle){
                    loopbattle = false;
                }
                else {
                    loopbattle = true;
                }
                dispose();
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




// Depth label and depth button settings

        depthlabel.setText("Depth: " + game.getScoreService().getDepth());
        if (game.getScoreService().getKilledMonsters() >= 5){
            depthButton.setVisible(true);
        }
        else {
            depthButton.setVisible(false);
        }

        stage.act();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
