package com.example.yarinkossover.snapapp.scenes.modifiers;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.LoopModifier;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Yarin.kossover on 10/24/2016.
 */
public class ModifiersCreators {

    @Getter
    @Setter
    int cameraWidth; //px

    @Getter
    @Setter
    int cameraHeight; //py
    private static ModifiersCreators instance;

    protected ModifiersCreators() {

    }

    public static ModifiersCreators getInstance(int pCameraWidth, int pCameraHeight) {
        if (instance == null)
            instance = new ModifiersCreators(pCameraWidth, pCameraHeight);

        return instance;

    }

    PathModifier.Path path;
    @Getter
    public LoopEntityModifier pathModifier;

    public ModifiersCreators(int pCameraWidth, int pCameraHeight) {
        this.cameraWidth = pCameraWidth;
        this.cameraHeight = pCameraHeight;
        initPath(pCameraWidth, (pCameraHeight * 9) / 11);
        // initPathModifier();
    }

    public IEntityModifier getModifierById(int id) {
        IEntityModifier iEntityModifier;
        switch (id) {
            case 1:
                iEntityModifier = new EntityModifier();
                break;
            case 2:
                iEntityModifier = new LoopEntityModifier(new CustomPathModifier(6, path),5);
                break;
            default:
                iEntityModifier = new CustomPathModifier(6, path);
        }

        return iEntityModifier;
    }

   /* @Getter
    public LoopEntityModifier entityModifier =
            new LoopEntityModifier(
                    new IEntityModifier.IEntityModifierListener() {
                        @Override
                        public void onModifierStarted(final IModifier<IEntity> pModifier, final IEntity pItem) {

                        }

                        @Override
                        public void onModifierFinished(final IModifier<IEntity> pEntityModifier, final IEntity pEntity) {

                        }
                    },
                    LoopModifier.LOOP_CONTINUOUS,
                    new LoopEntityModifier.ILoopEntityModifierListener() {
                        @Override
                        public void onLoopStarted(final LoopModifier<IEntity> pLoopModifier, final int pLoop, final int pLoopCount) {
                        }

                        @Override
                        public void onLoopFinished(final LoopModifier<IEntity> pLoopModifier, final int pLoop, final int pLoopCount) {
                        }
                    },
                    new SequenceEntityModifier(
// new RotationModifier(1, 0, 90),
                            new ParallelEntityModifier(
                                    new ScaleModifier(0.5f, 0.7f, 0.9f), new RotationModifier(1f, -15, 15)),
                            new ParallelEntityModifier(
                                    new ScaleModifier(0.5f, 0.9f, 0.7f), new RotationModifier(1f, 15, -15)))
            );*/

    public void initPath(int pCameraWidth, int pCameraHeight) {
        path = new PathModifier.Path(5).to(pCameraWidth, (pCameraHeight * 5) / 4).to((pCameraWidth * 3) / 4, (pCameraHeight * 4) / 5).to((pCameraWidth * 2) / 4, pCameraHeight).to((pCameraWidth * 1) / 4, (pCameraHeight * 3) / 4).to(-(pCameraWidth * 5) / 6, pCameraHeight);//.to(cameraWidth-100, cameraHeight).to(cameraWidth-200, cameraHeight).to(cameraWidth-300, cameraHeight-100);
    }

    /* Add the proper animation when a waypoint of the path is passed. */
    public void initPathModifier() {

        pathModifier = new

                LoopEntityModifier(new CustomPathModifier(6, path));
    }

}


