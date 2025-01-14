package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tippre")
public class TipoPrenda {
    @Id
    @NotNull
    @NotBlank    
    @Column(name = "codpre",unique = true)
    private String codpre;

    @NotNull
    @NotBlank
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
