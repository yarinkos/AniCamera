package com.example.yarinkossover.snapapp.views;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.VideoView;

import com.example.yarinkossover.snapapp.media.CameraHelper;

import java.io.File;

/**
 * Created by yarin.kossover on 8/18/2016.
 */
public class VideoViewWrapper extends VideoView {

    public VideoViewWrapper(Context context) {
        super(context);
        File videoFile = CameraHelper.getOutputMediaFile(CameraHelper.MEDIA_TYPE_VIDEO);
        /*File mediaFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraSample" + File.separator +
                "VID3.mp4");
        */

        Uri vidUri = Uri.parse(videoFile.toString());
        this.setVideoURI(vidUri);
    }
}
