package ui;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Stefcio on 21.02.2018.
 */
public class Assets implements Disposable {

    public final AssetManager manager = new AssetManager();

    public final String TEXTURE_ATLAS_PATH = "uiskin.atlas";
    public final String TEXTURE_SKIN_PATH = "uiskin.json";
    public final String BUTTON_FONT_PATH = "font-button.fnt";
    public final String LABEL_FONT_PATH = "font-label.fnt";
    public final String TITLE_FONT_PATH = "font-title.fnt";

   public final AssetDescriptor<Skin> uiSkin = new AssetDescriptor<Skin>(TEXTURE_SKIN_PATH, Skin.class,
                    new SkinLoader.SkinParameter(TEXTURE_ATLAS_PATH));

    public void loadTextures(){
       // manager.load(TEXTURE_FILE_PATH, Texture.class);
        //SkinLoader.SkinParameter param = new SkinLoader.SkinParameter(TEXTURE_ATLAS_PATH);
        //manager.load(TEXTURE_SKIN_PATH, Skin.class, param);
        manager.load(uiSkin);
    }
    public void loadFonts(){
        manager.load(BUTTON_FONT_PATH, BitmapFont.class);
        manager.load(LABEL_FONT_PATH, BitmapFont.class);
        manager.load(TITLE_FONT_PATH, BitmapFont.class);

    }

    @Override
    public void dispose() {
        manager.dispose();

    }
}
