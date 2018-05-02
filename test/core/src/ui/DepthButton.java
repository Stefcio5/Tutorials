package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 29.01.2018.
 */
public class DepthButton extends TextButton {

        public DepthButton(Skin skin, String styleName, final IClickCallback callback){
            super("Increase depth", skin, styleName);
            init(callback);

        }

        private void init(final IClickCallback callback) {
//            this.setWidth(120);
//            this.setHeight(50);
//            this.setX(20);
//            this.setY(540);
            this.setDebug(true);

            this.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    callback.onClick();
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }

    }

