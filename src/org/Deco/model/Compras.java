package org.Deco.model;

import java.time.LocalDateTime;

public class Compras {
//    -- compras
//create table compras(
//	no_compra int primary key auto_increment,
//    fecha_compra timestamp default current_timestamp,
//    total_compra decimal(8,2),
//    cui_cliente bigint

    private int noCompra; 
    private LocalDateTime fechaCompra;  
    private float totalCompra; 
    private long cui; 

    public Compras() {
    }

    public Compras(int noCompra, LocalDateTime fechaCompra, float totalCompra, long cui) {
        this.noCompra = noCompra;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.cui = cui;
    }

    public long getCui() {
        return cui;
    }

    public void setCui(long cui) {
        this.cui = cui;
    }

    public int getNoCompra() {
        return noCompra;
    }

    public void setNoCompra(int noCompra) {
        this.noCompra = noCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(float totalCompra) {
        this.totalCompra = totalCompra;
    }
    
    
    
}
