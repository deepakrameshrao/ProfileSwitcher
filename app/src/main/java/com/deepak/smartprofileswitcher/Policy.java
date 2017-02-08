package com.deepak.smartprofileswitcher;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by deepakrao.r on 20-09-2015.
 */
public class Policy {
    int ringingVol;
    int mediaVol;

    public Policy(int ringVol, int medVol) {
        this.ringingVol = ringVol;
        this.mediaVol = medVol;
    }

    public void apply(Context ctx) {
        AudioManager audioManager = (AudioManager) ctx.getSystemService(ctx.AUDIO_SERVICE);
        int maxRingVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int maxMediaVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, (ringingVol * maxRingVol)/100, 0);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (mediaVol * maxMediaVol)/100, 0);
    }
}
