package com.example.yarinkossover.snapapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yarin.kossover on 8/18/2016.
 */
public class GuiActivity extends BaseSceneActivity {

    private String TAG = this.getClass().getSimpleName();
    private boolean DEBUG = true;

    private Button button, button1;
    private FloatingActionButton captureButton;
    StateManager stateManager;

    View guiView;
    SimpleDrawingView simpleDrawingView;
    LayoutInflater inflater;

    Boolean displayPostsOfOthers = false;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        //  v = inflater.inflate(R.layout.main_activity, null);
        simpleDrawingView = new SimpleDrawingView(getActivity(), null);
        guiView = inflater.inflate(R.layout.main_activity, null);
        // guiView.setLayoutParams(Utils.createSurfaceViewLayoutParams());
        ((ViewGroup) view).addView(simpleDrawingView, Utils.createSurfaceViewLayoutParams());
        ((ViewGroup) view).addView(guiView, Utils.createSurfaceViewLayoutParams());


        captureButton = (FloatingActionButton) guiView.findViewById(R.id.record_button);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCaptureClick();
            }
        });
        button = (Button) guiView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          clickButton();
                                      }
                                  }

        );
        button1 = (Button) guiView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           //     myViewPager.bringToFront();
                                           //  guiView.setVisibility(View.INVISIBLE);
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
        guiView = inflater.inflate(R.layout.main_activity, null);
        addContentView(guiView, Utils.createSurfaceViewLayoutParams());
        captureButton = (FloatingActionButton) findViewById(R.id.record_button);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          clickButton();
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

    private void clickButton() {
        this.doSome();
    }


    /**
     * The capture button controls all user interaction. When recording, the button click
     * stops recording, releases {@link android.media.MediaRecorder} and {@link android.hardware.Camera}. When not recording,
     * it prepares the {@link android.media.MediaRecorder} and starts recording.
     */
    public void onCaptureClick() {
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
            new MediaPrepareTask().execute(null, null, null);
            videoView.setVisibility(View.GONE);
        }

        @Override
        public void stopRecordingVideo() {
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
