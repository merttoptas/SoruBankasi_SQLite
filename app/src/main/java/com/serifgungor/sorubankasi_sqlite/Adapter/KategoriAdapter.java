package com.serifgungor.sorubankasi_sqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.serifgungor.sorubankasi_sqlite.Model.Kategori;
import com.serifgungor.sorubankasi_sqlite.R;

import java.util.ArrayList;

public class KategoriAdapter extends BaseAdapter {

    private ArrayList<Kategori> kategoriler;
    private LayoutInflater layoutInflater;


    public KategoriAdapter() {
    }

    public KategoriAdapter(ArrayList<Kategori> kategoriler, Context context) {
        this.kategoriler = kategoriler;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return kategoriler.size();
    }

    @Override
    public Object getItem(int position) {
        return kategoriler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.kategori_satir_goruntusu,null);

        ImageView ivResim = v.findViewById(R.id.ivKategoriResim);
        TextView tvBaslik = v.findViewById(R.id.tvKategoriBaslik);

        int id = v.getResources().getIdentifier(
                kategoriler.get(position).getResim(),
                "drawable",
                v.getContext().getPackageName()
        );

        /*

        getIdentifier sayesinde dosya adını ve aranacak referans klasörü bildiğimiz
        bir nesnenin referans adresini döndürür.

        Aranacak değer klasör adı ve klasör içerisindeki dosya adı şeklinde
        ifade edilir, geri dönüş olarak referans id döner

        örneğin: projede drawable içerisinde logo.png yi aramak istiyorsak;

        int id = R.drawable.logo

         */


        ivResim.setImageResource(id);
        tvBaslik.setText(kategoriler.get(position).getBaslik());

        return v;
    }
}
