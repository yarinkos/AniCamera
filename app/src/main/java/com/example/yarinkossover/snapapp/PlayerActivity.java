package com.example.yarinkossover.snapapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.yarinkossover.snapapp.utils.Utils;
import com.example.yarinkossover.snapapp.views.CameraTextureView;
import com.example.yarinkossover.snapapp.views.VideoViewWrapper;


/**
 * Created by yarin.kossover on 8/13/2016.
 */
public class PlayerActivity extends CameraActivity {

    private String TAG = this.getClass().toString();
    protected VideoView videoView;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoView = new VideoViewWrapper(getActivity());
        ((ViewGroup) view).addView(videoView, Utils.createSurfaceViewLayoutParams());
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);

            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "video complete");

            }
        });
    }

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoView = new VideoViewWrapper(this);
        this.addContentView(videoView, Utils.createSurfaceViewLayoutParams());

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);

            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "video complete");

            }
        });
    }*/


}
