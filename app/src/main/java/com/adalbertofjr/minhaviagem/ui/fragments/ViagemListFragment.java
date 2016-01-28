package com.adalbertofjr.minhaviagem.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.adapter.ViagemListAdapter;
import com.adalbertofjr.minhaviagem.dao.ViagemDAO;
import com.adalbertofjr.minhaviagem.dominio.Viagem;

import java.util.List;

/**
 * Created by AdalbertoF on 28/01/2016.
 */
public class ViagemListFragment extends Fragment {

    private ViagemListAdapter mViagemAdapter;
    private ListView mListViagens;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_viagem, null);
        mListViagens = (ListView) layout.findViewById(R.id.listar_viagens);

        mViagemAdapter = new ViagemListAdapter(getContext(), listarViagens());
        mListViagens.setAdapter(mViagemAdapter);
        mListViagens.setEmptyView(layout.findViewById(R.id.lista_vazia));


        return layout;
    }

    private List<Viagem> listarViagens() {
        return new ViagemDAO(getContext()).listarViagens(null);
    }
}
