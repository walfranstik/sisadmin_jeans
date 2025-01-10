package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pdtos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pdto;
    
    @NotNull
    @NotBlank
    @Column(name = "ref")
    private String ref;


    @NotNull
    @NotBlank
    @Column(name = "descref", nullable = false)
    private String descref;

    @NotNull
    @Column(name = "cole", nullable = false)
    private String cole;

    @NotNull
    @Column(name = "marca", nullable = false)
    private String marca;

    @NotNull
    @Column(name = "linea", nullable = false)
    private String linea;

    @NotNull
    @Column(name = "tipopr", nullable = false)
    private String tipopr;

    @NotNull
    @Column(name = "ttalla", nullable = false)
    private String ttalla;


    @NotNull
    @Column(name = "pcosto", nullable = false)
    private double pcosto;






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


    public long getId_pdto() {
        return id_pdto;
    }

    public void setId_pdto(long id_pdto) {
        this.id_pdto = id_pdto;
    }






    

}
