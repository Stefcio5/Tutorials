package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Stefcio on 06.09.2017.
 */
public class ResetScoreButton extends Button{
    public ResetScoreButton(final IClickCallback callback) {
        super(prepareResetButtonStyle());

        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setSize(getWidth(), getHeight());
        this.setX(330);
        this.setY(560);
        this.setDebug(true);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);

            }
        });
    }
    private static ButtonStyle prepareResetButtonStyle(){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("rusty-robot-ui.atlas"));
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("button");
        buttonStyle.down = skin.getDrawable("button-pressed");

        
        return buttonStyle;
    }
}
