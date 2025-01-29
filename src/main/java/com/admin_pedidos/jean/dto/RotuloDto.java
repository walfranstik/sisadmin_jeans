package com.admin_pedidos.jean.dto;

public class RotuloDto {
   
    private String nitdir;
    private String nomdir;
    private String diredir;
    private String ciudir;
    private String dptodir;
    private String preteldir;
    private String teldir;
    private String almacen;
    private int cantidad; // Variable numérica

    // Constructor sin argumentos
    public RotuloDto() {
    }

    // Constructor con argumentos
    public RotuloDto(String nitdir, String nomdir, String diredir, String ciudir, String dptodir, String preteldir, String teldir, String almacen, int cantidad) {
        this.nitdir = nitdir;
        this.nomdir = nomdir;
        this.diredir = diredir;
        this.ciudir = ciudir;
        this.dptodir = dptodir;
        this.preteldir = preteldir;
        this.teldir = teldir;
        this.almacen = almacen;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getNitdir() {
        return nitdir;
    }

    public void setNitdir(String nitdir) {
        this.nitdir = nitdir;
    }

    public String getNomdir() {
        return nomdir;
    }

    public void setNomdir(String nomdir) {
        this.nomdir = nomdir;
    }

    public String getDiredir() {
        return diredir;
    }

    public void setDiredir(String diredir) {
        this.diredir = diredir;
    }

    public String getCiudir() {
        return ciudir;
    }

    public void setCiudir(String ciudir) {
        this.ciudir = ciudir;
    }

    public String getDptodir() {
        return dptodir;
    }

    public void setDptodir(String dptodir) {
        this.dptodir = dptodir;
    }

    public String getPreteldir() {
        return preteldir;
    }

    public void setPreteldir(String preteldir) {
        this.preteldir = preteldir;
    }

    public String getTeldir() {
        return teldir;
    }

    public void setTeldir(String teldir) {
        this.teldir = teldir;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "RotuloDto{" +
                "nitdir='" + nitdir + '\'' +
                ", nomdir='" + nomdir + '\'' +
                ", diredir='" + diredir + '\'' +
                ", ciudir='" + ciudir + '\'' +
                ", dptodir='" + dptodir + '\'' +
                ", preteldir='" + preteldir + '\'' +
                ", teldir='" + teldir + '\'' +
                ", almacen='" + almacen + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
    
}
