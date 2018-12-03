package com.serifgungor.sorubankasi_sqlite.Model;

public class Konu {
    private int konuId;
    private int kategoriId;
    private String baslik;
    private int soruSayisi;

    public Konu() {
    }

    public Konu(int konuId, int kategoriId, String baslik, int soruSayisi) {
        this.konuId = konuId;
        this.kategoriId = kategoriId;
        this.baslik = baslik;
        this.soruSayisi = soruSayisi;
    }

    public int getKonuId() {
        return konuId;
    }

    public void setKonuId(int konuId) {
        this.konuId = konuId;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public int getSoruSayisi() {
        return soruSayisi;
    }

    public void setSoruSayisi(int soruSayisi) {
        this.soruSayisi = soruSayisi;
    }
}
