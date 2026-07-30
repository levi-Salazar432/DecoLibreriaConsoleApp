package org.Deco.view;

import java.util.Scanner;
import java.util.List;
import org.Deco.model.Autor;

public class AutorConsoleView {

    private final Scanner leer = new Scanner(System.in);

    //metodo para mostrar las opciones de este menu
    public int mostrarMenu() {
        int opcion = 0;

        //todo el menu
        System.out.println("--- GESTION DE AUTORES ---");
        System.out.println("-1 CREAR nuevo Autor");
        System.out.println("-2 LISTAR los Autores");
        System.out.println("-3 BUSCAR un Autor");
        System.out.println("-4 MODIFICAR un Autor");
        System.out.println("-5 ELIMINAR un Autor");
        System.out.println("-6 REGRESAR AL MENU PRINCIPAL");
        System.out.println(" SLECCIONE UNA OPCION ---->");
        opcion = Integer.parseInt(leer.nextLine());

        return opcion;
    }

    public int idNombreAutor() {
        System.out.println("Ingrese el id de su Autor: ");
        return Integer.parseInt(leer.nextLine());
    }

    public String SolicitarNombreAutor() {
        System.out.println("Ingrese el nombre de su Autor: ");
        return leer.nextLine();
    }

    public void MostrarAutor(Autor autor) {
        System.out.println("--- DATOS DEL AUTOR ---");
        System.out.println("id_autor" + autor.getIdAutor());
        System.out.println("nombre_autor" + autor.getNombreAutor());
        System.out.println("apellido_autor" + autor.getApellidoAutor());
        System.out.println("nacionalidad" + autor.getNacionalidad());
        System.out.println("biografia" + autor.getBiografia());
    }

    public void mostrarListaAutores(List<Autor> autores) {
        System.out.println("--- LISTA DE AUTORES ---");

        for (Autor autor : autores) {
            System.out.printf("%-10s %-10s%n",
                    autor.getIdAutor(),
                    autor.getNombreAutor());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    
public void mostrarListaAutor(List<Autor> listarTodos) {
    System.out.println("--- LISTA DE AUTORES ---");

    for (Autor autor : listarTodos) {
        System.out.println("ID: " + autor.getIdAutor());
        System.out.println("Nombre: " + autor.getNombreAutor());
        System.out.println("Apellido: " + autor.getApellidoAutor());
        System.out.println("Nacionalidad: " + autor.getNacionalidad());
        System.out.println("Biografía: " + autor.getBiografia());
        System.out.println("------------------------");
    }
}


    
public int solicitarIdAutor() {
    System.out.print("Ingrese el ID del autor: ");
    return Integer.parseInt(leer.nextLine());
}


    
public void mostrarAutor(Autor autor) {
    System.out.println("--- DATOS DEL AUTOR ---");
    System.out.println("ID: " + autor.getIdAutor());
    System.out.println("Nombre: " + autor.getNombreAutor());
    System.out.println("Apellido: " + autor.getApellidoAutor());
    System.out.println("Nacionalidad: " + autor.getNacionalidad());
    System.out.println("Biografía: " + autor.getBiografia());
}

}
