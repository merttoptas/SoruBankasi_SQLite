package com.serifgungor.sorubankasi_sqlite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.serifgungor.sorubankasi_sqlite.Adapter.KonuAdapter;
import com.serifgungor.sorubankasi_sqlite.Helper.DatabaseHelper;
import com.serifgungor.sorubankasi_sqlite.Model.Konu;
import com.serifgungor.sorubankasi_sqlite.R;

import java.util.ArrayList;

public class DersKonulariActivity extends AppCompatActivity {

    ArrayList<Konu> konular;
    KonuAdapter konuAdapter;
    ListView listView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_konulari);

        int kategori_id = getIntent().getIntExtra("kategori_id",0);

        konular = new ArrayList<>();
        try{
            dbHelper = new DatabaseHelper(getApplicationContext());
            konular = dbHelper.getKonular(kategori_id);
        }catch (Exception e){

        }

        konuAdapter = new KonuAdapter(konular,getApplicationContext());
        listView = findViewById(R.id.listViewKonular);
        listView.setAdapter(konuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),SorularActivity.class);
                intent.putExtra("konu_id",konular.get(position).getKonuId());
                startActivity(intent);
            }
        });
    }
}
