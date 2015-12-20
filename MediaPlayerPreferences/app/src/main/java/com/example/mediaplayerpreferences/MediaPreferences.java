package com.example.mediaplayerpreferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by bam on 10/15/2015.
 */
public class MediaPreferences extends PreferenceActivity {
    public static class MediaPreferencesFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            PreferenceManager.setDefaultValues(getActivity(), R.xml.media_preferences, false);
            addPreferencesFromResource(R.xml.media_preferences);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MediaPreferencesFragment())
                .commit();
    }
}
