package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 20.09.2017.
 */
public class BattleButton extends TextButton{

        public BattleButton(Skin skin, String styleName, final IClickCallback callback) {
            super("Battle", skin, styleName);
            init(callback);
        }

        private void init(final IClickCallback callback){
            this.setWidth(120);
            this.setHeight(50);
            //this.setX(MyGdxGame.WIDTH/2-getWidth()/2);
            //this.setY(MyGdxGame.HEIGHT-64f);
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

