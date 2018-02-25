package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Stefcio on 17.09.2017.
 */
public class BattleScreenButton extends TextButton{
    public BattleScreenButton(String text, Skin skin, String styleName, final IClickCallback callback) {
        super("Battle",skin, styleName);
        init(callback);
    }
    private void init(final IClickCallback callback){
        this.setWidth(100);
        this.setHeight(50);
        this.setX(10);
        this.setY(10);
        this.setDebug(true);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });


    }
    private static TextButtonStyle prepareTextButtonStyle(){
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        return textButtonStyle;
    }


}
