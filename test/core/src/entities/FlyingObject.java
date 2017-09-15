package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.myGdxGame.game.MyGdxGame;

/**
 * Created by Stefcio on 10.09.2017.
 */
public class FlyingObject extends Image {

    public enum FlyingObjectType{
        MONEY
    }

    public final static String MONEY = "money.png";

    private static int WIDTH = 150;
    private static int HEIGHT = 150;

    private static final int STARTING_X_1 = 0;
    private static final int STARTING_X_2 = MyGdxGame.WIDTH;

    private static final int STARTING_Y = -100;

    private int startingX;

    private MyGdxGame game;
    private FlyingObjectType type;

    public FlyingObject(FlyingObjectType type, MyGdxGame game){
        super(new Texture(getTextureString(type)));

        this.game = game;
        this.type = type;

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        startingX = MathUtils.randomBoolean() ? STARTING_X_1 : STARTING_X_2;

        this.setPosition(startingX, STARTING_Y);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                reactOnClick();

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        }

    private void reactOnClick() {
        if(FlyingObjectType.MONEY.equals(type)){
            game.getScoreService().addPoints(50);
        }
        FlyingObject.this.remove();
    }

    private static String getTextureString(FlyingObjectType type) {
        if(FlyingObjectType.MONEY.equals(type)){
            return MONEY;
        }
        return "";

    }

    public void fly(){

        int xSign;
        int time1 = MathUtils.random(1, 6);
        int time2 = MathUtils.random(1, 6);
        int RandomYEffect = MathUtils.random(-100, 500);
        if(startingX==STARTING_X_1){
            xSign = 1;
        }else {
            xSign = -1;
        }
            Action a = Actions.parallel(
                    Actions.moveBy(xSign*300 + MathUtils.random(-200, 200), 200 + RandomYEffect , time1),
                    Actions.rotateBy(360, time1)
            );

            Action b = Actions.parallel(
                    Actions.moveBy(xSign*(-500)+ MathUtils.random(-200, 200), 900 + RandomYEffect, time2),
                    Actions.rotateBy(360, time2)
            );

            Action c = Actions.run(new Runnable() {

                @Override
                public void run() {
                    FlyingObject.this.remove();
                }
            });


            this.addAction(Actions.sequence(a, b, c));
        }
        }



