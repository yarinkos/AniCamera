package com.example.yarinkossover.snapapp.scenes;

import android.graphics.Typeface;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.options.ConfigChooserOptions;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.LoopEntityModifier.ILoopEntityModifierListener;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.StrokeFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.LoopModifier;

import java.io.IOException;
import java.util.ArrayList;


/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Ncolas Gramlich
 * @since 11:54:51 - 03.04.2010
 */
public class BaseSceneActivity extends SimpleBaseGameActivity {

    protected final String TAG = this.getClass().getSimpleName();


// ===========================================================
// Constants
// ===========================================================

    private static int CAMERA_WIDTH = 720;
    private static int CAMERA_HEIGHT = 1280;
    // ===========================================================
// Fields
// ===========================================================
    private BuildableBitmapTextureAtlas mBitmapTextureAtlas;

    private TiledTextureRegion mSantaDonkeyTextureRegion;
    private TiledTextureRegion mSantaWalkingTextureRegion;
    private TiledTextureRegion mSnowmanTextureRegion;
    private TiledTextureRegion mSantaSleighTextureRegion;
    private TextureRegion mCloudTextureRegion;
    private TextureRegion mSunTextureRegion;


    ArrayList<BaseAnimationModel> baseAnimationModels = new ArrayList<>();
    ArrayList<BaseSpriteModel> baseSprite = new ArrayList<>();
    private String mSantaDonkeyFileName = "CloudRain.png";
    private long mSantaDonkeyFrameDuration = 125;
    private int mSantaDonkeyFrameCount = 17;
    private long[] mSantaDonkeyAnimtedArray = new long[]{125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125};
    private int mSantaDonkeyfirstTileRow = 0;
    private int mSantaDonkeylastTileRow = 16;

    private String mSunFileName = "sun.png";
    private long mSunFrameDuration = 1000;
    private int mSunFrameCount = 1;


    private long[] mSantaWalkingAnimtedArray = new long[]{125, 125, 125, 125, 125};
    private int mSantaWalkingfirstTileRow = 0;
    private int mSantaWalkinglastTileRow = 4;

    private long[] mSnowmanAnimtedArray = new long[]{125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125};
    private int mSnowmanfirstTileRow = 0;
    private int mSnowmanlastTileRow = 13;

    private long[] mSantaSleighAnimtedArray = new long[]{125, 125, 125, 125};
    private int mSantaSleighfirstTileRow = 8;
    private int mSantaSleighlastTileRow = 11;

    private Sprite face1;
    private static Sprite face2, face3, face4;

    private static AnimatedSprite mAnimatedPress = null;

    private Font mFont;
    private Font mFont1;
    private Font mPlokFont;
    private StrokeFont mStrokeOnlyFont;
    private Text text1;
    private Text textNormal;


    Scene scene;
    int i = 0;
// ===========================================================
// Constructors
// ===========================================================

// ===========================================================
// Getter & Setter
// ===========================================================

// ===========================================================
// Methods for/from SuperClass/Interfaces
// ===========================================================


    public void doSome() {
/*
        mAnimatedPressTiledTextureRegion = mSantaDonkeyTextureRegion;
        mAnimatedPressAnimtedArray = mSantaDonkeyAnimtedArray;
        firstTileRow = mSantaDonkeyfirstTileRow;
        lastTileRow = mSantaDonkeylastTileRow;
*/

        animationIndex = animationIndex >= 5 ? 0 : animationIndex;
        initCurrentAnimation(animationIndex);

        switchAnimatedSprite();
        if (animationIndex == 0)
            mAnimatedPress.animate(mSantaDonkeyFrameDuration, mSantaDonkeyFrameCount, true);
        if (animationIndex == 4) {
        }//mAnimatedPress.animate(mSantaDonkeyFrameDuration, mSantaDonkeyFrameCount, true);
        else
            mAnimatedPress.animate(mAnimatedPressAnimtedArray, firstTileRow, lastTileRow, true);
        //onStopStartAnimation(mAnimatedPress);
        animationIndex++;
    }

    private void initAnimationModels() {
        baseAnimationModels.add(new BaseAnimationModel(mSantaDonkeyTextureRegion, mSantaDonkeyAnimtedArray, mSantaDonkeyfirstTileRow, mSantaDonkeylastTileRow));
        baseAnimationModels.add(new BaseAnimationModel(mSantaWalkingTextureRegion, mSantaWalkingAnimtedArray, mSantaWalkingfirstTileRow, mSantaWalkinglastTileRow));
        baseAnimationModels.add(new BaseAnimationModel(mSnowmanTextureRegion, mSnowmanAnimtedArray, mSnowmanfirstTileRow, mSnowmanlastTileRow));
        baseAnimationModels.add(new BaseAnimationModel(mSantaSleighTextureRegion, mSantaSleighAnimtedArray, mSantaSleighfirstTileRow, mSantaSleighlastTileRow));
        baseSprite.add(new BaseSpriteModel(mSunTextureRegion));

    }

    int animationIndex = 0;

    private void switchAnimatedSprite() {

        //mAnimatedPress.detachChild(face1);
        scene.unregisterTouchArea(mAnimatedPress);
        scene.detachChild(mAnimatedPress);
        mAnimatedPress.detachSelf();
        mAnimatedPress.dispose();
        mAnimatedPress = null;

        mAnimatedPress = new AnimatedSprite(100, 100, CAMERA_WIDTH / 4, CAMERA_HEIGHT / 4, this.mAnimatedPressTiledTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                return true;
            }
        };
        //mAnimatedPress.animate(mAnimatedPressAnimtedArray, firstTileRow, lastTileRow, true);
        //mAnimatedPress.setZIndex(-100);
        //mAnimatedPress.attachChild(face1);

        scene.registerTouchArea(mAnimatedPress);
        scene.attachChild(mAnimatedPress);
        // scene.sortChildren(true);

    }


    Sprite twitterLogo;


    public void onStopStartAnimation(AnimatedSprite mAnimatedSprite) {
        if (mAnimatedSprite.isAnimationRunning()) {
            mAnimatedSprite.stopAnimation(mAnimatedSprite.getCurrentTileIndex());
        } else {
            mAnimatedPress.animate(mAnimatedPressAnimtedArray, firstTileRow, lastTileRow, true);
        }
    }

    public void setAnimtedSpritePress(AnimatedSprite mAnimatedSprite) {
        this.mAnimatedPress = mAnimatedSprite;
    }


    @Override
    public Engine onCreateEngine(final EngineOptions pEngineOptions) {

        Engine e = new LimitedFPSEngine(pEngineOptions, 30);


        return e;
    }


    @Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
        engineOptions.getAudioOptions().setNeedsSound(true);
        final ConfigChooserOptions configChooserOptions = engineOptions.getRenderOptions().getConfigChooserOptions();


        configChooserOptions.setRequestedRedSize(8);
        configChooserOptions.setRequestedGreenSize(8);
        configChooserOptions.setRequestedBlueSize(8);
        configChooserOptions.setRequestedAlphaSize(8);
        configChooserOptions.setRequestedDepthSize(16);
        return engineOptions;
    }


    private Sound mExplosionSound;

    @Override
    public void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/band scene/");

        this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 4096, 4096, TextureOptions.BILINEAR);

        this.mSantaDonkeyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, mSantaDonkeyFileName, 6, 3); //col/row
        this.mSantaWalkingTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "SantaWalking.png", 3, 2);
        this.mSnowmanTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "CoolCat.png", 4, 4);
        this.mSantaSleighTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "animal1.png", 4, 4);
        this.mCloudTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "bubble.png");
        this.mSunTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, mSunFileName);
//        this.mTwitterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "Feces.png");
//        this.mCharacterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "messi4.png");

        final ITexture strokeOnlyFontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);

        try {
            this.mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            this.mBitmapTextureAtlas.load();
        } catch (TextureAtlasBuilderException e) {
            Debug.e(e);
        }


        FontFactory.setAssetBasePath("font/");
        this.mPlokFont = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 512, 512, TextureOptions.BILINEAR, this.getAssets(), "Plok.ttf", 52f, false, Color.BLACK_ABGR_PACKED_INT);
        this.mPlokFont.load();
        final ITexture fontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        this.mFont1 = new Font(this.getFontManager(), fontTexture, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 42, false, Color.BLUE);
        this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, TextureOptions.BILINEAR, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32f, Color.ABGR_PACKED_BLUE_CLEAR);
        this.mFont.load();
        this.mFont1.load();

        this.mStrokeOnlyFont = new StrokeFont(this.getFontManager(), strokeOnlyFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 42, true, Color.BLACK, 2, Color.WHITE);
        this.mStrokeOnlyFont.load();

        SoundFactory.setAssetBasePath("mfx/");
        try {
            this.mExplosionSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "munch.ogg");
        } catch (final IOException e) {
            Debug.e(e);
        }

    }


    String[] mStringArray = new String[10];
    int stringCount;

    private void initializeWordsArray() {
        mStringArray[0] = "Stupid  \n  !!";
        mStringArray[1] = "FAT  \n  !!";
        mStringArray[2] = "AmazinGG  \n  !!";
        mStringArray[3] = "Lovely..  \n  !!";
        stringCount = 0;
    }


    BaseAnimationModel currentBaseAnimationModel;


    private void initCurrentAnimation(int i) {
        this.currentBaseAnimationModel = baseAnimationModels.get(i);
        mAnimatedPressTiledTextureRegion = currentBaseAnimationModel.getTiledTextureRegion();
        mAnimatedPressAnimtedArray = currentBaseAnimationModel.getAnimationArrayFrames();
        firstTileRow = currentBaseAnimationModel.getStartIndex();
        lastTileRow = currentBaseAnimationModel.getStopIndex();
    }

    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        initAnimationModels();
        initializeWordsArray();
        initCurrentAnimation(0);
        scene = new Scene();
        scene.getBackground().setColor(Color.TRANSPARENT);


        mAnimatedPress = new AnimatedSprite(CAMERA_WIDTH * 0.33f, CAMERA_HEIGHT * 0.33f, CAMERA_WIDTH / 4, CAMERA_HEIGHT / 4, this.mAnimatedPressTiledTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                return true;
            }
        };

        if (true) {
            scene.registerTouchArea(mAnimatedPress);
            scene.attachChild(mAnimatedPress);
            scene.setTouchAreaBindingOnActionDownEnabled(true);
            return scene;
        }
        Sprite ground = new Sprite(mAnimatedPress.getWidth() * 1.5f, mAnimatedPress.getHeight() / 2, mAnimatedPress.getWidth(), mAnimatedPress.getHeight(), this.mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                //  this.setPosition(pSceneTouchEvent.getX() , pSceneTouchEvent.getY() );
                BaseSceneActivity.this.mExplosionSound.play();
                return true;
            }
        };
        textNormal = new Text(ground.getWidth() / 2, ground.getHeight() / 2, this.mPlokFont, "   Hi Shahar!\n\nYou Are \nMy First Fan!!\n Tnx!.", getVertexBufferObjectManager());
        textNormal.setText("WowWWW \n! !");
        ground.attachChild(textNormal);
        mAnimatedPress.attachChild(ground);

        mAnimatedPress.animate(mAnimatedPressAnimtedArray, firstTileRow, lastTileRow, true);
        mAnimatedPress.setScale(1f);
        mAnimatedPress.setZIndex(100);
        scene.registerTouchArea(mAnimatedPress);


        PhysicsHandler m1 = new PhysicsHandler(mAnimatedPress);
        mAnimatedPress.registerUpdateHandler(m1);
        m1.setVelocityX(-120);

        scene.registerTouchArea(mAnimatedPress);
        scene.attachChild(mAnimatedPress);


        scene.setTouchAreaBindingOnActionDownEnabled(true);


        scene.sortChildren(true);


        final LoopEntityModifier entityModifier =
                new LoopEntityModifier(
                        new IEntityModifierListener() {
                            @Override
                            public void onModifierStarted(final IModifier<IEntity> pModifier, final IEntity pItem) {

                            }

                            @Override
                            public void onModifierFinished(final IModifier<IEntity> pEntityModifier, final IEntity pEntity) {

                            }
                        },
                        100,
                        new ILoopEntityModifierListener() {
                            @Override
                            public void onLoopStarted(final LoopModifier<IEntity> pLoopModifier, final int pLoop, final int pLoopCount) {
                            }

                            @Override
                            public void onLoopFinished(final LoopModifier<IEntity> pLoopModifier, final int pLoop, final int pLoopCount) {
                            }
                        },
                        new SequenceEntityModifier(
// new RotationModifier(1, 0, 90),
                                new ParallelEntityModifier(
                                        new ScaleModifier(0.5f, 0.7f, 0.9f), new RotationModifier(1f, -15, 15)),
                                new ParallelEntityModifier(
                                        new ScaleModifier(0.5f, 0.9f, 0.7f), new RotationModifier(1f, 15, -15))
                        )
                );

        return scene;
    }


    TiledTextureRegion mAnimatedPressTiledTextureRegion;
    long[] mAnimatedPressAnimtedArray;
    int firstTileRow;
    int lastTileRow;
    int dd = 0;

    class BaseAnimationModel {


        public TiledTextureRegion getTiledTextureRegion() {
            return tiledTextureRegion;
        }

        TiledTextureRegion tiledTextureRegion;

        public int getStartIndex() {
            return startIndex;
        }

        int startIndex;

        public int getStopIndex() {
            return stopIndex;
        }

        int stopIndex;

        public long[] getAnimationArrayFrames() {
            return animationArrayFrames;
        }

        long[] animationArrayFrames;

        public long getFrameDuration() {
            return frameDuration;
        }

        public void setFrameDuration(long frameDuration) {
            this.frameDuration = frameDuration;
        }

        long frameDuration;

        int frameCount;

        public int getFrameCount() {
            return frameCount;
        }

        public void setFrameCount(int frameCount) {
            this.frameCount = frameCount;
        }


        public BaseAnimationModel(TiledTextureRegion tiledTextureRegion, long[] animationArrayFrames, int startIndex, int stopIndex) {
            // this.animatedSprite=animatedSprite;
            this.tiledTextureRegion = tiledTextureRegion;
            this.startIndex = startIndex;
            this.stopIndex = stopIndex;
            this.animationArrayFrames = animationArrayFrames;
        }

        public BaseAnimationModel(TiledTextureRegion tiledTextureRegion, long pFrameDuration, int pFrameCount) {
            this.tiledTextureRegion = tiledTextureRegion;
            this.frameDuration = pFrameDuration;
            this.frameCount = pFrameCount;

        }


    }

    class BaseSpriteModel {

        TextureRegion textureRegion;

        public BaseSpriteModel(TextureRegion pTextureRegion) {
            this.textureRegion = pTextureRegion;

        }

    }

}
// ===========================================================
// Methods
// ===========================================================

// ===========================================================
// Inner and Anonymous Classes
// ===========================================================


