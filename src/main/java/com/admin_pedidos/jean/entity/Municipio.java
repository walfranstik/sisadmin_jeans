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
@Table(name = "municipios")

public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_municipio;

    @NotNull
    @NotBlank
    @Column(name = "departamento")
    private String departamento;
    
    @NotNull
    @NotBlank
    @Column(name = "municipio")
    private String nombreMunicipio;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String municipio) {
        this.nombreMunicipio = municipio;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }
    
}
