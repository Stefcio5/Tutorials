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
    private PointsLabel pointsLabel;
    private AttributeLabel strengthLabel;
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
        initPointsLabel();
        initStrengthLabel();
        initPointProgressBar();
        initFlyingObjectController();

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
                game.getScoreService().addStrength();
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
        pointsLabel.setText("Points: " + game.getScoreService().getPoints());
        strengthLabel.setText("Strength: " +game.getScoreService().getStrength());
        pointsProgressBar.setValue(game.getScoreService().getPoints());

        stage.act();
    }
}
