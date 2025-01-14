package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tipmar")
public class Marca {

    @Id
    @NotNull
    @NotBlank
    @Column(name = "codmar",unique = true)
    private String codmar;

    @NotNull
    @NotBlank
    @Column(name = "desmar", nullable = false)
    private String desmar;





    
    public String getCodmar() {
        return codmar;
    }

    public void setCodmar(String codmar) {
        this.codmar = codmar;
    }

    public String getDesmar() {
        return desmar;
    }

    public void setDesmar(String desmar) {
        this.desmar = desmar;
    }

    
}
