/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HellCat
 */
public class MoBuku {
/* Methode Setter dan Getter untuk data buku,
   methode ini berisi entitas dari buku yang diambil dari field2 pada databases
   khususnya pada table buku
*/
    private final StringProperty idbuk;
    private final StringProperty judulbuk;
    private final StringProperty idpen;
    private final StringProperty idpenga;
    private final StringProperty tahunbuk;
    private final StringProperty jumlahbuk;
    private final StringProperty tglbuk;

    public MoBuku(String idbuk, String judulbuk, String idpen, String idpenga, String tahunbuk, String jumlahbuk, String tglbuk) {
        this.idbuk = new SimpleStringProperty(idbuk);
        this.judulbuk = new SimpleStringProperty(judulbuk);
        this.idpen = new SimpleStringProperty(idpen);
        this.idpenga = new SimpleStringProperty(idpenga);
        this.tahunbuk = new SimpleStringProperty(tahunbuk);
        this.jumlahbuk = new SimpleStringProperty(jumlahbuk);
        this.tglbuk = new SimpleStringProperty(tglbuk);
    }

    public final String getIdbuk() {
        return idbuk.get();
    }

    public final void setIdbuk(String value) {
        idbuk.set(value);
    }

    public StringProperty idbukProperty() {
        return idbuk;
    }

    public final String getJudulbuk() {
        return judulbuk.get();
    }

    public final void setJudulbuk(String value) {
        judulbuk.set(value);
    }

    public StringProperty judulbukProperty() {
        return judulbuk;
    }

    public final String getIdpen() {
        return idpen.get();
    }

    public final void setIdpen(String value) {
        idpen.set(value);
    }

    public StringProperty idpenProperty() {
        return idpen;
    }

    public final String getIdpenga() {
        return idpenga.get();
    }

    public final void setIdpenga(String value) {
        idpenga.set(value);
    }

    public StringProperty idpengaProperty() {
        return idpenga;
    }

    public final String getTahunbuk() {
        return tahunbuk.get();
    }

    public final void setTahunbuk(String value) {
        tahunbuk.set(value);
    }

    public StringProperty tahunbukProperty() {
        return tahunbuk;
    }

    public final String getJumlahbuk() {
        return jumlahbuk.get();
    }

    public final void setJumlahbuk(String value) {
        jumlahbuk.set(value);
    }

    public StringProperty jumlahbukProperty() {
        return jumlahbuk;
    }

    public final String getTglbuk() {
        return tglbuk.get();
    }

    public final void setTglbuk(String value) {
        tglbuk.set(value);
    }

    public StringProperty tglbukProperty() {
        return tglbuk;
    }

}
