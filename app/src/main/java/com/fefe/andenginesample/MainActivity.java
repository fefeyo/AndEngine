package com.fefe.andenginesample;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import java.io.IOException;

public class MainActivity extends SimpleBaseGameActivity {

    private static final int CAMERA_WIDTH = 800;
    private static final int CAMERA_HEIGHT = 480;


    private ITexture mHello;
    private ITextureRegion mHelloRegion;

    @Override
    protected void onCreateResources() {
        try {
            this.mHello = new AssetBitmapTexture(
                    this.getTextureManager(),
                    this.getAssets(),
                    "helloworld.png"
            );
        }catch (IOException e) {
            e.printStackTrace();
        }
        this.mHelloRegion = TextureRegionFactory.extractFromTexture(mHello);
        this.mHello.load();
    }

    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene = new Scene();
        scene.getBackground().setColor(Color.YELLOW);

        final float centerX = CAMERA_WIDTH / 2;
        final float centerY = CAMERA_HEIGHT / 2;

        final Sprite sprite = new Sprite(
                centerX,
                centerY,
                this.mHelloRegion,
                this.getVertexBufferObjectManager()
        );
        scene.attachChild(sprite);

        return scene;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        return new EngineOptions(
                true,
                ScreenOrientation.LANDSCAPE_SENSOR,
                new RatioResolutionPolicy(
                        CAMERA_WIDTH,
                        CAMERA_HEIGHT
                ),
                camera
        );
    }
}
