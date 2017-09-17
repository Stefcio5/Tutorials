package screens;

import com.myGdxGame.game.MyGdxGame;
import ui.GameplayScreenButton;
import ui.IClickCallback;

/**
 * Created by Stefcio on 17.09.2017.
 */
public class BattleScreen extends AbstractScreen {
    private GameplayScreenButton gameplayScreenButton;

    public BattleScreen(MyGdxGame game) {
        super(game);
        init();
    }
    private void init(){
        initGameplayScreenButton();
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
