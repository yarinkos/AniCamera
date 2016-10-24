/*
package com.example.yarinkossover.snapapp.scenes;

import java.io.IOException;


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
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.LoopEntityModifier.ILoopEntityModifierListener;
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


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;


*/
/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Ncolas Gramlich
 * @since 11:54:51 - 03.04.2010
 *//*

public class FaceAddSceneActivity extends SimpleBaseGameActivity {

    protected final String TAG = this.getClass().getSimpleName();


// ===========================================================
// Constants
// ===========================================================

    private static int CAMERA_WIDTH = 720;
    private static int CAMERA_HEIGHT = 1280;
    private static float WIDTH;
    private static float HEIGHT;
    private static float WIDTH_FULL_SCREEN;
    private static float HEIGHT_FULL_SCREEN;
    public static float TouchEventScaleX;
    public static float TouchEventScaleY;

    // ===========================================================
// Fields
// ===========================================================
    private BuildableBitmapTextureAtlas mBitmapTextureAtlas;

    private TiledTextureRegion mSantaDonkeyTextureRegion;
    private TiledTextureRegion mSantaWalkingTextureRegion;
    private TiledTextureRegion mSnowmanTextureRegion;
    private TiledTextureRegion mSantaSleighTextureRegion;
    private TextureRegion mCloudTextureRegion;
    private TextureRegion mTwitterTextureRegion;
    private TextureRegion mCharacterTextureRegion;
    private AnimatedSprite mSantaDonkey;
    private AnimatedSprite mSantaWalking;
    private AnimatedSprite mSnowman;
    private AnimatedSprite mSantaSleigh;

    private long[] mSantaDonkeyAnimtedArray = new long[]{2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000};
    private int mSantaDonkeyfirstTileRow = 0;
    private int mSantaDonkeylastTileRow = 7;

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


    Sprite Spritebutton;
    Sprite Spritebutton1, Spritebutton2, Spritebutton3, Spritebutton4, Spritebutton5, Spritebutton6,
            Spritebutton7, Spritebutton8, Spritebutton9, Spritebutton10, Spritebutton11, Spritebutton12,
            Spritebutton19, Spritebutton20, Spritebutton21, Spritebutton22, Spritebutton23, Spritebutton24;

    Button button1, button2, button3, button4, button5, button6;
    OnClickListener buttonListenerScene, buttonListenerTechnical;


    public void doSome() {
*/
/*
        mAnimatedPressTiledTextureRegion = mSantaDonkeyTextureRegion;
        mAnimatedPressAnimtedArray = mSantaDonkeyAnimtedArray;
        firstTileRow = mSantaDonkeyfirstTileRow;
        lastTileRow = mSantaDonkeylastTileRow;
*//*

        mAnimatedPressTiledTextureRegion = mSantaSleighTextureRegion;
        mAnimatedPressAnimtedArray = mSantaSleighAnimtedArray;
        firstTileRow = mSantaSleighfirstTileRow;
        lastTileRow = mSantaSleighlastTileRow;
        switchAnimatedSprite();
        mAnimatedPress.animate(mAnimatedPressAnimtedArray, firstTileRow, lastTileRow, true);
        //onStopStartAnimation(mAnimatedPress);

    }

   */
/* @Override
    public void onCreateTechnicalButtons() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        buttonListenerTechnical = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == button6) {
                    startStopRecording();
                }
                if (v == button5) {
                    surfaceView.setFlash();
                }
                if (v == button4) {

                }
                if (v == button3) {
                    FaceAddSceneActivity.this.mExplosionSound.play();
                    mAnimatedPress.setX(1500);
             *//*
*/
/*Log.e("ooo1",Integer.toString((int) face2.getX()));
             Log.e("ooo2",Integer.toString((int) getwidthBmp()));
     		Log.e("ooo3",Integer.toString(getStartX()));*//*
*/
/*
                }
                if (v == button2) {
                    setBubbleText();
                }
                if (v == button1) {
                    mAnimatedPress.animate(mAnimatedPressAnimtedArray, firstTileRow, lastTileRow, true);
                }
            }

            private void setBubbleText() {
                textNormal.setText(mStringArray[stringCount]);
                stringCount++;
                if (stringCount > 3) stringCount = 0;

            }
        };
        button6.setOnClickListener(buttonListenerTechnical);
        button5.setOnClickListener(buttonListenerTechnical);
        button4.setOnClickListener(buttonListenerTechnical);
        button3.setOnClickListener(buttonListenerTechnical);
        button2.setOnClickListener(buttonListenerTechnical);
        button1.setOnClickListener(buttonListenerTechnical);
    }
*//*


    Sprite twitterLogo;
    Intent menuIntent;


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
        int d = (android.os.Process.myTid());

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/band scene/");

        this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 4096, 4096, TextureOptions.BILINEAR);

        this.mSantaDonkeyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "setsmiley.png", 4, 2); //col/row
        this.mSantaWalkingTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "SantaWalking.png", 3, 2);
        this.mSnowmanTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "CoolCat.png", 4, 4);
        this.mSantaSleighTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "animal1.png", 4, 4);
        this.mCloudTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "bubble.png");
        this.mTwitterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "Feces.png");
        this.mCharacterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "messi4.png");

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


    float increaseScale = 0.1f;

    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());


        initializeWordsArray();

        scene = new Scene();


        scene.getBackground().setColor(Color.TRANSPARENT);

        twitterLogo = new Sprite(CAMERA_WIDTH - 3 * (CAMERA_WIDTH / 12), 11 * CAMERA_HEIGHT / 12, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mTwitterTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {

                }

                return true;
            }
        };
        twitterLogo.setAlpha(254.0f / 255.0f);
        scene.registerTouchArea(twitterLogo);
//scene.attachChild(twitterLogo);


//addSpriteButtonsModifiers();
//addSpriteButtonsPicker();	   
//addSpriteButtonsTechnical();


        mAnimatedPressTiledTextureRegion = mSantaDonkeyTextureRegion;
        mAnimatedPressAnimtedArray = mSantaDonkeyAnimtedArray;
        firstTileRow = mSantaDonkeyfirstTileRow;
        lastTileRow = mSantaDonkeylastTileRow;


        mAnimatedPress = new AnimatedSprite(CAMERA_WIDTH * 0.33f, CAMERA_HEIGHT * 0.33f, CAMERA_WIDTH / 4, CAMERA_HEIGHT / 4, this.mAnimatedPressTiledTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
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
        Sprite ground = new Sprite(mAnimatedPress.getWidth() * 1.5f, mAnimatedPress.getHeight() / 2, mAnimatedPress.getWidth(), mAnimatedPress.getHeight(), this.mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                //  this.setPosition(pSceneTouchEvent.getX() , pSceneTouchEvent.getY() );
                FaceAddSceneActivity.this.mExplosionSound.play();
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
*/
/*new ScaleModifier(2, 1, 0.5f),
new DelayModifier(0.5f),
new ParallelEntityModifier(
new ScaleModifier(3, 0.5f, 1.5f),
new RotationByModifier(3, 90)
),
new ParallelEntityModifier(
new ScaleModifier(3, 1.5f, 1),
new RotationModifier(3, 180, 0)
)*//*

                        )
                );


//face3.registerEntityModifier(entityModifier);


        return scene;
    }


    private void addSpriteButtonsModifiers() {
        Spritebutton1 = new Sprite(CAMERA_WIDTH / 12, 1 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {

                }
                return true;
            }
        };

        scene.registerTouchArea(Spritebutton1);
        scene.attachChild(Spritebutton1);

        Spritebutton2 = new Sprite(CAMERA_WIDTH / 12, 2 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    if (mAnimatedPress != null && scene != null) {
                        onStopStartAnimation(mAnimatedPress);
                    }
                }
                return true;
            }
        };

        scene.registerTouchArea(Spritebutton2);
        scene.attachChild(Spritebutton2);

        Spritebutton3 = new Sprite(CAMERA_WIDTH / 12, 3 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    if (mAnimatedPress != null && scene != null) {
                        mAnimatedPress.setFlippedHorizontal(!mAnimatedPress.isFlippedHorizontal());

                    }
                }
                return true;
            }
        };

        scene.registerTouchArea(Spritebutton3);
        scene.attachChild(Spritebutton3);

        Spritebutton4 = new Sprite(CAMERA_WIDTH / 12, 4 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    if (mAnimatedPress != null && scene != null) {

                        face2.setScaleX(face2.getScaleX() - 0.1f);
                        face2.setScaleY(face2.getScaleY() - 0.1f);
                    }
                }
                return true;
            }
        };

        scene.registerTouchArea(Spritebutton4);
        scene.attachChild(Spritebutton4);

        Spritebutton5 = new Sprite(CAMERA_WIDTH / 12, 5 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    if (mAnimatedPress != null && scene != null) {

                        face2.setScaleX(face2.getScaleX() + 0.1f);
                        face2.setScaleY(face2.getScaleY() + 0.1f);
                    }
                }

                return true;
            }
        };

        scene.registerTouchArea(Spritebutton5);
        scene.attachChild(Spritebutton5);

        Spritebutton6 = new Sprite(CAMERA_WIDTH / 12, 6 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    if (mAnimatedPress != null && scene != null) {
                        if (mAnimatedPress.isVisible()) mAnimatedPress.setVisible(false);
                        else {
                            mAnimatedPress.setVisible(true);
                        }
                    }


                    String s = "test";// editText.getText().toString();
                    textNormal.setText(s);

                }
                return true;
            }
        };

        scene.registerTouchArea(Spritebutton6);
        scene.attachChild(Spritebutton6);

    }


    private void addSpriteButtonsPicker() {
        Spritebutton7 = new Sprite(CAMERA_WIDTH - (CAMERA_WIDTH / 12), 1 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {

                    Spritebutton7.setAlpha(0.5f);
                    mAnimatedPressTiledTextureRegion = mSantaDonkeyTextureRegion;
                    mAnimatedPressAnimtedArray = mSantaDonkeyAnimtedArray;
                    firstTileRow = mSantaDonkeyfirstTileRow;
                    lastTileRow = mSantaDonkeylastTileRow;
                    switchAnimatedSprite();
                }
                return true;
            }
        };

        scene.registerTouchArea(Spritebutton7);
        scene.attachChild(Spritebutton7);

        Spritebutton8 = new Sprite(CAMERA_WIDTH - (CAMERA_WIDTH / 12), 2 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {

                    mAnimatedPressTiledTextureRegion = mSantaSleighTextureRegion;
                    mAnimatedPressAnimtedArray = mSantaSleighAnimtedArray;
                    firstTileRow = mSantaSleighfirstTileRow;
                    lastTileRow = mSantaSleighlastTileRow;
                    switchAnimatedSprite();

                }


                return true;
            }
        };

        scene.registerTouchArea(Spritebutton8);
        scene.attachChild(Spritebutton8);

        Spritebutton9 = new Sprite(CAMERA_WIDTH - (CAMERA_WIDTH / 12), 3 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    mAnimatedPressTiledTextureRegion = mSantaWalkingTextureRegion;
                    mAnimatedPressAnimtedArray = mSantaWalkingAnimtedArray;
                    firstTileRow = mSantaWalkingfirstTileRow;
                    lastTileRow = mSantaWalkinglastTileRow;
                    switchAnimatedSprite();


                }

                return true;
            }
        };

        scene.registerTouchArea(Spritebutton9);
        scene.attachChild(Spritebutton9);

        Spritebutton10 = new Sprite(CAMERA_WIDTH - (CAMERA_WIDTH / 12), 4 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    mAnimatedPressTiledTextureRegion = mSnowmanTextureRegion;
                    mAnimatedPressAnimtedArray = mSnowmanAnimtedArray;
                    firstTileRow = mSnowmanfirstTileRow;
                    lastTileRow = mSnowmanlastTileRow;
                    switchAnimatedSprite();
                }


                return true;
            }
        };

        scene.registerTouchArea(Spritebutton10);
        scene.attachChild(Spritebutton10);

        Spritebutton11 = new Sprite(CAMERA_WIDTH - (CAMERA_WIDTH / 12), 5 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {

                }


                return true;
            }
        };

        scene.registerTouchArea(Spritebutton11);
        scene.attachChild(Spritebutton11);

        Spritebutton12 = new Sprite(CAMERA_WIDTH - (CAMERA_WIDTH / 12), 6 * CAMERA_HEIGHT / 7, CAMERA_WIDTH / 7, CAMERA_HEIGHT / 7, mCloudTextureRegion, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {


                    String s = "test";// editText.getText().toString();
                    textNormal.setText(s);
                }

                return true;
            }
        };

        scene.registerTouchArea(Spritebutton12);
        scene.attachChild(Spritebutton12);
        // ButtonSprite m =new ButtonSprite(500,500,mSantaWalkingTextureRegion,mSantaWalkingTextureRegion,this.getVertexBufferObjectManager());


    }


    TiledTextureRegion mAnimatedPressTiledTextureRegion;
    long[] mAnimatedPressAnimtedArray;
    int firstTileRow;
    int lastTileRow;
    int dd = 0;

    private void switchAnimatedSprite() {

        float locationX = mAnimatedPress.getX();
        float locationY = mAnimatedPress.getY();
        float scaleX = mAnimatedPress.getScaleX();
        float scaleY = mAnimatedPress.getScaleY();

        mAnimatedPress.detachChild(face1);
        scene.unregisterTouchArea(mAnimatedPress);
        scene.detachChild(mAnimatedPress);
        mAnimatedPress.detachSelf();
        mAnimatedPress.dispose();
        mAnimatedPress = null;

        if (dd == 0) {
            mAnimatedPress = new AnimatedSprite(locationX, locationY, this.mAnimatedPressTiledTextureRegion, this.getVertexBufferObjectManager()) {
                @Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                    this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                    return true;
                }
            };
            mAnimatedPress.animate(mAnimatedPressAnimtedArray, firstTileRow, lastTileRow, true);

        }

        mAnimatedPress.setScaleX(scaleX);
        mAnimatedPress.setScaleY(scaleY);
        mAnimatedPress.setZIndex(-100);
        //mAnimatedPress.attachChild(face1);

        scene.registerTouchArea(mAnimatedPress);
        //	scene.attachChild(mAnimatedPress);
        scene.sortChildren(true);

    }

    static boolean mUpdateScale = true;

    static int startX = 0;

    public static int getStartX() {
        float startX1 = mAnimatedPress.getX();
        startX1 = startX1 * (TouchEventScaleX);
        startX = (int) startX1 - (getwidthBmp() / 2) - 30;
        return startX;

    }

    static int startY = 0;

    public static int getStartY() {
        float startY1 = mAnimatedPress.getY();
        startY1 = startY1 * (TouchEventScaleY);
        startY = (int) HEIGHT - (int) startY1 - getheightBmp() / 2 - 30;
        return startY;
    }

    static int mWidthBmp = 0;

    public static int getwidthBmp() {
        if (mUpdateScale) {
            mWidthBmp = (int) mAnimatedPress.getWidthScaled();
            mWidthBmp = (int) (mWidthBmp * (TouchEventScaleX)) + 360;
            //mUpdateScale=false;
        }
        return mWidthBmp;

    }

    static int mHeightBmp = 0;

    public static int getheightBmp() {
        if (mUpdateScale) {
            mHeightBmp = (int) mAnimatedPress.getHeightScaled();
            mHeightBmp = (int) (mHeightBmp * (TouchEventScaleY)) + 260;
            //mUpdateScale=false;
        }
        return mHeightBmp;
    }

}
// ===========================================================
// Methods
// ===========================================================

// ===========================================================
// Inner and Anonymous Classes
// ===========================================================


*/
