package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dir")
public class Directorio {
 @Id
    @Column(name = "nitdir")
    private String nitdir;

    @Column(name = "dcnitdir", nullable = false)
    private char dcnitdir;

    @Column(name = "nomdir", nullable = false)
    private String nomdir;

    @Column(name = "diredir", nullable = false)
    private String diredir;

    @Column(name = "ciudir", nullable = false)
    private String ciudir;

    @Column(name = "dptodir", nullable = false)
    private String dptodir;

    @Column(name = "preteldir", nullable = false)
    private String preteldir;

    @Column(name = "teldir", nullable = false)
    private String teldir;

    @Column(name = "prefaxdir", nullable = false)
    private String prefaxdir;

    @Column(name = "faxdir", nullable = false)
    private String faxdir;

    @Column(name = "almacen", nullable = false)
    private String almacen;

    @Column(name = "contacdir", nullable = false)
    private String contacdir;

    @Column(name = "pretelcontacdir", nullable = false)
    private String pretelcontacdir;

    @Column(name = "telcontacdir", nullable = false)
    private String telcontacdir;

    @Column(name = "provee", nullable = false)
    private String provee;

    @Column(name = "clte", nullable = false)
    private String clte;

    @Column(name = "vddor", nullable = false)
    private String vddor;

    @Column(name = "empl", nullable = false)
    private String empl;

    @Column(name = "tercero", nullable = false)
    private String tercero;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "foto", nullable = false)
    private String foto;

    @Column(name = "nomvddor", nullable = false)
    private String nomvddor;






    public String getNitdir() {
        return nitdir;
    }

    public void setNitdir(String nitdir) {
        this.nitdir = nitdir;
    }

    public char getDcnitdir() {
        return dcnitdir;
    }

    public void setDcnitdir(char dcnitdir) {
        this.dcnitdir = dcnitdir;
    }

    public String getNomdir() {
        return nomdir;
    }

    public void setNomdir(String nomdir) {
        this.nomdir = nomdir;
    }

    public String getDiredir() {
        return diredir;
    }

    public void setDiredir(String diredir) {
        this.diredir = diredir;
    }

    public String getCiudir() {
        return ciudir;
    }

    public void setCiudir(String ciudir) {
        this.ciudir = ciudir;
    }

    public String getDptodir() {
        return dptodir;
    }

    public void setDptodir(String dptodir) {
        this.dptodir = dptodir;
    }

    public String getPreteldir() {
        return preteldir;
    }

    public void setPreteldir(String preteldir) {
        this.preteldir = preteldir;
    }

    public String getTeldir() {
        return teldir;
    }

    public void setTeldir(String teldir) {
        this.teldir = teldir;
    }

    public String getPrefaxdir() {
        return prefaxdir;
    }

    public void setPrefaxdir(String prefaxdir) {
        this.prefaxdir = prefaxdir;
    }

    public String getFaxdir() {
        return faxdir;
    }

    public void setFaxdir(String faxdir) {
        this.faxdir = faxdir;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getContacdir() {
        return contacdir;
    }

    public void setContacdir(String contacdir) {
        this.contacdir = contacdir;
    }

    public String getPretelcontacdir() {
        return pretelcontacdir;
    }

    public void setPretelcontacdir(String pretelcontacdir) {
        this.pretelcontacdir = pretelcontacdir;
    }

    public String getTelcontacdir() {
        return telcontacdir;
    }

    public void setTelcontacdir(String telcontacdir) {
        this.telcontacdir = telcontacdir;
    }

    public String getProvee() {
        return provee;
    }

    public void setProvee(String provee) {
        this.provee = provee;
    }

    public String getClte() {
        return clte;
    }

    public void setClte(String clte) {
        this.clte = clte;
    }

    public String getVddor() {
        return vddor;
    }

    public void setVddor(String vddor) {
        this.vddor = vddor;
    }

    public String getEmpl() {
        return empl;
    }

    public void setEmpl(String empl) {
        this.empl = empl;
    }

    public String getTercero() {
        return tercero;
    }

    public void setTercero(String tercero) {
        this.tercero = tercero;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomvddor() {
        return nomvddor;
    }

    public void setNomvddor(String nomvddor) {
        this.nomvddor = nomvddor;
    }


    

}
