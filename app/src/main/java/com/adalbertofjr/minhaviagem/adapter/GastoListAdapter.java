package com.adalbertofjr.minhaviagem.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.dominio.Gasto;
import com.adalbertofjr.minhaviagem.util.Util;

import java.util.List;

/**
 * Created by AdalbertoF on 27/01/2016.
 */
public class GastoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Gasto> mGastos;

    public GastoListAdapter(Context mContext, List<Gasto> mGastos) {
        this.mContext = mContext;
        this.mGastos = mGastos;
    }

    @Override
    public int getCount() {
        return mGastos.size();
    }

    @Override
    public Object getItem(int position) {
        return mGastos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGastos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lista_gastos, null);
            viewHolder = new ViewHolder();
            viewHolder.data = (TextView) convertView.findViewById(R.id.data);
            viewHolder.categoria = convertView.findViewById(R.id.categoria);
            viewHolder.descricao = (TextView) convertView.findViewById(R.id.descricao);
            viewHolder.valor = (TextView) convertView.findViewById(R.id.valor);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Gasto gasto = mGastos.get(position);
        viewHolder.data.setText(Util.dateToStringFormat(gasto.getData()));
        int cor = mContext.getResources().getColor(R.color.categoria_alimentacao);
        viewHolder.categoria.setBackgroundColor(cor);
        viewHolder.descricao.setText(gasto.getDescricao());
        viewHolder.valor.setText(gasto.getValor().toString());


        return convertView;
    }

    public void remover(Gasto gasto){
        mGastos.remove(gasto);
    }


    static class ViewHolder{

        public TextView data;
        public View categoria;
        public TextView descricao;
        public TextView valor;
    }
}
