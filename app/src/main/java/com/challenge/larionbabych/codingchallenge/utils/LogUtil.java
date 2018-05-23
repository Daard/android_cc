package com.challenge.larionbabych.codingchallenge.utils;

import android.support.annotation.NonNull;
import android.util.Log;

public class LogUtil {

    private final boolean show;

    public enum type {e, w, i, d}

    public LogUtil(boolean show) {
        this.show = show;
    }

    public void log(type t, @NonNull String tag, @NonNull String message) {
        if (show) {
            tag = "LogUtil:" + tag;
            switch (t) {
                case e:
                    Log.e(tag, message);
                    break;
                case w:
                    Log.w(tag, message);
                    break;
                case i:
                    Log.i(tag, message);
                    break;
                default:
                    Log.d(tag, message);
            }
        }
    }

    public void log(type t, @NonNull String tag, @NonNull String message, Exception exception) {
        if (show) {
            tag = "LogUtil:" + tag;
            switch (t) {
                case e:
                    Log.e(tag, message, exception);
                    break;
                case w:
                    Log.w(tag, message, exception);
                    break;
                case i:
                    Log.i(tag, message, exception);
                    break;
                default:
                    Log.d(tag, message, exception);
            }
        }
    }

}
