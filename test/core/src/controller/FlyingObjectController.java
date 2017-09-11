package controller;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.myGdxGame.game.MyGdxGame;
import entities.FlyingObject;

/**
 * Created by Stefcio on 11.09.2017.
 */
public class FlyingObjectController {

    private int spawnTime;
    public FlyingObjectController(MyGdxGame game, Stage stage){
        init(game, stage);

    }

    private void init(final MyGdxGame game, final Stage stage) {
        randomizeSpawnTime();

        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {

                Timer.schedule(new Timer.Task() {

                    @Override
                    public void run() {

                        addFlyingObjectToStage(game, stage);
                        randomizeSpawnTime();

                    }
                }, spawnTime);
            }
        }, 0, 5);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(5, 10);
    }


    private void addFlyingObjectToStage(MyGdxGame game, Stage stage){
        FlyingObject flyingObject = null;

        if (MathUtils.randomBoolean()){
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.MONEY, game);

        }
        else {
            flyingObject = new FlyingObject(FlyingObject.FlyingObjectType.MONEY, game);
        }

        stage.addActor(flyingObject);
        flyingObject.fly();

    }
}
