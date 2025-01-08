package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiplin")
public class Linea {
    @Id
    @Column(name = "codlin")
    private String codlin;

    @Column(name = "deslin", nullable = false)
    private String deslin;





    

    public String getCodlin() {
        return codlin;
    }

    public void setCodlin(String codlin) {
        this.codlin = codlin;
    }

    public String getDeslin() {
        return deslin;
    }

    public void setDeslin(String deslin) {
        this.deslin = deslin;
    }

    
}
