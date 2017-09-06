package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by Stefcio on 2017-08-16.
 */
public class Player extends com.badlogic.gdx.scenes.scene2d.ui.Image {

    private static final int WIDTH = 189;
    private static final int HEIGHT = 300;

    private static final int STARTING_X = 200;
    private static final int STARTING_Y = 300;

    public Player() {
        super(new Texture("Player.png"));

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_X);


    }


    public void reactOnClick() {
        int moveAmount = MathUtils.random(-130, 130);
        Action moveAction = Actions.sequence(Actions.moveBy(moveAmount, 10, 0.30f, Interpolation.swingOut),
                Actions.moveBy(-moveAmount, -10, 0.30f, Interpolation.swingIn));

        int growAmount = MathUtils.random(-30, 150);
        Action growAction = Actions.sequence(Actions.sizeBy(growAmount, 20, 0.2f, Interpolation.swingOut),
                Actions.sizeBy(-growAmount, -20, 0.2f, Interpolation.swingIn));

        this.addAction(moveAction);
        this.addAction(growAction);

        if (this.getHeight() > 170) {
            this.addAction(Actions.rotateBy(MathUtils.randomSign() * 360, 0.4f));
        }


    }
}
