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
public class MoReport {
     /* Methode Setter dan Getter untuk data pengarang, penerbit dan buku,
        hal ini dikarenakan setiap tabel antara penerbit dan pengarang berelasi ke tabel buku
        dengan relasi one to many
        methode ini digunakan hanya untuk mempermudah melakukan cetak data laporan
     */
    private final StringProperty idpengarang;
    private final StringProperty namapengarang;
    private final StringProperty idpenerbit;
    private final StringProperty namapenerbit;
    private final StringProperty idbuku;
    private final StringProperty judulbuku;

    public MoReport(String idpengarang, String namapengarang, String idpenerbit, String namapenerbit, String idbuku, String judulbuku) {
        this.idpengarang = new SimpleStringProperty (idpengarang);
        this.namapengarang = new SimpleStringProperty (namapengarang);
        this.idpenerbit = new SimpleStringProperty (idpenerbit);
        this.namapenerbit = new SimpleStringProperty (namapenerbit);
        this.idbuku = new SimpleStringProperty (idbuku);
        this.judulbuku = new SimpleStringProperty (judulbuku);
    }

    public final String getIdpengarang() {
        return idpengarang.get();
    }

    public final void setIdpengarang(String value) {
        idpengarang.set(value);
    }

    public StringProperty idpengarangProperty() {
        return idpengarang;
    }

    public final String getNamapengarang() {
        return namapengarang.get();
    }

    public final void setNamapengarang(String value) {
        namapengarang.set(value);
    }

    public StringProperty namapengarangProperty() {
        return namapengarang;
    }

    public final String getIdpenerbit() {
        return idpenerbit.get();
    }

    public final void setIdpenerbit(String value) {
        idpenerbit.set(value);
    }

    public StringProperty idpenerbitProperty() {
        return idpenerbit;
    }

    public final String getNamapenerbit() {
        return namapenerbit.get();
    }

    public final void setNamapenerbit(String value) {
        namapenerbit.set(value);
    }

    public StringProperty namapenerbitProperty() {
        return namapenerbit;
    }

    public final String getIdbuku() {
        return idbuku.get();
    }

    public final void setIdbuku(String value) {
        idbuku.set(value);
    }

    public StringProperty idbukuProperty() {
        return idbuku;
    }

    public final String getJudulbuku() {
        return judulbuku.get();
    }

    public final void setJudulbuku(String value) {
        judulbuku.set(value);
    }

    public StringProperty judulbukuProperty() {
        return judulbuku;
    }
    
    
}
