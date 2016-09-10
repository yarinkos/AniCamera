package com.example.yarinkossover.snapapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by yarin.kossover on 8/12/2016.
 */
public class Utils {

    public static Pair<Integer, Integer> getScreenResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return new Pair(width, height);
    }

    public static double getScreenResolutionRatio(Context context) {
        return (double) getScreenResolution(context).first / getScreenResolution(context).second;
    }

    public static WindowManager.LayoutParams createSurfaceViewLayoutParams() {
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }


    public static String printPairs(Pair... pairs) {
        StringBuilder sb = new StringBuilder();
        for (Pair p : pairs) {
            sb.append(p.first).append(":").append(p.second);
        }
        return sb.toString();
    }
}
