package org.Deco.view;

import java.util.List;
import java.util.Scanner;
import org.Deco.model.Autor;

public class AutorConsoleView {

    private final Scanner leer = new Scanner(System.in);

    public int mostrarMenu() {
        int opcion = 0;
        System.out.println("--- GESTION DE AUTORES ---");
        System.out.println("-1 CREAR nuevo Autor ---");
        System.out.println("-2 LISTAR todos los Autores ---");
        System.out.println("-3 BUSCAR Autor por ID ---");
        System.out.println("-4 MODIFICAR Autor ---");
        System.out.println("-5 ELIMINAR Autor ---");
        System.out.println("-6 REGRESAR a menú PRINCIPAL ---");
        System.out.print("SELECCIONE UNA OPCION --> ");
        opcion = Integer.parseInt(leer.nextLine());
        return opcion;
    }

    public int solicitarIdAutor() {
        System.out.println("Ingrese el ID del autor: ");
        return Integer.parseInt(leer.nextLine());
    }

    public String solicitarNombreAutor() {
        System.out.println("Ingrese el NOMBRE del autor:");
        return leer.nextLine();
    }

    public String solicitarApellidoAutor() {
        System.out.println("Ingrese el APELLIDO del autor:");
        return leer.nextLine();
    }

    public String solicitarNacionalidad() {
        System.out.println("Ingrese la NACIONALIDAD del autor:");
        return leer.nextLine();
    }

    public String solicitarBiografia() {
        System.out.println("Ingrese la BIOGRAFÍA del autor:");
        return leer.nextLine();
    }

    public void mostrarAutor(Autor autor) {
        System.out.println("--- DATOS DEL AUTOR ---");
        System.out.println("ID: " + autor.getIdAutor());
        System.out.println("NOMBRE: " + autor.getNombreAutor());
        System.out.println("APELLIDO: " + autor.getApellidoAutor());
        System.out.println("NACIONALIDAD: " + autor.getNacionalidad());
        System.out.println("BIOGRAFÍA: " + autor.getBiografia());
        System.out.println("---\n");
    }

    public void mostrarListaAutores(List<Autor> autores) {
        System.out.println("--- LISTA DE AUTORES ---");
        System.out.printf("%-10s %-15s %-15s %-15s %-20s\n", "ID", "NOMBRE", "APELLIDO", "NACIONALIDAD", "BIOGRAFÍA");
        
        for (Autor autor : autores) {
            System.out.printf("%-10s %-15s %-15s %-15s %-20s\n",
                    autor.getIdAutor(), autor.getNombreAutor(), autor.getApellidoAutor(), autor.getNacionalidad(), autor.getBiografia());
        }
        System.out.println(" --- fin de autores ---\n");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarListaAutor(List<Autor> listarTodos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
