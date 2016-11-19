package com.example.yarinkossover.snapapp.model;

import android.net.Uri;

import com.example.yarinkossover.snapapp.model.animations.PostAnimationData;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Yarin.kossover on 10/28/2016.
 */
public class ImagePost extends Post {


    @Getter
    @Setter
    Uri imageURI;

   /* @Getter
    @Setter
    Uri videoPath;*/

    @Getter
    @Setter
    long imageLength;

    @Getter
    @Setter
    PostAnimationData postAnimationData;
}
