package com.example.yarinkossover.snapapp.model.animations;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Yarin.kossover on 10/28/2016.
 */
public class PostAnimationData {
    @Getter
    @Setter
    SpriteModel[] sprites;

    @Getter
    @Setter
    AnimationSpriteModel[] animatedSprites;
/*
    @Getter
    @Setter
    String[] spritesModifiers;

    @Getter
    @Setter
    String[] animationSpritesModifiers;*/
}
