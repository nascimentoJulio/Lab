package com.magodev.lab.gingado.utils;

import java.util.Locale;

public class MusicFormatter {
    public static String FormattedMusicDuration(int duration) {
        int minutes = getMinutesFromMilliseconds(duration);
        int seconds = getSecondsFromMilliseconds(duration);
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    public static String FormattedMusicProgress(int progress) {
        int minutes = progress / 60;
        int seconds = progress % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    public static int getMinutesFromMilliseconds(int milliseconds) {
        return (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
    }

    public static int getSecondsFromMilliseconds(int milliseconds) {
        return ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
    }

    public static int getTotalSecondsFromMilliseconds(int milliseconds) {
        return milliseconds / 1000;
    }
}
