package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.myGdxGame.game.MyGdxGame;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDown;

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

    private static final int STARTING_X = 0;
    private static final int STARTING_Y = -100;

    private MyGdxGame game;
    private FlyingObjectType type;

    public FlyingObject(FlyingObjectType type, MyGdxGame game){
        super(new Texture(getTextureString(type)));

        this.game = game;
        this.type = type;

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(STARTING_X, STARTING_Y);

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
            game.addPoints(50);
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
            Action a = Actions.parallel(
                    Actions.moveBy(300, 200, 5),
                    Actions.rotateBy(360, 5)
            );

            Action b = Actions.parallel(
                    Actions.moveBy(-500, 900, 3),
                    Actions.rotateBy(360, 3)
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



