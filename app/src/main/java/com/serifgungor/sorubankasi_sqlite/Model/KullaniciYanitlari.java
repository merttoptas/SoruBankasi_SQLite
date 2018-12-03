package com.serifgungor.sorubankasi_sqlite.Model;

public class KullaniciYanitlari {
    private int soru_id;
    private String soru_adi;
    private String soru_dogru_yanit_harf;
    private String soru_kullanici_yanit_harf;


    public KullaniciYanitlari() {
    }

    public KullaniciYanitlari(int soru_id, String soru_adi, String soru_dogru_yanit_harf, String soru_kullanici_yanit_harf) {
        this.soru_id = soru_id;
        this.soru_adi = soru_adi;
        this.soru_dogru_yanit_harf = soru_dogru_yanit_harf;
        this.soru_kullanici_yanit_harf = soru_kullanici_yanit_harf;
    }

    public int getSoru_id() {
        return soru_id;
    }

    public void setSoru_id(int soru_id) {
        this.soru_id = soru_id;
    }

    public String getSoru_adi() {
        return soru_adi;
    }

    public void setSoru_adi(String soru_adi) {
        this.soru_adi = soru_adi;
    }

    public String getSoru_dogru_yanit_harf() {
        return soru_dogru_yanit_harf;
    }

    public void setSoru_dogru_yanit_harf(String soru_dogru_yanit_harf) {
        this.soru_dogru_yanit_harf = soru_dogru_yanit_harf;
    }

    public String getSoru_kullanici_yanit_harf() {
        return soru_kullanici_yanit_harf;
    }

    public void setSoru_kullanici_yanit_harf(String soru_kullanici_yanit_harf) {
        this.soru_kullanici_yanit_harf = soru_kullanici_yanit_harf;
    }
}
