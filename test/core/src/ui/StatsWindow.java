package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.myGdxGame.game.MyGdxGame;
import screens.GameplayScreen;

/**
 * Created by Stefcio on 14.02.2018.
 */
public class StatsWindow extends Window {

    private static WindowStyle windowStyle;

    static {

        windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("roottable.png")))));
    }



    public StatsWindow(Skin skin) {
        super("Attributes", skin);


        TextButton.TextButtonStyle closeButtonStyle = new TextButton.TextButtonStyle();
        closeButtonStyle.font = new BitmapFont();

        TextButton closeButton;
        closeButton = new TextButton("X", closeButtonStyle);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);

            }
        });
        getTitleTable().add(closeButton).size(20f, 20f).padRight(0f).padTop(0f);
        getTitleTable().setDebug(true);
        setClip(false);
        setTransform(true);
        setMovable(false);



        this.setSize(300,400);
        this.setPosition(Gdx.graphics.getWidth()/2 - this.getWidth()/2, Gdx.graphics.getHeight()/2 - this.getHeight()/2);


    }






}
