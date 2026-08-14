package org.Deco.model;

public class AutoresLibro {

    private int idAutorLibro;
    private int idAutor;
    private String isbn;

    public AutoresLibro() {
    }

    public AutoresLibro(int idAutorLibro, int idAutor, String isbn) {
        this.idAutorLibro = idAutorLibro;
        this.idAutor = idAutor;
        this.isbn = isbn;
    }

    public AutoresLibro(int idAutor, String isbn) {
        this.idAutor = idAutor;
        this.isbn = isbn;
    }

    public int getIdAutorLibro() {
        return idAutorLibro;
    }

    public void setIdAutorLibro(int idAutorLibro) {
        this.idAutorLibro = idAutorLibro;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "ID Relación: " + idAutorLibro +
               " | ID Autor: " + idAutor +
               " | ISBN: " + isbn;
    }
}