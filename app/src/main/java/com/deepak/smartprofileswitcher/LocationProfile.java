package com.deepak.smartprofileswitcher;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by deepakrao.r on 19-09-2015.
 */
public class LocationProfile extends Profile implements LocationListener{

    Location mLocation;
    LocationManager locMgr;

    LocationProfile(Context ctx, String name, int ringingVolume, int mediaVolume) {
        this.mPolicy = new Policy(ringingVolume, mediaVolume);
        this.mName = name;
        this.mContext = ctx;
        locMgr = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        locMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Locationprofile", "onLocationChanged");
        this.mLocation = location;
        Intent intent = new Intent(LOCATION_PROFILE_INTENT);
        intent.putExtra("NAME", mName);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        locMgr.removeUpdates(this);
        locMgr.addProximityAlert(location.getLatitude(), location.getLongitude(), 1000, -1, pi);
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
