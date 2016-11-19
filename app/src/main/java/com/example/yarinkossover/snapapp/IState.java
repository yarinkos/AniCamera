package com.example.yarinkossover.snapapp;

/**
 * Created by Yarin.kossover on 8/20/2016.
 */
public interface IState {
    void initState();

    void showCamera();

    void startRecordingVideo();

    void stopRecordingVideo();

    void imageCapture();

    void showImage();

    void showVideo();

    void startAnimation();

    void goToEditMode();

    void backToControllerView();


}
