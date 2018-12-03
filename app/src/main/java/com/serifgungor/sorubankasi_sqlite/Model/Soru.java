package com.serifgungor.sorubankasi_sqlite.Model;

public class Soru {
    private int soruId;
    private int konuId;
    private String soruBasligi;
    private String soruYanitA;
    private String soruYanitB;
    private String soruYanitC;
    private String soruYanitD;
    private String soruYanitE;
    private String dogruYanitHarf;
    private int puan;

    public Soru() {
    }

    public Soru(int soruId, int konuId, String soruBasligi, String soruYanitA, String soruYanitB, String soruYanitC, String soruYanitD, String soruYanitE, String dogruYanitHarf, int puan) {
        this.soruId = soruId;
        this.konuId = konuId;
        this.soruBasligi = soruBasligi;
        this.soruYanitA = soruYanitA;
        this.soruYanitB = soruYanitB;
        this.soruYanitC = soruYanitC;
        this.soruYanitD = soruYanitD;
        this.soruYanitE = soruYanitE;
        this.dogruYanitHarf = dogruYanitHarf;
        this.puan = puan;
    }

    public int getSoruId() {
        return soruId;
    }

    public void setSoruId(int soruId) {
        this.soruId = soruId;
    }

    public int getKonuId() {
        return konuId;
    }

    public void setKonuId(int konuId) {
        this.konuId = konuId;
    }

    public String getSoruBasligi() {
        return soruBasligi;
    }

    public void setSoruBasligi(String soruBasligi) {
        this.soruBasligi = soruBasligi;
    }

    public String getSoruYanitA() {
        return soruYanitA;
    }

    public void setSoruYanitA(String soruYanitA) {
        this.soruYanitA = soruYanitA;
    }

    public String getSoruYanitB() {
        return soruYanitB;
    }

    public void setSoruYanitB(String soruYanitB) {
        this.soruYanitB = soruYanitB;
    }

    public String getSoruYanitC() {
        return soruYanitC;
    }

    public void setSoruYanitC(String soruYanitC) {
        this.soruYanitC = soruYanitC;
    }

    public String getSoruYanitD() {
        return soruYanitD;
    }

    public void setSoruYanitD(String soruYanitD) {
        this.soruYanitD = soruYanitD;
    }

    public String getSoruYanitE() {
        return soruYanitE;
    }

    public void setSoruYanitE(String soruYanitE) {
        this.soruYanitE = soruYanitE;
    }

    public String getDogruYanitHarf() {
        return dogruYanitHarf;
    }

    public void setDogruYanitHarf(String dogruYanitHarf) {
        this.dogruYanitHarf = dogruYanitHarf;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }
}
