package com.example.yarinkossover.snapapp.model;

import android.net.Uri;

import com.example.yarinkossover.snapapp.model.animations.PostAnimationData;

import java.net.URI;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Yarin.kossover on 10/28/2016.
 */
public class VideoPost extends Post {


    @Getter
    @Setter
    Uri videoURI;

   /* @Getter
    @Setter
    Uri videoPath;*/

    @Getter
    @Setter
    long videoLength;

    @Getter
    @Setter
    PostAnimationData postAnimationData;
}
