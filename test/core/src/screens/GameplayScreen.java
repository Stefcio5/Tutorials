package screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.MyGdxGame;
import entities.Player;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDown;


/**
 * Created by Stefcio on 2017-08-16.
 */
public class GameplayScreen extends AbstractScreen{

    private Player player;
    private Button playerButton;
    private Button resetScoreButton;
    private Label pointsLabel;

    public GameplayScreen(MyGdxGame game) {
        super(game);
        init();

    }

    private void init(){
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initPointsLabel();

    }

    private void initResetScoreButton() {
        resetScoreButton = new Button(new ButtonStyle());
        resetScoreButton.setWidth(100);
        resetScoreButton.setHeight(100);
        resetScoreButton.setX(330);
        resetScoreButton.setY(560);
        resetScoreButton.setDebug(true);

        stage.addActor(resetScoreButton);
        resetScoreButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.resetGamePoints();
                return super.touchDown(event, x, y, pointer, button);
            }
        }
    })





    private void initPointsLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        pointsLabel = new Label("", labelStyle);
        pointsLabel.setX(20);
        pointsLabel.setY(650);
        stage.addActor(pointsLabel);
    }

    private void initPlayerButton() {
        playerButton = new Button(new ButtonStyle());
        playerButton.setWidth(460);
        playerButton.setHeight(360);
        playerButton.setX(10);
        playerButton.setY(170);
        playerButton.setDebug(true);

        stage.addActor(playerButton);

        playerButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                player.reactOnClick();
                game.addPoint();

                return super.touchDown(event, x, y, pointer, button);
            }
        });


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
        pointsLabel.setText("Points: "+game.getPoints());
        stage.act();
    }
}
