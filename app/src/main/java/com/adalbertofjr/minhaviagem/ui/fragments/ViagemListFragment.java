package com.adalbertofjr.minhaviagem.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 28/01/2016.
 */
public class ViagemListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_viagem, null);






        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
