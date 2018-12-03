package com.serifgungor.sorubankasi_sqlite.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.serifgungor.sorubankasi_sqlite.Model.Kategori;
import com.serifgungor.sorubankasi_sqlite.Model.Konu;
import com.serifgungor.sorubankasi_sqlite.Model.Soru;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context myContext;
    private static String DB_NAME = "sorubankasi.db";
    private static String DB_PATH = "";
    private static int DATABASE_VERSION = 1;
    public SQLiteDatabase myDatabase;

    public Cursor getRows(String query) {
        myDatabase = this.getReadableDatabase();
        String Query = query;
        Cursor CR = myDatabase.rawQuery(Query, null);
        return CR;
    }

    public ArrayList<Kategori> getKategoriler() {
        ArrayList<Kategori> kategoriler = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("select * from Kategori", null);
            while (c.moveToNext()) {
                kategoriler.add(
                        new Kategori(
                                c.getInt(c.getColumnIndex("id")),
                                c.getString(c.getColumnIndex("baslik")),
                                c.getString(c.getColumnIndex("resim"))
                        )
                );
            }
        } catch (Exception e) {
        }
        return kategoriler;
    }

    public ArrayList<Konu> getKonular(int kategori_id) {
        ArrayList<Konu> konular = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("select * from Konu where kategori_id="+kategori_id, null);
            while (c.moveToNext()) {
                konular.add(
                        new Konu(
                                c.getInt(c.getColumnIndex("konu_id")),
                                c.getInt(c.getColumnIndex("kategori_id")),
                                c.getString(c.getColumnIndex("baslik")),
                                0
                        )
                );
            }
        } catch (Exception e) {
        }
        return konular;
    }

    public ArrayList<Soru> getSorular(int konu_id){
        ArrayList<Soru> sorular = new ArrayList<>();

        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("select * from Soru where konu_id="+konu_id,null);
            while (c.moveToNext()){
                sorular.add(
                        new Soru(
                                c.getInt(c.getColumnIndex("id")),
                                c.getInt(c.getColumnIndex("konu_id")),
                                c.getString(c.getColumnIndex("soru_baslik")),
                                c.getString(c.getColumnIndex("soru_yanit_a")),
                                c.getString(c.getColumnIndex("soru_yanit_b")),
                                c.getString(c.getColumnIndex("soru_yanit_c")),
                                c.getString(c.getColumnIndex("soru_yanit_d")),
                                c.getString(c.getColumnIndex("soru_yanit_e")),
                                c.getString(c.getColumnIndex("soru_yanit_harf")),
                                c.getInt(c.getColumnIndex("soru_puan"))
                        )
                );
            }

        }catch (Exception e){}

        return sorular;
    }

    public ArrayList<Soru> getRastgeleSorular(){
        ArrayList<Soru> sorular = new ArrayList<>();

        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("select * from Soru ORDER BY RANDOM() LIMIT 10",null);
            while (c.moveToNext()){
                sorular.add(
                        new Soru(
                                c.getInt(c.getColumnIndex("id")),
                                c.getInt(c.getColumnIndex("konu_id")),
                                c.getString(c.getColumnIndex("soru_baslik")),
                                c.getString(c.getColumnIndex("soru_yanit_a")),
                                c.getString(c.getColumnIndex("soru_yanit_b")),
                                c.getString(c.getColumnIndex("soru_yanit_c")),
                                c.getString(c.getColumnIndex("soru_yanit_d")),
                                c.getString(c.getColumnIndex("soru_yanit_e")),
                                c.getString(c.getColumnIndex("soru_yanit_harf")),
                                c.getInt(c.getColumnIndex("soru_puan"))
                        )
                );
            }

        }catch (Exception e){}

        return sorular;
    }


    private int getCount(String query) {
        myDatabase = this.getReadableDatabase();
        Cursor CR = myDatabase.rawQuery(query, null);
        return CR.getCount();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i > i1) {
            Log.v("Database Upgrade", "Database version higher than old.");
            deleteDatabase();
        }
    }

    public DatabaseHelper(Context context) throws IOException {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        boolean dbExists = checkDatabase();
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

        if (dbExists) {
            Log.d("DB_LOG", "Database bulundu !");
        } else {
            try {
                if (createDatabase() == true) {
                    Log.d("DB_LOG", "Database oluşturuldu !");
                } else {
                    Log.d("DB_LOG", "Database oluşturulamadı !");
                }
            } catch (Exception e) {
                Log.d("DB_LOG", "Database oluşturulamadı !");
            }
        }

    }

    public boolean createDatabase() throws IOException {
        boolean dbExists = checkDatabase();
        // checkDatabase metodu ile database varmı/yokmu kontrolü yap
        if (dbExists) { //database varsa
            return true;
        } else { // database yoksa
            this.getReadableDatabase();
            try {
                this.close();
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Database kopyalanma hatası");
            }
            return false;
        }
    }

    public boolean checkDatabase() {
        boolean checkdb = false;

        try {
            String dosyaKonumu = DB_PATH + DB_NAME;
            File dbFile = new File(dosyaKonumu);
            checkdb = dbFile.exists();
        } catch (SQLiteException e) {
            Log.d("DB_LOG", "Database bulunamadı");
        }

        return checkdb;
    }

    private void copyDatabase() throws IOException {
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    public void openDatabase() {
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        if (file.exists()) {
            file.delete();
            if (file.delete() == true) {
                Log.d("DB_LOG", "Database file deleted on apk in database file");
            } else {
                Log.d("DB_LOG", "Database file do not deleted !");
            }
        }
    }

    public synchronized void close() {
        if (myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

}