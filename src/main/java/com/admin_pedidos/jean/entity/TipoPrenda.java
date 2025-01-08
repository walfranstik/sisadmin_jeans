package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tippre")
public class TipoPrenda {
    @Id
    @Column(name = "codpre")
    private String codpre;

    @Column(name = "despre", nullable = false)
    private String despre;



    

    public String getCodpre() {
        return codpre;
    }

    public void setCodpre(String codpre) {
        this.codpre = codpre;
    }

    public String getDespre() {
        return despre;
    }

    public void setDespre(String despre) {
        this.despre = despre;
    }

    
}
