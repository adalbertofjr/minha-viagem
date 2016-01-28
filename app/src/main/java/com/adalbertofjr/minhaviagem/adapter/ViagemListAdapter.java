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
import com.adalbertofjr.minhaviagem.dominio.Viagem;

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

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lista_gastos, null);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return null;
    }

    static class ViewHolder{
        public ImageView imagem;
        public TextView destino;
        public TextView data;
        public TextView valor;
        public ProgressBar barraProgresso;

    }

}
