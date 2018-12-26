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
public class MoPenerbit {

    /* Methode Setter dan Getter untuk data Penerbit,
       methode ini berisi entitas dari penerbit yang diambil dari field2 pada databases
       khususnya pada table penerbit
     */

    private final StringProperty id;
    private final StringProperty nama;
    private final StringProperty alamat;
    private final StringProperty notelp;
    private final StringProperty email;

    public MoPenerbit(String id, String nama, String alamat, String notelp, String email) {
        this.id = new SimpleStringProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.notelp = new SimpleStringProperty(notelp);
        this.email = new SimpleStringProperty(email);
    }

    public final String getId() {
        return id.get();
    }

    public final void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }

    public final String getNama() {
        return nama.get();
    }

    public final void setNama(String value) {
        nama.set(value);
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public final String getAlamat() {
        return alamat.get();
    }

    public final void setAlamat(String value) {
        alamat.set(value);
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public final String getNotelp() {
        return notelp.get();
    }

    public final void setNotelp(String value) {
        notelp.set(value);
    }

    public StringProperty notelpProperty() {
        return notelp;
    }

    public final String getEmail() {
        return email.get();
    }

    public final void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

}
