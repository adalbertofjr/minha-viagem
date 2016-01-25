package com.adalbertofjr.minhaviagem.ui.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 25/01/2016.
 */
public class ConfiguracoesFragment extends PreferenceFragment {
    public ConfiguracoesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
