package com.admin_pedidos.jean.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pddos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pedido;
    
    

    @Column(name = "fechaped", nullable = false)
    private Date fechaped;

    @Column(name = "vddor", nullable = false)
    private String vddor;

    @Column(name = "col", nullable = false)
    private double col;

    @Column(name = "numped", nullable = false)
    private double numped;

    @Column(name = "clte", nullable = false)
    private String clte;

    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    @Column(name = "iva", nullable = false)
    private double iva;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "tprendas", nullable = false)
    private double tprendas;

    @Column(name = "obsgen", nullable = false)
    private String obsgen;

    @Column(name = "t1")
    private String t1;

    @Column(name = "t2")
    private String t2;

    @Column(name = "t3")
    private String t3;

    @Column(name = "t4")
    private String t4;

    @Column(name = "t5")
    private String t5;

    @Column(name = "t6")
    private String t6;

    @Column(name = "t7")
    private String t7;

    @Column(name = "t8")
    private String t8;

    @Column(name = "t9")
    private String t9;

    @Column(name = "t10")
    private String t10;





    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }

    public String getT5() {
        return t5;
    }

    public void setT5(String t5) {
        this.t5 = t5;
    }

    public String getT6() {
        return t6;
    }

    public void setT6(String t6) {
        this.t6 = t6;
    }

    public String getT7() {
        return t7;
    }

    public void setT7(String t7) {
        this.t7 = t7;
    }

    public String getT8() {
        return t8;
    }

    public void setT8(String t8) {
        this.t8 = t8;
    }

    public String getT9() {
        return t9;
    }

    public void setT9(String t9) {
        this.t9 = t9;
    }

    public String getT10() {
        return t10;
    }

    public void setT10(String t10) {
        this.t10 = t10;
    }

    public double getPrendref() {
        return prendref;
    }

    public void setPrendref(double prendref) {
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

    @Column(name = "prendref", nullable = false)
    private double prendref;

    @Column(name = "vuniref", nullable = false)
    private double vuniref;

    @Column(name = "obsref", nullable = false)
    private String obsref;

    @Column(name = "descref", nullable = false)
    private String descref;






    
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

    public double getCol() {
        return col;
    }

    public void setCol(double col) {
        this.col = col;
    }

    public double getNumped() {
        return numped;
    }

    public void setNumped(double numped) {
        this.numped = numped;
    }

    public String getClte() {
        return clte;
    }

    public void setClte(String clte) {
        this.clte = clte;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTprendas() {
        return tprendas;
    }

    public void setTprendas(double tprendas) {
        this.tprendas = tprendas;
    }

    public String getObsgen() {
        return obsgen;
    }

    public void setObsgen(String obsgen) {
        this.obsgen = obsgen;
    }

    
}
