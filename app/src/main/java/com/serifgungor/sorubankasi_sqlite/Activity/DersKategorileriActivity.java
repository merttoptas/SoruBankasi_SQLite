package com.serifgungor.sorubankasi_sqlite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.serifgungor.sorubankasi_sqlite.Adapter.KategoriAdapter;
import com.serifgungor.sorubankasi_sqlite.Helper.DatabaseHelper;
import com.serifgungor.sorubankasi_sqlite.Model.Kategori;
import com.serifgungor.sorubankasi_sqlite.R;

import java.util.ArrayList;

public class DersKategorileriActivity extends AppCompatActivity {


    GridView gridView;
    ArrayList<Kategori> kategoriler;
    KategoriAdapter adapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_kategorileri);

        try{
            databaseHelper = new DatabaseHelper(getApplicationContext());
            kategoriler = databaseHelper.getKategoriler();
        }catch (Exception e){

        }

        gridView = findViewById(R.id.gridViewKategoriler);
        adapter = new KategoriAdapter(kategoriler,getApplicationContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),DersKonulariActivity.class);
                intent.putExtra("kategori_id",kategoriler.get(position).getId());
                startActivity(intent);

            }
        });


    }
}
