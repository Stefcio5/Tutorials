package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Stefcio on 16.09.2017.
 */
public class PointsProgressBar extends ProgressBar {

    public PointsProgressBar(float min, float max, float stepSize, boolean vertical) {
        super(min, max, stepSize, vertical, PointsProgressBarStyle());

        this.setX(140);
        this.setY(670);
    }


private static ProgressBarStyle PointsProgressBarStyle(){
    Pixmap pixmap = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.BROWN);
    pixmap.fill();

    TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
    pixmap.dispose();

    ProgressBarStyle progressBarStyle = new ProgressBarStyle();
    progressBarStyle.background = drawable;

// Not usable atm
    pixmap = new Pixmap(0, 20, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.GREEN);
    pixmap.fill();
    drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
    pixmap.dispose();

    progressBarStyle.knob = drawable;


    pixmap = new Pixmap(1, 20, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.YELLOW);
    pixmap.fill();
    drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
    pixmap.dispose();

    progressBarStyle.knobBefore = drawable;

    return progressBarStyle;
}
}
