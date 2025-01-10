package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "dir")
public class Directorio {
    @Id
    @NotNull
    @NotBlank
    @Column(name = "nitdir")
    private String nitdir;

    @Column(name = "dcnitdir", nullable = false)
    private char dcnitdir;

    @NotNull
    @NotBlank
    @Column(name = "nomdir", nullable = false)
    private String nomdir;

    @NotNull
    @NotBlank
    @Column(name = "diredir", nullable = false)
    private String diredir;

    @NotNull
    @NotBlank
    @Column(name = "ciudir", nullable = false)
    private String ciudir;

    @NotNull
    @NotBlank
    @Column(name = "dptodir", nullable = false)
    private String dptodir;

    @NotNull
    @NotBlank
    @Column(name = "preteldir", nullable = false)
    private String preteldir;

    @NotNull
    @NotBlank
    @Column(name = "teldir", nullable = false)
    private String teldir;

    @NotNull
    @NotBlank
    @Column(name = "prefaxdir", nullable = false)
    private String prefaxdir;

    @NotNull
    @NotBlank
    @Column(name = "faxdir", nullable = false)
    private String faxdir;

    @NotNull
    @NotBlank
    @Column(name = "almacen", nullable = false)
    private String almacen;

    @NotNull
    @NotBlank
    @Column(name = "contacdir", nullable = false)
    private String contacdir;

    @NotNull
    @NotBlank
    @Column(name = "pretelcontacdir", nullable = false)
    private String pretelcontacdir;

    @NotNull
    @NotBlank
    @Column(name = "telcontacdir", nullable = false)
    private String telcontacdir;

    @NotNull
    @NotBlank
    @Column(name = "provee", nullable = false)
    private String provee;

    @NotNull
    @NotBlank
    @Column(name = "clte", nullable = false)
    private String clte;

    @NotNull
    @NotBlank
    @Column(name = "vddor", nullable = false)
    private String vddor;

    @NotNull
    @NotBlank
    @Column(name = "empl", nullable = false)
    private String empl;

    @NotNull
    @NotBlank
    @Column(name = "tercero", nullable = false)
    private String tercero;

    @NotNull
    @NotBlank
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

    

    public String getNomvddor() {
        return nomvddor;
    }

    public void setNomvddor(String nomvddor) {
        this.nomvddor = nomvddor;
    }


    

}
