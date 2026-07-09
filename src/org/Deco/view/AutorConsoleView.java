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
        System.out.println("--- GESTION DE CLIENTES ---");
        System.out.println("-1 CREAR nuevo cliente");
        System.out.println("-2 LISTAR nuevo cliente");
        System.out.println("-3 BUSCAR nuevo cliente");
        System.out.println("-4 MODIFICAR nuevo cliente");
        System.out.println("-5 ELIMINAR nuevo cliente");
        System.out.println("-6 REGRESAR nuevo cliente");
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
        System.out.println("ID: " + autor.getIdAutor());
        System.out.println("NOMBRE: " + autor.getNombreAutor());
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
}
