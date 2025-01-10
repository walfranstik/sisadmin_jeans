package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tipcole")
public class Coleccion {
    @Id
    @NotNull
    @NotBlank
    @Column(name = "codcole")
    private String codcole;

    @NotNull
    @NotBlank
    @Column(name = "descole", nullable = false)
    private String descole;




    
    public String getCodcole() {
        return codcole;
    }

    public void setCodcole(String codcole) {
        this.codcole = codcole;
    }

    public String getDescole() {
        return descole;
    }

    public void setDescole(String descole) {
        this.descole = descole;
    }

    
}
