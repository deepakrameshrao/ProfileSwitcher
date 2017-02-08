package com.deepak.smartprofileswitcher;

import android.content.Context;

/**
 * Created by deepakrao.r on 19-09-2015.
 */
public abstract class Profile {

    String mName;
    Policy mPolicy;
    Context mContext;

    public static String LOCATION_PROFILE_INTENT = "com.deepak.smarptofileswitcher.LOCATION_DETECTED";

    public Policy getPolicy() {
        return mPolicy;
    }
}
