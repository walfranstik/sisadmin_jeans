package com.admin_pedidos.jean.entity;


import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pddos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pedido;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "fechaped")
    private Date fechaped;



    @NotNull
    @NotBlank
    @Column(name = "vddor", nullable = false)
    private String vddor;

    @NotNull
    @NotBlank
    @Column(name = "col", nullable = false)
    private String col;

    @NotNull
    @NotBlank
    @Column(name = "numped", nullable = false)
    private String numped;

    @NotNull
    @NotBlank
    @Column(name = "clte", nullable = false)
    private String clte;

    @NotNull
    @Column(name = "total", nullable = false)
    private double total;

    @NotNull
    @Column(name = "tprendas", nullable = false)
    private int tprendas;

    @Column(name = "obsgen")
    private String obsgen;

    @Column(name = "t1")
    private int t1;

    @Column(name = "t2")
    private int t2;

    @Column(name = "t3")
    private int t3;

    @Column(name = "t4")
    private int t4;

    @Column(name = "t5")
    private int t5;

    @Column(name = "t6")
    private int t6;

    @Column(name = "t7")
    private int t7;

    @Column(name = "t8")
    private int t8;

    @Column(name = "t9")
    private int t9;

    @Column(name = "t10")
    private int t10;


    @Column(name = "ref", nullable = false)
    private String ref;

    @Column(name = "prendref", nullable = false)
    private int prendref;

    @Column(name = "vuniref", nullable = false)
    private double vuniref;

    @Column(name = "obsref")
    private String obsref;

    @Column(name = "descref", nullable = false)
    private String descref;

    @Column(name = "reng", nullable = false)
    private int reng;






    public int getReng() {
        return reng;
    }

    public void setReng(int reng) {
        this.reng = reng;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getT4() {
        return t4;
    }

    public void setT4(int t4) {
        this.t4 = t4;
    }

    public int getT5() {
        return t5;
    }

    public void setT5(int t5) {
        this.t5 = t5;
    }

    public int getT6() {
        return t6;
    }

    public void setT6(int t6) {
        this.t6 = t6;
    }

    public int getT7() {
        return t7;
    }

    public void setT7(int t7) {
        this.t7 = t7;
    }

    public int getT8() {
        return t8;
    }

    public void setT8(int t8) {
        this.t8 = t8;
    }

    public int getT9() {
        return t9;
    }

    public void setT9(int t9) {
        this.t9 = t9;
    }

    public int getT10() {
        return t10;
    }

    public void setT10(int t10) {
        this.t10 = t10;
    }

    public int getPrendref() {
        return prendref;
    }

    public void setPrendref(int prendref) {
        this.prendref = prendref;
    }

    public double getVuniref() {
        return vuniref;
    }

    public void setVuniref(double vuniref) {
        this.vuniref = vuniref;
    }

    public String getObsref() {
        return obsref;
    }

    public void setObsref(String obsref) {
        this.obsref = obsref;
    }

    public String getDescref() {
        return descref;
    }

    public void setDescref(String descref) {
        this.descref = descref;
    }
          



    
    public Date getFechaped() {
        return fechaped;
    }



    public void setFechaped(Date fechaped) {
        this.fechaped = fechaped;
    }

    public String getVddor() {
        return vddor;
    }

    public void setVddor(String vddor) {
        this.vddor = vddor;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getNumped() {
        return numped;
    }

    public void setNumped(String numped) {
        this.numped = numped;
    }

    public String getClte() {
        return clte;
    }

    public void setClte(String clte) {
        this.clte = clte;
    }


    public double getTotal() {
        return total;
    }
   
    public String getTotalFormateado() {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(12); // Ajusta según la precisión que necesites
        return df.format(total);
    }  // Formatea el total a dos decimales.

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTprendas() {
        return tprendas;
    }

    public void setTprendas(int tprendas) {
        this.tprendas = tprendas;
    }

    public String getObsgen() {
        return obsgen;
    }

    public void setObsgen(String obsgen) {
        this.obsgen = obsgen;
    }

    
}
