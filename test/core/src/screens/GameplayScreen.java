package screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.myGdxGame.game.MyGdxGame;
import entities.Player;
import ui.IClickCallback;
import ui.PlayerButton;
import ui.PointsLabel;
import ui.ResetScoreButton;


/**
 * Created by Stefcio on 2017-08-16.
 */
public class GameplayScreen extends AbstractScreen {

    private Image backgroundImage;
    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private PointsLabel pointsLabel;

    public GameplayScreen(MyGdxGame game) {
        super(game);
        init();

    }

    private void init() {
        initBackground();
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initPointsLabel();

    }

    private void initBackground() {
        backgroundImage = new Image(new Texture("background.png"));
        stage.addActor(backgroundImage);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.resetGamePoints();
            }
        });
        stage.addActor(resetScoreButton);

        resetScoreButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.resetGamePoints();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }


    private void initPointsLabel() {
        pointsLabel = new PointsLabel();
        stage.addActor(pointsLabel);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.addPoint();
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
        pointsLabel.setText("Points: " + game.getPoints());
        stage.act();
    }
}
