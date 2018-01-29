package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myGdxGame.game.MyGdxGame;
import controller.FlyingObjectController;
import entities.Player;
import ui.*;


/**
 * Created by Stefcio on 2017-08-16.
 */
public class GameplayScreen extends AbstractScreen {

    private Image backgroundImage;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private BattleScreenButton battleScreenButton;
    private StrengthButton strengthButton;

    private AttributeLabel levelLabel;
    private PointsLabel pointsLabel;
    private AttributeLabel strengthLabel;
    private AttributeLabel attributeLabel;
    private FlyingObjectController flyingObjectController;
    private PointsProgressBar pointsProgressBar;

    public GameplayScreen(MyGdxGame game) {
        super(game);
        init();

    }

    private void init() {
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

    private void initStrengthButton() {
        strengthButton = new StrengthButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().addStrength();
            }
        });
        strengthButton.setText("Add strength");
        stage.addActor(strengthButton);
    }

    private void initAttributeLabel() {
        attributeLabel = new AttributeLabel();
        attributeLabel.setX(20);
        attributeLabel.setY(610);
        stage.addActor(attributeLabel);
    }

    private void initLevelLabel() {
        levelLabel = new AttributeLabel();
        levelLabel.setX(20);
        levelLabel.setY(670);
        stage.addActor(levelLabel);
    }

    private void initGameplayScreenButton() {
        battleScreenButton = new BattleScreenButton(new IClickCallback() {
            @Override
            public void onClick() {
                Gdx.app.log("My tag", "BattleScreenButton clicked");
                game.setScreen(new BattleScreen(game));
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
        pointsLabel = new PointsLabel();
        pointsLabel.setX(20);
        pointsLabel.setY(630);
        stage.addActor(pointsLabel);
    }
    private void initStrengthLabel() {
        strengthLabel = new AttributeLabel();
        strengthLabel.setX(20);
        strengthLabel.setY(650);
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
        attributeLabel.setText("Attribute points to add: " + game.getScoreService().getAttributes());
        pointsProgressBar.setValue(game.getScoreService().getPoints());

        stage.act();
    }
}
