package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.myGdxGame.game.MyGdxGame;
import ui.Assets;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Stefcio on 2017-08-15.
 */
public class SplashScreen extends AbstractScreen {



    private Texture splashImg;


    public SplashScreen(final MyGdxGame game) {
        super(game);
        init();
    }


    private void init() {


        //TODO implements better assets loading
        splashImg = new Texture("Splash screen.jpg");



        game.assets.loadTextures();
//        assets.loadFonts();
//        assets.manager.finishLoading();
//        game.setScreen(new GameplayScreen(game));




//        while (!assets.manager.update()) {
//            Gdx.app.log("AssetManager", "Progress: " + assets.manager.getProgress() * 100);
//            game.setScreen(new GameplayScreen(game));
//
//
//        }

//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
//                game.setScreen(new GameplayScreen(game));
//
//            }
//        },5);
//    }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();

        while (!game.assets.manager.update()){
            Gdx.app.log("AssetManager", "Progress: " + game.assets.manager.getProgress() * 100);
        }

        if (game.assets.manager.update()) {


            com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new GameplayScreen(game));
                    dispose();

                }
            }, 2);

        }



    }

}

