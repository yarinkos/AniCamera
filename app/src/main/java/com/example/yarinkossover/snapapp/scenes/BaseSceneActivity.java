package com.example.yarinkossover.snapapp.scenes;

import android.graphics.Typeface;
import android.util.Log;

import com.example.yarinkossover.snapapp.model.animations.PostAnimationData;
import com.example.yarinkossover.snapapp.model.animations.SpriteModel;
import com.example.yarinkossover.snapapp.scenes.modifiers.ModifiersCreators;

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
import org.andengine.entity.modifier.IEntityModifier;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


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


    HashMap<String, BaseAnimationModel> baseAnimationModels = new HashMap<>();
    HashMap<String, BaseSpriteModel> baseSpriteModels = new HashMap<>();

    private String mSantaDonkeyFileName = "CloudRain.png";
    private String mSunFileName = "sun.png";

    private static AnimatedSprite mAnimatedPress = null;

    private Font mFont;
    private Font mFont1;
    private Font mPlokFont;
    private StrokeFont mStrokeOnlyFont;
    private Text text1;
    private Text textNormal;

    @Getter
    @Setter
    PostAnimationData postAnimationData;

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


    ModifiersCreators modifiersCreators;

    public void setDemoPost() {
        postAnimationData = new PostAnimationData();
        postAnimationData.setSprites(new SpriteModel[]{new SpriteModel("Sun", "1", "2")}); //todo add special id for different paths in PathModifers
    }

    public void doSome() {

        //   createAnimatedSprite("SantaDonkey");
        resetScene();
        setDemoPost();
        modifiersCreators = ModifiersCreators.getInstance(CAMERA_WIDTH, CAMERA_HEIGHT);
        for (SpriteModel spriteModel : postAnimationData.getSprites()) {
            Sprite s = createSprite(spriteModel.getSpriteName());
            for (String iEntityModifierId : Arrays.asList(spriteModel.getModifiers())) {
                s.registerEntityModifier(modifiersCreators.getModifierById(Integer.parseInt(iEntityModifierId)));
            }
        }
    }

    private void initAnimationModels() {
        baseAnimationModels.put("SantaDonkey", new BaseAnimationModel("CloudRain.png", 6, 3, 125, 17));
        baseAnimationModels.put("SantaWalking", new BaseAnimationModel("SantaWalking.png", 3, 2, 125, 5));
        baseAnimationModels.put("Snowman", new BaseAnimationModel("CoolCat.png", 4, 4, 125, 14));
        baseAnimationModels.put("SantaSleigh", new BaseAnimationModel("animal1.png", 4, 4, 125, 16));

    }

    private void initSpriteModels() {
        baseSpriteModels.put("Sun", new BaseSpriteModel("sun.png"));
    }

    private AnimatedSprite createAnimatedSprite(String animationKey) {
        Log.d(TAG, "createAnimatedSprite");
        BaseAnimationModel baseAnimationModel = baseAnimationModels.get(animationKey);
        AnimatedSprite animatedSprite = new AnimatedSprite(100, 100, CAMERA_WIDTH / 4, CAMERA_HEIGHT / 4, baseAnimationModel.getTiledTextureRegion(), this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                Log.d(TAG, "DEBUG");
                return true;
            }
        };

        scene.registerTouchArea(animatedSprite);
        scene.attachChild(animatedSprite);

        return animatedSprite;
    }

    private Sprite createSprite(String spriteKey) {
        BaseSpriteModel baseSpriteModel = baseSpriteModels.get(spriteKey);
        Sprite sprite = new Sprite((CAMERA_WIDTH * 5) / 6, (CAMERA_HEIGHT * 5) / 6, CAMERA_WIDTH / 3, CAMERA_HEIGHT / 3, baseSpriteModel.getTextureRegion(), this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                return true;
            }
        };

        scene.registerTouchArea(sprite);
        scene.attachChild(sprite);

        return sprite;
    }

    public void resetScene() {
        clearSceneFromChildren();
    }

    public void clearSceneFromChildren() {
        for (int i = 0; i < scene.getChildCount(); i++) {
            IEntity entity = scene.getChildByIndex(i);
            scene.unregisterTouchArea(entity);
            entity.detachSelf();
            entity.dispose();
            entity = null;
        }
        scene.detachChildren();
        System.gc();

    }

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
        initSpriteModels();
        initAnimationModels();
        for (BaseAnimationModel value : baseAnimationModels.values()) {
            value.setTiledTextureRegion(BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, getActivity(), value.getFileName(), value.getCol(), value.getRow()));
        }
        for (BaseSpriteModel value : baseSpriteModels.values()) {
            value.setTextureRegion(BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, getActivity(), value.getFileName()));
        }

        this.mSantaDonkeyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, getActivity(), mSantaDonkeyFileName, 6, 3); //col/row
        this.mSantaWalkingTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, getActivity(), "SantaWalking.png", 3, 2);
        this.mSnowmanTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, getActivity(), "CoolCat.png", 4, 4);
        this.mSantaSleighTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, getActivity(), "animal1.png", 4, 4);
        this.mCloudTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, getActivity(), "bubble.png");
        this.mSunTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, getActivity(), mSunFileName);
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
        this.mPlokFont = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 512, 512, TextureOptions.BILINEAR, getActivity().getAssets(), "Plok.ttf", 52f, false, Color.BLACK_ABGR_PACKED_INT);
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
            this.mExplosionSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getActivity(), "munch.ogg");
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

        initializeWordsArray();
        //initCurrentAnimation(0);
        scene = new Scene();
        scene.getBackground().setColor(Color.TRANSPARENT);
        scene.setTouchAreaBindingOnActionDownEnabled(true);
        doSome();

        if (true)
            return scene;

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

    class BaseAnimationModel extends BaseSpriteModel {

        @Getter
        @Setter
        long frameDuration;
        @Getter
        @Setter
        int frameCount;
        @Getter
        @Setter
        TiledTextureRegion tiledTextureRegion;
        @Getter
        @Setter
        int startIndex, stopIndex;

        @Getter
        @Setter
        int row, col;
        @Getter
        @Setter
        long[] animationArrayFrames;




      /*  public BaseAnimationModel(TiledTextureRegion tiledTextureRegion, long[] animationArrayFrames, int row, int col, int startIndex, int stopIndex) {
            // this.animatedSprite=animatedSprite;
            this.tiledTextureRegion = tiledTextureRegion;
            this.row = row;
            this.col = col;
            this.startIndex = startIndex;
            this.stopIndex = stopIndex;
            this.animationArrayFrames = animationArrayFrames;
        }*/

        public BaseAnimationModel(String fileName, int col, int row, long pFrameDuration, int pFrameCount) {
            this.fileName = fileName;
            this.col = col;
            this.row = row;
            this.frameDuration = pFrameDuration;
            this.frameCount = pFrameCount;

        }


    }

    class BaseSpriteModel {
        @Getter
        @Setter
        String fileName;

        @Getter
        @Setter
        TextureRegion textureRegion;

        public BaseSpriteModel() {

        }

        public BaseSpriteModel(String fileName) {
            this.fileName = fileName;

        }

    }

}
// ===========================================================
// Methods
// ===========================================================

// ===========================================================
// Inner and Anonymous Classes
// ===========================================================


