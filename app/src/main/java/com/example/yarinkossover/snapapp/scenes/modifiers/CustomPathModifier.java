package com.example.yarinkossover.snapapp.scenes.modifiers;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.util.debug.Debug;
import org.andengine.util.modifier.ease.EaseSineInOut;

/**
 * Created by Yarin.kossover on 10/30/2016.
 */
public class CustomPathModifier extends PathModifier {


    public CustomPathModifier(final float pDuration,final Path pPath) {
        super(pDuration, pPath, null, new PathModifier.IPathModifierListener() {
                    @Override
                    public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {
                        Debug.d("onPathStarted");
                    }

                    @Override
                    public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity,
                                                      final int pWaypointIndex) {
                        Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
           /* switch (pWaypointIndex) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }*/
                    }

                    @Override
                    public void onPathWaypointFinished(final PathModifier pPathModifier,
                                                       final IEntity pEntity, final int pWaypointIndex) {
                        Debug.d("onPathWaypointFinished: " + pWaypointIndex);
                    }

                    @Override
                    public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
                        Debug.d("onPathFinished");
                    }
                }

                , EaseSineInOut.getInstance());
    }
}
