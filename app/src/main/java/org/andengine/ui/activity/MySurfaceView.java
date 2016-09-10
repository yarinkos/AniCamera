package org.andengine.ui.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View.MeasureSpec;
import android.view.SurfaceView;
import android.hardware.Camera.PreviewCallback;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class MySurfaceView extends SurfaceView implements Callback /*Camera.PreviewCallback,*/ {

    private static final String TAG = "MySurfaceView";



    public SurfaceHolder mHolder;

    public Camera mCamera;

    Parameters parameters;

    int width;
    int height;


    public boolean flash = false;


    public MySurfaceView(final Context pContext , int width, int height, int width_d) {
        super(pContext);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //prepare the cashing for cartoon effect optimization
        this.width = width;
        this.height = height;

    }



    @SuppressLint("WrongCall")
    protected void onMeasure(final int pWidthMeasureSpec, final int pHeightMeasureSpec) {
    /*int parentWidth = MeasureSpec.getSize(pWidthMeasureSpec);
    int parentHeight = MeasureSpec.getSize(pHeightMeasureSpec);*/
        this.setMeasuredDimension(
                this.width, this.height);
        Log.e(TAG, "onMeasure: " + "parentWidth: " + Integer.toString(this.width) + " " + "parentHeight: " + Integer.toString(this.height));

    }

    public boolean frontCamera = false;

    public void surfaceCreated(SurfaceHolder holder) {
        synchronized (this) {
            openCamera();
        }

    }

    public void openCamera() {
        cameraCount = 0;
        if (frontCamera) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            cameraCount = Camera.getNumberOfCameras();
            for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
                Camera.getCameraInfo(camIdx, cameraInfo);
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
                    cameraCount = camIdx;
            }
        }
        this.mCamera = Camera.open(cameraCount);
        setParameters();

        try {
            this.mCamera.setPreviewDisplay(mHolder);
        } catch (final IOException e) {
            Debug.e(e);
        }
    }


    @Override
    public void surfaceDestroyed(final SurfaceHolder pSurfaceHolder) {
        this.mCamera.stopPreview();
        this.mCamera.setPreviewCallback(null);
        this.mCamera.release();
        this.mCamera = null;

    }

    @Override
    public void surfaceChanged(final SurfaceHolder pSurfaceHolder, final int pPixelFormat, final int pWidth, final int pHeight) {

    }

    int cameraCount;

    public void setParameters() {
        parameters = this.mCamera.getParameters();
        Log.e(TAG, "setParameters() ");


        setPreviewSize(this.width, this.height);//thats the real surface size pixels.it can be previewed bigger with setMeasuredDimension-but the pixels size determined here.

        parameters.setPreviewFormat(ImageFormat.NV21);
        Log.e(TAG, "parameters.getPreviewSize(): " + Integer.toString(parameters.getPreviewSize().width) + " " + Integer.toString(parameters.getPreviewSize().height));
        this.mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(0);
        this.mCamera.startPreview();
    /*this.mCamera.autoFocus(new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
        }
    });*/

    }

    public void setFlash() {

        this.flash = !this.flash;
        if (this.flash) {
            parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
        } else {
            parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
        }
        this.mCamera.setParameters(parameters);
    }

    public void setPreviewSize(int width, int height) {
        parameters.setPreviewSize(width, height);
        this.mCamera.setParameters(parameters);
        Log.e(TAG, "setPreviewSize" + Integer.toString(parameters.getPreviewSize().width) + " " + Integer.toString(parameters.getPreviewSize().height));

    }


    public Camera getCamera() {
        return this.mCamera;
    }
 
/*-------------------------------------------------------------------------*/

}