package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Stefcio on 06.09.2017.
 */
public class PointsLabel extends Label {
    public PointsLabel() {
        super("", prepareLabelStyle());
        init();
    }

    private void init() {
//        this.setX(20);
//        this.setY(650);
    }

    private static LabelStyle prepareLabelStyle() {
        LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        return labelStyle;
    }
}
