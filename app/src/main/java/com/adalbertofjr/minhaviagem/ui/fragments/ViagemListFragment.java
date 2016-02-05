package com.adalbertofjr.minhaviagem.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.adapter.ViagemListAdapter;
import com.adalbertofjr.minhaviagem.dao.ViagemDAO;
import com.adalbertofjr.minhaviagem.dominio.Viagem;
import com.adalbertofjr.minhaviagem.ui.ViagemActivity;

import java.util.List;

/**
 * Created by AdalbertoF on 28/01/2016.
 */
public class ViagemListFragment extends Fragment implements View.OnClickListener,  AdapterView.OnItemClickListener {

    private ViagemListAdapter mViagemAdapter;
    private ListView mListViagens;
    private FloatingActionButton mFabViagem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_viagem, null);
        mListViagens = (ListView) layout.findViewById(R.id.listar_viagens);
        mFabViagem = (FloatingActionButton) layout.findViewById(R.id.fb_nova_viagem);

        mViagemAdapter = new ViagemListAdapter(getContext(), listarViagens());
        mListViagens.setAdapter(mViagemAdapter);
        mListViagens.setEmptyView(layout.findViewById(R.id.lista_vazia));

        mFabViagem.setOnClickListener(this);
        mListViagens.setOnItemClickListener(this);

        return layout;
    }

    private List<Viagem> listarViagens() {
        return new ViagemDAO(getContext()).listarViagens(null);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.fb_nova_viagem) {
            startActivity(new Intent(getActivity(), ViagemActivity.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ViagemActivity.class);
        intent.putExtra(ViagemActivity.VIAGEM_EXTRA, (int)id);
        startActivity(intent);
    }
}
