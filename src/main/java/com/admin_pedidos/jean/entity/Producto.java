package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pdtos")
public class Producto {
    @Id
    @Column(name="id_pdto")
    private int id_pdto;
    
    @Column(name = "ref")
    private String ref;

    @Column(name = "descref", nullable = false)
    private String descref;

    @Column(name = "cole", nullable = false)
    private String cole;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "linea", nullable = false)
    private String linea;

    @Column(name = "tipopr", nullable = false)
    private String tipopr;

    @Column(name = "ttalla", nullable = false)
    private String ttalla;

    @Column(name = "pcosto", nullable = false)
    private double pcosto;

    @Column(name = "iva", nullable = false)
    private double iva;

    @Column(name = "foto", nullable = false)
    private String foto;





    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDescref() {
        return descref;
    }

    public void setDescref(String descref) {
        this.descref = descref;
    }

    public String getCole() {
        return cole;
    }

    public void setCole(String cole) {
        this.cole = cole;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getTipopr() {
        return tipopr;
    }

    public void setTipopr(String tipopr) {
        this.tipopr = tipopr;
    }

    public String getTtalla() {
        return ttalla;
    }

    public void setTtalla(String ttalla) {
        this.ttalla = ttalla;
    }

    public double getPcosto() {
        return pcosto;
    }

    public void setPcosto(double pcosto) {
        this.pcosto = pcosto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId_pdto() {
        return id_pdto;
    }

    public void setId_pdto(int id_pdto) {
        this.id_pdto = id_pdto;
    }






    

}
