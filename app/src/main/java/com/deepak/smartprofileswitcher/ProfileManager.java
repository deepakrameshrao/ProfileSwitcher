package com.deepak.smartprofileswitcher;

import java.util.HashMap;

/**
 * Created by deepakrao.r on 20-09-2015.
 */
public class ProfileManager {
    HashMap<String, Profile> profileList;

    public static ProfileManager profileManager = null;


    private ProfileManager(){
        profileList = new HashMap<String, Profile>();
    }

    public static ProfileManager getInstance(){
        if(profileManager == null) {
            profileManager = new ProfileManager();
        }
        return profileManager;
    }

    public void add(String name, Profile profile) {
        profileList.put(name, profile);
    }

    public HashMap<String, Profile> getProfileList () {
        return profileList;
    }
}
