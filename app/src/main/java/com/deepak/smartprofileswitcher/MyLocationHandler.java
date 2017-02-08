package com.deepak.smartprofileswitcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class MyLocationHandler extends BroadcastReceiver implements LocationListener{

    static MyLocationHandler myLocationHandler = null;
    HashMap<String, Policy> locationProfilerMap;

    public MyLocationHandler() {
        locationProfilerMap = new HashMap<String, Policy>();
    }

    public static MyLocationHandler getInstance(){
        if(myLocationHandler != null) {
            myLocationHandler = new MyLocationHandler();
        }
        return myLocationHandler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("MyLocationhandler", "onRecv. Proximity alert event.");
        if(intent != null && intent.getAction().equals(Profile.LOCATION_PROFILE_INTENT)) {
            String key = LocationManager.KEY_PROXIMITY_ENTERING;
            Boolean entering = intent.getBooleanExtra(key, false);
            String name = intent.getStringExtra("NAME");
            if (entering) {
                Log.d("MyLocationhandler", "onRecv. Proximity alert event. Entering");
                Profile profile = ProfileManager.getInstance().getProfileList().get(name);
                profile.mPolicy.apply(context);
                Log.d(getClass().getSimpleName(), "entering");
            } else {
                Log.d("MyLocationhandler", "onRecv. Proximity alert event. Exiting");
                AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_RING, 8, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);
                Log.d(getClass().getSimpleName(), "exiting");
            }

        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
