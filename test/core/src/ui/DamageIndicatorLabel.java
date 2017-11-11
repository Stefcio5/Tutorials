package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Stefcio on 11.11.2017.
 */
public class DamageIndicatorLabel extends Label {

    public DamageIndicatorLabel(){
        super("", labelStyle());
    }

    public static LabelStyle labelStyle(){
        LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        return labelStyle;
    }
}