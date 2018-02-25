package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Stefcio on 17.09.2017.
 */
public class GameplayScreenButton extends TextButton {
    public GameplayScreenButton(Skin skin, String styleName, final IClickCallback callback) {
        super("Training", skin, styleName);
        init(callback);
    }

    private void init(final IClickCallback callback){
        this.setWidth(120);
        this.setHeight(50);
        this.setX(10);
        this.setY(10);
        this.setDebug(true);
        this.setTransform(true);
//        this.getLabel().setFontScale(0.6f, 0.6f);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });


    }
}
