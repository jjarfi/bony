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
public class MoDashboard {
      /* Methode Setter dan Getter untuk data pengarang, penerbit dan buku,
        hal ini dikarenakan setiap tabel antara penerbit dan pengarang berelasi ke tabel buku
        dengan relasi one to many
        methode ini digunakan hanya untuk mempermudah melakukan pencarian
     */
    private final StringProperty idbuku;
    private final StringProperty idpengarang;
    private final StringProperty idpenerbit;
    private final StringProperty judulbuku;
    private final StringProperty namapengarang;
    private final StringProperty penerbit;
    private final StringProperty jumlahbuku;
    private final StringProperty tahunterbit;
    private final StringProperty alamatpengarang;
    private final StringProperty alamatpenerbit;

    public MoDashboard(String idbuku, String idpengarang, String idpenerbit, String judulbuku, String namapengarang, String penerbit, String jumlahbuku, String tahunterbit, String alamatpengarang, String alamatpenerbit) {
        this.idbuku = new SimpleStringProperty (idbuku);
        this.idpengarang = new SimpleStringProperty (idpengarang);
        this.idpenerbit = new SimpleStringProperty (idpenerbit);
        this.judulbuku = new SimpleStringProperty (judulbuku);
        this.namapengarang = new SimpleStringProperty (namapengarang);
        this.penerbit = new SimpleStringProperty (penerbit);
        this.jumlahbuku = new SimpleStringProperty (jumlahbuku);
        this.tahunterbit = new SimpleStringProperty (tahunterbit);
        this.alamatpengarang = new SimpleStringProperty (alamatpengarang);
        this.alamatpenerbit = new SimpleStringProperty (alamatpenerbit);
        
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

    public final String getIdpengarang() {
        return idpengarang.get();
    }

    public final void setIdpengarang(String value) {
        idpengarang.set(value);
    }

    public StringProperty idpengarangProperty() {
        return idpengarang;
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

    public final String getJudulbuku() {
        return judulbuku.get();
    }

    public final void setJudulbuku(String value) {
        judulbuku.set(value);
    }

    public StringProperty judulbukuProperty() {
        return judulbuku;
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

    public final String getPenerbit() {
        return penerbit.get();
    }

    public final void setPenerbit(String value) {
        penerbit.set(value);
    }

    public StringProperty penerbitProperty() {
        return penerbit;
    }

    public final String getJumlahbuku() {
        return jumlahbuku.get();
    }

    public final void setJumlahbuku(String value) {
        jumlahbuku.set(value);
    }

    public StringProperty jumlahbukuProperty() {
        return jumlahbuku;
    }

    public final String getTahunterbit() {
        return tahunterbit.get();
    }

    public final void setTahunterbit(String value) {
        tahunterbit.set(value);
    }

    public StringProperty tahunterbitProperty() {
        return tahunterbit;
    }

    public final String getAlamatpengarang() {
        return alamatpengarang.get();
    }

    public final void setAlamatpengarang(String value) {
        alamatpengarang.set(value);
    }

    public StringProperty alamatpengarangProperty() {
        return alamatpengarang;
    }

    public final String getAlamatpenerbit() {
        return alamatpenerbit.get();
    }

    public final void setAlamatpenerbit(String value) {
        alamatpenerbit.set(value);
    }

    public StringProperty alamatpenerbitProperty() {
        return alamatpenerbit;
    }
    
}
