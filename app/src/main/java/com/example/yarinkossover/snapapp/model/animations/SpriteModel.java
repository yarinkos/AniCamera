package com.example.yarinkossover.snapapp.model.animations;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Yarin.kossover on 10/28/2016.
 */
public class SpriteModel {

    @Getter
    @Setter
    String spriteName;
    @Getter
    @Setter
    String[] modifiers;

    public SpriteModel(String pSpriteName,String... pModifiers){
        this.spriteName=pSpriteName;
        setModifiers(pModifiers);
    }
}
