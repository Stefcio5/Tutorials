package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Stefcio on 16.09.2017.
 */
public class AttributeLabel extends Label {
    public AttributeLabel(Skin skin, String styleName) {
        super("", skin, styleName);
        init();
    }
    private void init(){
//        this.setX(20);
//        this.setY(630);
    }
    private static LabelStyle prepareLabelStyle() {
        LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        return labelStyle;
    }
    }
