package com.example.yarinkossover.snapapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.yarinkossover.snapapp.model.ImagePost;
import com.example.yarinkossover.snapapp.model.Post;
import com.example.yarinkossover.snapapp.model.VideoPost;
import com.example.yarinkossover.snapapp.scenes.BaseSceneActivity;
//import com.example.yarinkossover.snapapp.scenes.FaceAddSceneActivity;
import com.example.yarinkossover.snapapp.utils.Utils;
import com.example.yarinkossover.snapapp.views.SimpleDrawingView;
import com.example.yarinkossover.snapapp.views.smallviews.Circle;
import com.example.yarinkossover.snapapp.views.smallviews.CircleAngleAnimation;
import com.example.yarinkossover.snapapp.views.smallviews.colorpicker.LineColorPicker;
import com.example.yarinkossover.snapapp.views.smallviews.colorpicker.OnColorChangedListener;


/**
 * Created by yarin.kossover on 8/18/2016.
 */
public class GuiActivity extends BaseSceneActivity {

    private String TAG = this.getClass().getSimpleName();
    private boolean DEBUG = true;

    /****
     * controller view buttons
     ***/
    private ImageButton reconfigScene;
    private ImageButton changeCamera, flashButton;

    Circle circle;
    CircleAngleAnimation animationCircle;
    Animation animScale;

    private FloatingActionButton captureButton;

    /****
     * edit view buttons
     ***/

    private ImageButton backToControllerViewButton, addTextButton , drawButton;

    StateManager stateManager;
    View controllerView, editModeView;
    LineColorPicker colorPicker;
    SimpleDrawingView simpleDrawingView;
    LayoutInflater inflater;

    AnimationSet animationSet;

    Boolean displayPostsOfOthers = false;

    public GuiActivity() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        //  v = inflater.inflate(R.layout.main_activity, null);
        simpleDrawingView = new SimpleDrawingView(getActivity(), null);
        simpleDrawingView.setVisibility(View.INVISIBLE);
        controllerView = inflater.inflate(R.layout.controllers_view, null);
        editModeView = inflater.inflate(R.layout.editmode_view, null);
        // controllerView.setLayoutParams(Utils.createSurfaceViewLayoutParams());
        backToControllerViewButton = (ImageButton) editModeView.findViewById(R.id.back);
        backToControllerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateManager.backToControllerView();
            }
        });
        addTextButton= (ImageButton) editModeView.findViewById(R.id.add_text);
        drawButton= (ImageButton) editModeView.findViewById(R.id.draw);
        colorPicker = (LineColorPicker) editModeView.findViewById(R.id.picker);

// set color palette
        colorPicker.setColors(getResources().getIntArray(R.array.colors_picker));

// set selected color [optional]
        colorPicker.setSelectedColor(Color.RED);

// set on change listener
        colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                Log.d(TAG, "Selected color " + Integer.toHexString(c));
                simpleDrawingView.setPaintColor(c);
            }
        });

        ((ViewGroup) view).addView(simpleDrawingView, Utils.createSurfaceViewLayoutParams());
        ((ViewGroup) view).addView(controllerView, Utils.createSurfaceViewLayoutParams());
        ((ViewGroup) view).addView(editModeView, Utils.createSurfaceViewLayoutParams());
        editModeView.setVisibility(View.INVISIBLE);
        circle = (Circle) controllerView.findViewById(R.id.record_button);
        //circle.setRect(300);
        attachAnimationForRecordButton();


        //captureButton = (FloatingActionButton) controllerView.findViewById(R.id.record_button);
        /*View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecordingVideo();
            }
        };*/
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnCaptureImage();
                stateManager.goToEditMode();
            }
        });
        circle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onRecordingVideo();
                return true;
            }
        });
        View.OnTouchListener recordTouchListener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View pView, MotionEvent pEvent) {
                // pView.onTouchEvent(pEvent);
                // We're only interested in when the button is released.
                if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (mPreview.isRecording())
                        onRecordingVideo();
                }
                return false;
            }
        };

        circle.setOnTouchListener(recordTouchListener);
        // circle.setOnClickListener(onClickListener);
        reconfigScene = (ImageButton) controllerView.findViewById(R.id.reconfig_scene);

        reconfigScene.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 reConfigScene();
                                             }
                                         }

        );
        changeCamera = (ImageButton) controllerView.findViewById(R.id.switch_camara);
        changeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreview.changeCameraFacing();
            }
        });


        flashButton = (ImageButton) controllerView.findViewById(R.id.flash);
        flashButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               mPreview.setFlash();
                                               //   mPreview.changeCameraFacing();
                                               //     myViewPager.bringToFront();
                                               //  controllerView.setVisibility(View.INVISIBLE);
                                           }
                                       }

        );
        stateManager = new StateManager();
        stateManager.initState();
        // startAndEngine();


    }

    public void startRecordAnimation() {
        circle.setDrawBigCircle(true);
        circle.startAnimation(animationSet);
    }

    public void attachAnimationForRecordButton() {

        animationCircle = new CircleAngleAnimation(circle, 360);
        animationCircle.setDuration(10000);
        animScale = AnimationUtils.loadAnimation(getActivity(), R.anim.scale);
        animScale.setDuration(50);

        //set new animationSet
        animationSet = new AnimationSet(false);
        animationSet.addAnimation(animationCircle);
        animationSet.addAnimation(animScale);

    }

    public void stopRecordAnimation() {
        Log.d(TAG, "Stop Animation for record button");

        animationSet.getAnimations().get(0).cancel();
        animationSet.getAnimations().get(1).cancel();
        circle.clearAnimation();
        circle.setDrawBigCircle(false);
        circle.requestLayout();
    }
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        controllerView = inflater.inflate(R.layout.main_activity, null);
        addContentView(controllerView, Utils.createSurfaceViewLayoutParams());
        captureButton = (FloatingActionButton) findViewById(R.id.record_button);
        reConfigScene = (Button) findViewById(R.id.reConfigScene);
        reConfigScene.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          reConfigScene();
                                      }
                                  }

        );
        stateManager = new StateManager();
        stateManager.initState();


    }*/


    public void setPostToPlay(Post post) {
        if (post instanceof VideoPost) videoView.setVideoURI(((VideoPost) post).getVideoURI());
        if (post instanceof ImagePost) videoView.setVideoURI(((ImagePost) post).getImageURI());
        displayPostsOfOthers = true;
        setDemoPost();
        stateManager.showVideo();
        stateManager.startAnimation();
    }

    private void reConfigScene() {
        this.doSome();
    }


    public void OnCaptureImage() {
        Log.d(TAG, "CAPTURE IMAGE");
    }

    /**
     * The capture button controls all user interaction. When recording, the button click
     * stops recording, releases {@link android.media.MediaRecorder} and {@link android.hardware.Camera}. When not recording,
     * it prepares the {@link android.media.MediaRecorder} and starts recording.
     */
    public void onRecordingVideo() {
        if (DEBUG) {
            Log.d(TAG, "videoView Sizes:" + Utils.printPairs(new Pair("w", videoView.getWidth()), new Pair("h", videoView.getHeight())));
            Log.d(TAG, "cameraView Sizes:" + Utils.printPairs(new Pair("w", mPreview.getWidth()), new Pair("h", mPreview.getHeight())));
        }
        if (mPreview.isRecording()) {
            Log.d(TAG, "Stop recording and start preview");
            stateManager.stopRecordingVideo();
        } else {
            // BEGIN_INCLUDE(prepare_start_media_recorder)
            Log.d(TAG, "Start recording ");
            stateManager.startRecordingVideo();

            // END_INCLUDE(prepare_start_media_recorder)
        }
    }

    /*public void setCaptureButtonText(String title) {
        captureButton.setText(title);
    }
*/
    class StateManager implements IState {

        @Override
        public void initState() {
            mPreview.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
        }

        @Override
        public void showCamera() {
            Log.d(TAG, "Show Camera");
            mPreview.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            videoView.stopPlayback();
        }

        @Override
        public void startRecordingVideo() {
            startRecordAnimation();
            new MediaPrepareTask().execute(null, null, null);
            videoView.setVisibility(View.GONE);
        }


        @Override
        public void stopRecordingVideo() {
            Log.d(TAG, "stopRecording");
            stopRecordAnimation();
            mPreview.stopRecord();
            // inform the user that recording has stopped
            //    setCaptureButtonText("Capture");
            // END_INCLUDE(stop_release_media_recorder)
            showVideo();
        }

        @Override
        public void imageCapture() {

        }

        @Override
        public void showImage() {

        }

        @Override
        public void showVideo() {
            mPreview.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            videoView.start();
        }

        @Override
        public void startAnimation() {
            doSome();
        }

        @Override
        public void goToEditMode() {
            editModeView.setVisibility(View.VISIBLE);
            controllerView.setVisibility(View.INVISIBLE);
            simpleDrawingView.setVisibility(View.VISIBLE);
        }

        @Override
        public void backToControllerView() {
            simpleDrawingView.setVisibility(View.INVISIBLE);
            simpleDrawingView.clearPaint();
            controllerView.setVisibility(View.VISIBLE);
            editModeView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Asynchronous task for preparing the {@link android.media.MediaRecorder} since it's a long blocking
     * operation.
     */
    class MediaPrepareTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            // initialize video camera
            return startRecording();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                getActivity().finish();
            }
            // inform the user that recording has started
            // setCaptureButtonText("Stop");

        }
    }
    //todo handle it on the activity level

    public void onBackPressed() {
        stateManager.showCamera();
    }


}
