package com.example.yarinkossover.snapapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yarinkossover.snapapp.model.Post;
import com.example.yarinkossover.snapapp.model.VideoPost;
import com.example.yarinkossover.snapapp.scenes.BaseSceneActivity;
//import com.example.yarinkossover.snapapp.scenes.FaceAddSceneActivity;
import com.example.yarinkossover.snapapp.utils.Utils;
import com.example.yarinkossover.snapapp.views.SimpleDrawingView;
import com.example.yarinkossover.snapapp.views.smallviews.Circle;
import com.example.yarinkossover.snapapp.views.smallviews.CircleAngleAnimation;


/**
 * Created by yarin.kossover on 8/18/2016.
 */
public class GuiActivity extends BaseSceneActivity {

    private String TAG = this.getClass().getSimpleName();
    private boolean DEBUG = true;

    private Button reconfigScene, painter;
    private FloatingActionButton captureButton;
    StateManager stateManager;

    View controllerView;
    SimpleDrawingView simpleDrawingView;
    LayoutInflater inflater;
    Circle circle;
    CircleAngleAnimation animation;

    Boolean displayPostsOfOthers = false;

    public GuiActivity() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        //  v = inflater.inflate(R.layout.main_activity, null);
        simpleDrawingView = new SimpleDrawingView(getActivity(), null);
        controllerView = inflater.inflate(R.layout.controllers_view, null);
        // controllerView.setLayoutParams(Utils.createSurfaceViewLayoutParams());
        ((ViewGroup) view).addView(simpleDrawingView, Utils.createSurfaceViewLayoutParams());
        ((ViewGroup) view).addView(controllerView, Utils.createSurfaceViewLayoutParams());

        circle = (Circle) controllerView.findViewById(R.id.record_button);
        //circle.setRect(300);
        animation = new CircleAngleAnimation(circle, 360);
        animation.setDuration(10000);


        //captureButton = (FloatingActionButton) controllerView.findViewById(R.id.record_button);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecordingVideo();
            }
        };
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnCaptureImage();
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
        reconfigScene = (Button) controllerView.findViewById(R.id.reconfig_scene);

        reconfigScene.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 reConfigScene();
                                             }
                                         }

        );
        painter = (Button) controllerView.findViewById(R.id.painter);
        painter.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           //     myViewPager.bringToFront();
                                           //  controllerView.setVisibility(View.INVISIBLE);
                                       }
                                   }

        );
        stateManager = new StateManager();
        stateManager.initState();
        // startAndEngine();


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
        displayPostsOfOthers = true;
        setDemoPost();
        stateManager.showVideo();
        stateManager.startAnimation();
    }

    private void reConfigScene() {
        this.doSome();
    }


    public void OnCaptureImage(){
        Log.d(TAG,"CAPTURE IMAGE" );
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
            mPreview.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            videoView.stopPlayback();
        }

        @Override
        public void startRecordingVideo() {
            circle.startAnimation(animation);
            new MediaPrepareTask().execute(null, null, null);
            videoView.setVisibility(View.GONE);
        }

        @Override
        public void stopRecordingVideo() {
            animation.cancel();
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
