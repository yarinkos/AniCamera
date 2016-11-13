package com.example.yarinkossover.snapapp.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Log;
import android.view.TextureView;


import com.example.yarinkossover.snapapp.media.CameraHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yarin.kossover on 8/13/2016.
 */
public class CameraTextureView extends TextureView {


    private Camera mCamera;
    private CamcorderProfile profile;
    private MediaRecorder mMediaRecorder;
    private File mOutputFile;

    private boolean isRecording = false;
    private static final String TAG = "Recorder";
    private CameraSurfaceTextureListener mCameraSurfaceTextureListener;

    private boolean flash = false;

    @Getter
    @Setter
    int currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;

    public CameraTextureView(Context context) {
        super(context);
        mCameraSurfaceTextureListener = new CameraSurfaceTextureListener();
        this.setSurfaceTextureListener(mCameraSurfaceTextureListener);
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecordState(boolean state) {
        this.isRecording = state;
    }


    public void changeCameraFacing() {
        if (isRecording()) return;
        if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
        } else {
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        releaseCamera();
        startCameraPreview(currentCameraId);
    }

    private void resetMediaRecorder() {
        if (mMediaRecorder != null) {
            // clear recorder configuration
            mMediaRecorder.reset();
            // release the recorder object
            // mMediaRecorder.release();
            // mMediaRecorder = null;
            // Lock camera for later use i.e taking it back from MediaRecorder.
            // MediaRecorder doesn't need it anymore and we will release it if the activity pauses.
            mCamera.lock();
        }
    }

    public void stopMediaRecording() {
        mMediaRecorder.stop();
    }

    public void stopRecord() {
        // BEGIN_INCLUDE(stop_release_media_recorder)

        // stop recording and release camera
        try {
            stopMediaRecording();  // stop the recording
        } catch (RuntimeException e) {
            // RuntimeException is thrown when stop() is called immediately after start().
            // In this case the output file is not properly constructed ans should be deleted.
            Log.d(TAG, "RuntimeException: stop() is called immediately after start()");
            //noinspection ResultOfMethodCallIgnored
            mOutputFile.delete();
        }
        resetMediaRecorder(); // release the MediaRecorder object
        mCamera.lock();         // take camera access back from MediaRecorder
        // releaseCamera();
        setRecordState(false);
    }

    public void deleteOutputFile() {
        mOutputFile.delete();
    }

    public void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            // clear recorder configuration
            mMediaRecorder.reset();
            // release the recorder object
            mMediaRecorder.release();
            mMediaRecorder = null;
            // Lock camera for later use i.e taking it back from MediaRecorder.
            // MediaRecorder doesn't need it anymore and we will release it if the activity pauses.
            mCamera.lock();
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            // release the camera for other applications
            mCamera.release();
            mCamera = null;
        }
    }

    public void setFlash() {
        if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT)
            return;
        Log.d(TAG, "set Flash");
        this.flash = !this.flash;
        Camera.Parameters parameters = mCamera.getParameters();
        if (this.flash) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        } else {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        }
        this.mCamera.setParameters(parameters);
    }

    //@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void startCameraPreview(int cameraId) {

        // BEGIN_INCLUDE (configure_preview)
//        mCamera = CameraHelper.getDefaultCameraInstance();
        mCamera = Camera.open(cameraId);

        // We need to make sure that our preview and recording video size are supported by the
        // camera. Query camera to find all the sizes and choose the optimal size given the
        // dimensions of our preview surface.
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> mSupportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> mSupportedVideoSizes = parameters.getSupportedVideoSizes();
        Camera.Size optimalSize = CameraHelper.getOptimalVideoSize(mSupportedVideoSizes,
                mSupportedPreviewSizes, this.getWidth(), this.getHeight());

        // Use the same size for recording profile.
        profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        profile.videoFrameWidth = optimalSize.width;
        profile.videoFrameHeight = optimalSize.height;

        // likewise for the camera object itself.
        parameters.setPreviewSize(profile.videoFrameWidth, profile.videoFrameHeight);
        mCamera.setParameters(parameters);
        try {
            // Requires API level 11+, For backward compatibility use {@link setPreviewDisplay}
            // with {@link SurfaceView}
            mCamera.setPreviewTexture(this.getSurfaceTexture());
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.e(TAG, "Surface texture is unavailable or unsuitable" + e.getMessage());
        }
        // END_INCLUDE (configure_preview)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean prepareVideoRecorder() {
        //startCameraPreview();
        // BEGIN_INCLUDE (configure_media_recorder)
        mMediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        // Step 2: Set sources
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mMediaRecorder.setProfile(profile);
        mMediaRecorder.setOrientationHint(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK ? 90 : 270);
        // Step 4: Set output file
        mOutputFile = CameraHelper.getOutputMediaFile(CameraHelper.MEDIA_TYPE_VIDEO);
        if (mOutputFile == null) {
            return false;
        }
        mMediaRecorder.setOutputFile(mOutputFile.getPath());
        // END_INCLUDE (configure_media_recorder)

        // Step 5: Prepare configured MediaRecorder
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    public void startVideoRecorder() {
        mMediaRecorder.start();

        setRecordState(true);
    }

    class CameraSurfaceTextureListener implements
            SurfaceTextureListener {

        public CameraSurfaceTextureListener() {

        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface,
                                                int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            if (mCamera != null) {
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
            return true;
        }

        @Override
        public void onSurfaceTextureAvailable(
                SurfaceTexture surface,
                int width, int height) {
            Log.d("!!!!", "onSurfaceTextureAvailable!!!");
       /* Pair<CameraInfo, Integer> backCamera = getBackCamera();
        final int backCameraId = backCamera.second;
        mBackCameraInfo = backCamera.first;
        mCamera = Camera.open(backCameraId);
        cameraDisplayRotation();*/
            startCameraPreview(currentCameraId);
      /*  try {
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
        } catch (IOException ioe) {
            // Something bad happened
        }*/
        }
    }
}
