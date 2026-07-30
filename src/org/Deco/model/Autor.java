package org.Deco.model;

public class Autor {

    private int idAutor;
    private String nombreAutor;
    private String apellidoAutor;
    private String nacionalidad;
    private String biografia;

    public Autor() {
    }

    public Autor(int idAutor, String nombreAutor, String apellidoAutor,
                   String nacionalidad, String biografia) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidad = nacionalidad;
        this.biografia = biografia;
    }

    public Autor(int idAutor, String nombreAutor, String apellidoAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
    }

    public Autor(int idAutor, String nombreAutor, String apellidoAutor, String nacionalidad) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidad = nacionalidad;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    // Métodos para compatibilidad con el controlador
    public String getNombre() {
        return nombreAutor;
    }

    public void setNombre(String nombre) {
        this.nombreAutor = nombre;
    }

    public String getApellido() {
        return apellidoAutor;
    }

    public void setApellido(String apellido) {
        this.apellidoAutor = apellido;
    }

    @Override
    public String toString() {
        return "ID: " + idAutor +
               " | Nombre: " + nombreAutor + " " + apellidoAutor +
               " | Nacionalidad: " + nacionalidad;
    }

}