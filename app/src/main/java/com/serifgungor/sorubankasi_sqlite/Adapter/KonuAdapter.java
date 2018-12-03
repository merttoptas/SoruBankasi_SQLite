package com.serifgungor.sorubankasi_sqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.serifgungor.sorubankasi_sqlite.Model.Konu;
import com.serifgungor.sorubankasi_sqlite.R;

import java.util.ArrayList;

public class KonuAdapter extends BaseAdapter {
    private ArrayList<Konu> konular;
    private LayoutInflater layoutInflater;

    public KonuAdapter(){}

    public KonuAdapter(ArrayList<Konu> konular, Context context){
        this.konular = konular;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return konular.size();
    }

    @Override
    public Object getItem(int position) {
        return konular.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.konu_satir_goruntusu,null);

        TextView konuBaslik = v.findViewById(R.id.tvKonuBaslik);
        TextView soruSayisi = v.findViewById(R.id.tvKonuSoruSayisi);

        konuBaslik.setText(""+konular.get(position).getBaslik());
        soruSayisi.setText(""+konular.get(position).getSoruSayisi());

        return v;
    }
}
