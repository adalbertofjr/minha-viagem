package com.adalbertofjr.minhaviagem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.data.MinhaViagemContract;
import com.adalbertofjr.minhaviagem.dominio.Viagem;
import com.adalbertofjr.minhaviagem.util.Util;

import java.util.List;


/**
 * Created by AdalbertoF on 28/01/2016.
 */
public class ViagemListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Viagem> mViagens;

    public ViagemListAdapter(Context mContext, List<Viagem> mViagens) {
        this.mContext = mContext;
        this.mViagens = mViagens;
    }

    @Override
    public int getCount() {
        return mViagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mViagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mViagens.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lista_viagem, null);
            viewHolder = new ViewHolder();
            viewHolder.imagem = (ImageView) convertView.findViewById(R.id.tipo_viagem);
            viewHolder.destino = (TextView) convertView.findViewById(R.id.destino);
            viewHolder.data = (TextView) convertView.findViewById(R.id.data);
            viewHolder.valor = (TextView) convertView.findViewById(R.id.valor);
            viewHolder.barraProgresso = (ProgressBar) convertView.findViewById(R.id.barra_progresso);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Viagem viagem = mViagens.get(position);

        if (viagem.getTipoViagem() == MinhaViagemContract.ViagemEntry.VIAGEM_LAZER) {
            viewHolder.imagem.setImageResource(R.drawable.ic_lazer);
        } else {
            viewHolder.imagem.setImageResource(R.drawable.ic_negocios);
        }
        viewHolder.destino.setText(viagem.getDestino());
        String dataPeriodo = Util.dateToStringFormat(viagem.getDataChegada())
                + " a " + Util.dateToStringFormat(viagem.getDataPartida());
        viewHolder.data.setText(dataPeriodo);
        viewHolder.valor.setText(viagem.getOrcamento() + ""); //todo - gasto total

        return convertView;
    }

    static class ViewHolder {
        public ImageView imagem;
        public TextView destino;
        public TextView data;
        public TextView valor;
        public ProgressBar barraProgresso;

    }

}
