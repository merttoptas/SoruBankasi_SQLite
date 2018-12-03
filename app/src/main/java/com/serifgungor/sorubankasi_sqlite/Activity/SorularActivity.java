package com.serifgungor.sorubankasi_sqlite.Activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.serifgungor.sorubankasi_sqlite.Helper.DatabaseHelper;
import com.serifgungor.sorubankasi_sqlite.Model.KullaniciYanitlari;
import com.serifgungor.sorubankasi_sqlite.Model.Soru;
import com.serifgungor.sorubankasi_sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class SorularActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    private class ListeleAdapter extends BaseAdapter {

        ArrayList<KullaniciYanitlari> yanitlar = new ArrayList<>();
        LayoutInflater layoutInflater;

        public ListeleAdapter() {
        }

        public ListeleAdapter(ArrayList<KullaniciYanitlari> yanitlar, Context context) {
            this.yanitlar = yanitlar;
            this.layoutInflater = (LayoutInflater)
                    context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return yanitlar.size();
        }

        @Override
        public Object getItem(int position) {
            return yanitlar.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = layoutInflater.inflate(R.layout.yanit_satir_goruntusu, null);

            TextView tvBaslik = v.findViewById(R.id.tvYanitBaslik);
            TextView tvDogru = v.findViewById(R.id.tvYanitDogru);
            TextView tvKullanici = v.findViewById(R.id.tvYanitKullanici);
            RelativeLayout relativeLayout = v.findViewById(R.id.relativeYanit);


            if (
                    yanitlar.get(position).getSoru_dogru_yanit_harf().equals(
                            yanitlar.get(position).getSoru_kullanici_yanit_harf()
                    )
                    ) {//doğruysa
                relativeLayout.setBackgroundColor(Color.parseColor("#5600ff33"));
            } else { //yanlışsa
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.yanlis));
            }

            tvBaslik.setText(yanitlar.get(position).getSoru_adi());
            tvDogru.setText(yanitlar.get(position).getSoru_dogru_yanit_harf());
            tvKullanici.setText(yanitlar.get(position).getSoru_kullanici_yanit_harf());

            return v;
        }
    }


    public void sonuclariGoster() {
        final Dialog dialog = new Dialog(SorularActivity.this);
        dialog.setContentView(R.layout.dialog_yanitlari_listele);

        TextView tvPuan = dialog.findViewById(R.id.tvPuan);
        ListView listViewYanitlar = dialog.findViewById(R.id.listViewYanitlar);
        listViewYanitlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getSoru(position+1);
                dialog.dismiss();

                btnOncekiSoru.setVisibility(View.GONE);
                btnSonrakiSoru.setVisibility(View.GONE);
                btnSonuclar.setVisibility(View.VISIBLE);
            }
        });

        int puan = 0;

        for (int i = 0; i < yanitlar.size(); i++) {
            if (
                    yanitlar.get(i).getSoru_dogru_yanit_harf().equals(
                            yanitlar.get(i).getSoru_kullanici_yanit_harf()
                    )
                    ) {
                puan = puan + 10;
            }
        }
        tvPuan.setText("" + puan);


        ListeleAdapter adapter = new ListeleAdapter(yanitlar, getApplicationContext());
        listViewYanitlar.setAdapter(adapter);

        dialog.show();

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rdYanitA:
                if (isChecked) {
                    secilenYanit = "A";
                }
                break;
            case R.id.rdYanitB:
                if (isChecked) {
                    secilenYanit = "B";
                }
                break;
            case R.id.rdYanitC:
                if (isChecked) {
                    secilenYanit = "C";
                }
                break;
            case R.id.rdYanitD:
                if (isChecked) {
                    secilenYanit = "D";
                }
                break;
            case R.id.rdYanitE:
                if (isChecked) {
                    secilenYanit = "E";
                }
                break;
            default:
                break;
        }

    }

    TextView tvBaslik;
    RadioButton rdYanitA, rdYanitB, rdYanitC, rdYanitD, rdYanitE;
    Button btnOncekiSoru, btnSonrakiSoru,btnSonuclar;
    ArrayList<KullaniciYanitlari> yanitlar = new ArrayList<>();


    DatabaseHelper databaseHelper;
    ArrayList<Soru> sorular = new ArrayList<>();
    int soru_index;
    String secilenYanit;

    public void getSoru(int soru_index) {
        rdYanitA.setText(sorular.get(soru_index).getSoruYanitA());
        rdYanitB.setText(sorular.get(soru_index).getSoruYanitB());
        rdYanitC.setText(sorular.get(soru_index).getSoruYanitC());
        rdYanitD.setText(sorular.get(soru_index).getSoruYanitD());
        rdYanitE.setText(sorular.get(soru_index).getSoruYanitE());
        tvBaslik.setText(sorular.get(soru_index).getSoruBasligi());

        if (soru_index == 0) {
            btnOncekiSoru.setVisibility(View.GONE);
        } else {
            btnOncekiSoru.setVisibility(View.VISIBLE);
        }

        if (soru_index == sorular.size() - 1) {
            //btnSonrakiSoru.setVisibility(View.GONE);
            btnSonrakiSoru.setText("Bitir");
        } else {
            btnSonrakiSoru.setText("Sonraki Soru");
            //btnSonrakiSoru.setVisibility(View.VISIBLE);
        }
    }

    public boolean yanitVarmi(int soruId) {
        boolean b = false;
        for (int i = 0; i < yanitlar.size(); i++) {
            if (yanitlar.get(i).getSoru_id() == soruId) {
                b = true;
            }
        }
        return b;
    }

    public void yanitiSil(int soruId) {
        for (int i = 0; i < yanitlar.size(); i++) {
            if (yanitlar.get(i).getSoru_id() == soruId) {
                yanitlar.remove(i);
            }
        }
    }

    public void yanitlariResetle(){
        rdYanitA.setChecked(false);
        rdYanitB.setChecked(false);
        rdYanitC.setChecked(false);
        rdYanitD.setChecked(false);
        rdYanitE.setChecked(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorular);

        int konu_id = getIntent().getIntExtra("konu_id", 0);
        soru_index = 0;

        try {
            databaseHelper = new DatabaseHelper(getApplicationContext());
            sorular = databaseHelper.getSorular(konu_id);
        } catch (Exception e) {

        }

        tvBaslik = findViewById(R.id.tvBaslik);
        btnOncekiSoru = findViewById(R.id.btnOncekiSoru);
        btnSonrakiSoru = findViewById(R.id.btnSonrakiSoru);
        btnSonuclar = findViewById(R.id.btnSonuclar);
        rdYanitA = findViewById(R.id.rdYanitA);
        rdYanitB = findViewById(R.id.rdYanitB);
        rdYanitC = findViewById(R.id.rdYanitC);
        rdYanitD = findViewById(R.id.rdYanitD);
        rdYanitE = findViewById(R.id.rdYanitE);

        btnSonuclar.setVisibility(View.INVISIBLE);
        btnSonuclar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonuclariGoster();
            }
        });

        rdYanitA.setOnCheckedChangeListener(this);
        rdYanitB.setOnCheckedChangeListener(this);
        rdYanitC.setOnCheckedChangeListener(this);
        rdYanitD.setOnCheckedChangeListener(this);
        rdYanitE.setOnCheckedChangeListener(this);


        getSoru(0);

        btnSonrakiSoru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soru_index < sorular.size() - 1) {
                    //BİTİR BUTONU TIKLAMA OLAYI GİBİ DAVRANSIN
                    soru_index++;
                    getSoru(soru_index);

                } else {
                    sonuclariGoster();
                }

                /*
                Eğer bu değer daha önce Yanıtlar Arraylistine ekli değil ise, ekle
                Daha önce ekli ise, önceki değeri sil, son verdiği yanıtı ekle
                 */

                if (yanitVarmi(sorular.get(soru_index).getSoruId())) { //soru varsa
                    yanitiSil(sorular.get(soru_index).getSoruId());
                    yanitlar.add(
                            new KullaniciYanitlari(
                                    sorular.get(soru_index).getSoruId(),
                                    sorular.get(soru_index).getSoruBasligi(),
                                    sorular.get(soru_index).getDogruYanitHarf(),
                                    secilenYanit
                            )
                    );
                } else { //soru yoksa
                    yanitlar.add(
                            new KullaniciYanitlari(
                                    sorular.get(soru_index).getSoruId(),
                                    sorular.get(soru_index).getSoruBasligi(),
                                    sorular.get(soru_index).getDogruYanitHarf(),
                                    secilenYanit
                            )
                    );
                }
                yanitlariResetle();


            }
        });

        btnOncekiSoru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soru_index--;
                getSoru(soru_index);
            }
        });


    }


}
