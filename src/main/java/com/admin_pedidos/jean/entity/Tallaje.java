package com.admin_pedidos.jean.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tiptalla")
public class Tallaje {
    @Id
    @NotNull
    @NotBlank
    @Column(name = "codtall")
    private String codtall;


    @NotNull
    @NotBlank
    @Column(name = "destall")
    private String destall;

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






    

    public String getCodtall() {
        return codtall;
    }

    public void setCodtall(String codtall) {
        this.codtall = codtall;
    }

    public String getDestall() {
        return destall;
    }

    public void setDestall(String destall) {
        this.destall = destall;
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
    

}
