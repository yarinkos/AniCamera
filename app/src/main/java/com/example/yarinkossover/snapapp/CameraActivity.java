/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.yarinkossover.snapapp;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;

import com.example.yarinkossover.snapapp.utils.Utils;
import com.example.yarinkossover.snapapp.views.CameraTextureView;


/**
 * This activity uses the camera/camcorder as the A/V source for the {@link MediaRecorder} API.
 * A {@link android.view.TextureView} is used as the camera preview which limits the code to API 14+. This
 * can be easily replaced with a {@link android.view.SurfaceView} to run on older devices.
 */
public class CameraActivity extends Activity {

    public CameraTextureView mPreview;
    private static final String TAG = "Recorder";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.sample_main);
        mPreview = new CameraTextureView(this);
        //TextureView) findViewById(R.id.surface_view);
        this.addContentView(mPreview, Utils.createSurfaceViewLayoutParams());
        mPreview.startCameraPreview();

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        // if we are using MediaRecorder, release it first
        mPreview.releaseMediaRecorder();
        // we should release the camera immediately on pause event
        //but we don't want to,as we want a quick back for user experience
        // releaseCamera();
    }


    public boolean startRecording() {
        if (mPreview.prepareVideoRecorder()) {
            // Camera is available and unlocked, MediaRecorder is prepared,
            // now you can start recording
            mPreview.startVideoRecorder();
        } else {
            // prepare didn't work, release the camera
            mPreview.releaseMediaRecorder();
            return false;
        }
        return true;
    }


}