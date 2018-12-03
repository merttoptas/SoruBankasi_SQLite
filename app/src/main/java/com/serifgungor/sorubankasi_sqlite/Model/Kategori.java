package com.serifgungor.sorubankasi_sqlite.Model;

public class Kategori {
    private int id;
    private String baslik;
    private String resim;

    public Kategori() {
    }

    public Kategori(int id, String baslik, String resim) {
        this.id = id;
        this.baslik = baslik;
        this.resim = resim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
}
