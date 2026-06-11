/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.alvarocalderon.model;


public class Editorial {
    private String nit;
    private String nombreEditorial;
    private String telefonoEditorial;
    private String direccionEditorial; // Mapea a 'direccion_editoria' en tu BD

    public Editorial() {}

    public Editorial(String nit, String nombreEditorial, String telefonoEditorial, String direccionEditorial) {
        this.nit = nit;
        this.nombreEditorial = nombreEditorial;
        this.telefonoEditorial = telefonoEditorial;
        this.direccionEditorial = direccionEditorial;
    }

    // TODO: Generar Getters y Setters en NetBeans (Alt + Insert)

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getTelefonoEditorial() {
        return telefonoEditorial;
    }

    public void setTelefonoEditorial(String telefonoEditorial) {
        this.telefonoEditorial = telefonoEditorial;
    }

    public String getDireccionEditorial() {
        return direccionEditorial;
    }

    public void setDireccionEditorial(String direccionEditorial) {
        this.direccionEditorial = direccionEditorial;
    }
    
}