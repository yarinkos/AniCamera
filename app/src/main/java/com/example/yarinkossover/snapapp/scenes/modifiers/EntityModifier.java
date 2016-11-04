package com.example.yarinkossover.snapapp.scenes.modifiers;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.LoopModifier;

import lombok.Getter;

/**
 * Created by Yarin.kossover on 10/30/2016.
 */
public class EntityModifier extends LoopEntityModifier {


    public EntityModifier() {
        super( new IEntityModifier.IEntityModifierListener() {
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
                                new ScaleModifier(0.5f, 0.9f, 0.7f), new RotationModifier(1f, 15, -15))));

    }


    @Getter
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
            );

}
