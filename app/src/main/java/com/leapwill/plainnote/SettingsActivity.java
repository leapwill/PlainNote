package com.leapwill.plainnote;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

public class SettingsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, SettingsFragment.newInstance())
                .commit();
    }
}
