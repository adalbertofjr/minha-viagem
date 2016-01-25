package com.adalbertofjr.minhaviagem.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 25/01/2016.
 */
public class ConfiguracoesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }


}
