package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Stefcio on 14.02.2018.
 */
public class StatsWindow extends Window {


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

//        this.setPosition(480 - this.getWidth()/2, 700 - this.getHeight()/2);
        this.setSize(300, 400);
        this.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setPosition(0, 0);

    }


}
