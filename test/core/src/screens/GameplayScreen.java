package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.myGdxGame.game.MyGdxGame;
import controller.FlyingObjectController;
import entities.Player;
import ui.*;


/**
 * Created by Stefcio on 2017-08-16.
 */
public class GameplayScreen extends AbstractScreen {

    private Skin skin;

    private Image backgroundImage;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private BattleScreenButton battleScreenButton;
    private StrengthButton strengthButton;
    private StatsWindow statsWindow;

    private Label levelLabel;
    private Label pointsLabel;
    private Label strengthLabel;
    private Label attributeLabel;
    private FlyingObjectController flyingObjectController;
    private PointsProgressBar pointsProgressBar;



    public GameplayScreen(MyGdxGame game) {
        super(game);
        init();

    }

    private void init() {

        initSkins();
        initBackground();
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initGameplayScreenButton();
        initStrengthButton();
        initLevelLabel();
        initPointsLabel();
        initStrengthLabel();
        initAttributeLabel();
        initPointProgressBar();
        initFlyingObjectController();



    }

    private void initSkins() {
       skin = game.assets.manager.get(game.assets.uiSkin);

    }


    private void initStrengthButton() {
        strengthButton = new StrengthButton(skin, "default", new IClickCallback() {
            @Override
            public void onClick() {
                statsWindow = new StatsWindow(skin);
                stage.addActor(statsWindow);
            }
        });
        strengthButton.setText("Add strength");
        stage.addActor(strengthButton);
    }

    private void initAttributeLabel() {
        attributeLabel = new Label("Attribute", skin, "subtitle");
        attributeLabel.setX(20);
        attributeLabel.setY(610);
        attributeLabel.setWidth(150);
        stage.addActor(attributeLabel);
    }

    private void initLevelLabel() {
        levelLabel = new Label("Level", skin, "subtitle");
        levelLabel.setX(20);
        levelLabel.setY(670);
        levelLabel.setWidth(100);
        stage.addActor(levelLabel);
    }

    private void initGameplayScreenButton() {
        battleScreenButton = new BattleScreenButton("", skin, "round", new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("My tag", "BattleScreenButton clicked");
                game.setScreen(new BattleScreen(game));
                dispose();
            }
        });
        stage.addActor(battleScreenButton);

    }

    private void initPointProgressBar() {
        pointsProgressBar = new PointsProgressBar(0, 100, 1, false);
        pointsProgressBar.setAnimateDuration(0.25f);
        stage.addActor(pointsProgressBar);
    }


    private void initFlyingObjectController() {
        flyingObjectController = new FlyingObjectController(game, stage);


    }


    private void initBackground() {
        backgroundImage = new Image(new Texture("background.png"));
        stage.addActor(backgroundImage);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().resetGamePoints();
            }
        });
        stage.addActor(resetScoreButton);

    }


    private void initPointsLabel() {
        pointsLabel = new Label("Points", skin, "subtitle");
        pointsLabel.setX(20);
        pointsLabel.setY(630);
        pointsLabel.setWidth(100);
        stage.addActor(pointsLabel);
    }
    private void initStrengthLabel() {
        strengthLabel = new Label("Strength", skin, "subtitle");
        strengthLabel.setX(20);
        strengthLabel.setY(650);
        strengthLabel.setWidth(100);

        stage.addActor(strengthLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
                game.getScoreService().addAttribute();
            }
        });

        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();


        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        levelLabel.setText("Level: " + game.getScoreService().getLevel());
        pointsLabel.setText("Points: " + game.getScoreService().getPoints());
        strengthLabel.setText("Strength: " +game.getScoreService().getStrength());
        attributeLabel.setText("Attribute points: " + game.getScoreService().getAttributes());
        pointsProgressBar.setValue(game.getScoreService().getPoints());

        stage.act();
    }
}
