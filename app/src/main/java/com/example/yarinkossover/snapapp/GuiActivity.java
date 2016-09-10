package com.example.yarinkossover.snapapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.yarinkossover.snapapp.scenes.BaseSceneActivity;
import com.example.yarinkossover.snapapp.scenes.FaceAddSceneActivity;
import com.example.yarinkossover.snapapp.utils.Utils;


/**
 * Created by yarin.kossover on 8/18/2016.
 */
public class GuiActivity extends BaseSceneActivity {

    private String TAG = this.getClass().getSimpleName();
    private boolean DEBUG = true;

    private Button button;
    private FloatingActionButton captureButton;
    StateManager stateManager;

    View guiView;

    @Override
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


    }

    private void clickButton(){
        this.doSome();
    }

    /**
     * The capture button controls all user interaction. When recording, the button click
     * stops recording, releases {@link android.media.MediaRecorder} and {@link android.hardware.Camera}. When not recording,
     * it prepares the {@link android.media.MediaRecorder} and starts recording.
     *
     * @param view the view generating the event.
     */
    public void onCaptureClick(View view) {
        if (DEBUG) {
            Log.d(TAG, "videoView Sizes:" + Utils.printPairs(new Pair("w", videoView.getWidth()), new Pair("h", videoView.getHeight())));
            Log.d(TAG, "cameraView Sizes:" + Utils.printPairs(new Pair("w", mPreview.getWidth()), new Pair("h", mPreview.getHeight())));
        }
        if (mPreview.isRecording()) {
            stateManager.stopRecordingVideo();
        } else {
            // BEGIN_INCLUDE(prepare_start_media_recorder)
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
            mPreview.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
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
            videoView.start();
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
                GuiActivity.this.finish();
            }
            // inform the user that recording has started
           // setCaptureButtonText("Stop");

        }
    }

    @Override
    public void onBackPressed() {
        stateManager.showCamera();
    }
}
