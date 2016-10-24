package com.example.yarinkossover.snapapp;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.ConfigChooserOptions;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 11:54:51 - 03.04.2010
 */
public class SpriteExample extends SimpleBaseGameActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    private static final int CAMERA_WIDTH = 480;
    private static final int CAMERA_HEIGHT = 720;

    // ===========================================================
    // Fields
    // ===========================================================

    private ITexture mFaceTexture;
    private ITextureRegion mFaceTextureRegion;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    /*@Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT) , camera);//new FillResolutionPolicy()
        final ConfigChooserOptions configChooserOptions = engineOptions.getRenderOptions().getConfigChooserOptions();
        configChooserOptions.setRequestedRedSize(8);
        configChooserOptions.setRequestedGreenSize(8);
        configChooserOptions.setRequestedBlueSize(8);
        configChooserOptions.setRequestedAlphaSize(8);
        configChooserOptions.setRequestedDepthSize(16);
        return engineOptions;
    }*/

    @Override
    public EngineOptions onCreateEngineOptions() {

        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
        final ConfigChooserOptions configChooserOptions = engineOptions.getRenderOptions().getConfigChooserOptions();
        configChooserOptions.setRequestedRedSize(8);
        configChooserOptions.setRequestedGreenSize(8);
        configChooserOptions.setRequestedBlueSize(8);
        configChooserOptions.setRequestedAlphaSize(8);
        configChooserOptions.setRequestedDepthSize(16);
        return engineOptions;
    }

    @Override
    public void onCreateResources() throws IOException {
        this.mFaceTexture = new AssetBitmapTexture(this.getTextureManager(), getActivity().getAssets(), "gfx/face_box.png");
        this.mFaceTextureRegion = TextureRegionFactory.extractFromTexture(this.mFaceTexture);
        this.mFaceTexture.load();
    }

    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene = new Scene();
        scene.getBackground().setColor(Color.TRANSPARENT);

        final float centerX = CAMERA_WIDTH / 2;
        final float centerY = CAMERA_HEIGHT / 2;

		/* Create the sprite and add it to the scene. */
        final Sprite sprite = new Sprite(centerX, centerY,100,100, this.mFaceTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                return true;
            }
        };
        scene.registerTouchArea(sprite);
        scene.attachChild(sprite);
        scene.setTouchAreaBindingOnActionDownEnabled(true);



        /**
         *  mAnimatedPress = new AnimatedSprite(CAMERA_WIDTH * 0.33f, CAMERA_HEIGHT * 0.33f, CAMERA_WIDTH / 4, CAMERA_HEIGHT / 4, this.mAnimatedPressTiledTextureRegion, this.getVertexBufferObjectManager()) {
        @Override public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
        this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
        return true;
        }
        };

         if(true) {
         scene.registerTouchArea(mAnimatedPress);
         scene.attachChild(mAnimatedPress);
         scene.setTouchAreaBindingOnActionDownEnabled(true);
         return scene;
         }
         */
        return scene;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}