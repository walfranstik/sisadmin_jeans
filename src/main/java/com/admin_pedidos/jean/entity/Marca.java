package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipmar")
public class Marca {
    @Id
    @Column(name = "codmar")
    private String codmar;

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
