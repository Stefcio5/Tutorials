package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Stefcio on 17.09.2017.
 */
public class GameplayScreenButton extends TextButton {
    public GameplayScreenButton(final IClickCallback callback) {
        super("GameplayScreen", prepareTextButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback){
        this.setWidth(120);
        this.setHeight(50);
        this.setX(5);
        this.setY(5);
        this.setDebug(true);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });


    }
    private static TextButton.TextButtonStyle prepareTextButtonStyle(){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        return textButtonStyle;
    }
}
