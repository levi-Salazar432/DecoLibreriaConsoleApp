package org.Deco.view;

import java.util.List;
import java.util.Scanner;
import org.Deco.model.Editorial;

public class EditorialConsoleView {
    private final Scanner leer = new Scanner(System.in);
    
    //metodo para mostrar las opciones de este menu
    public int mostrarMenu(){
        int opcion = 0;
        // todo el menu
        System.out.println("--- GESTION DE EDITORIALES ---");
        System.out.println("-1 CREAR nueva Editorial ---");
        System.out.println("-2 LISTAR todas las Editoriales ---");
        System.out.println("-3 BUSCAR Editoriales por nit");
        System.out.println("-4 MODIFICAR Editorial ---");
        System.out.println("-5 ELIMINAR nueva editorial");
        System.out.println("-6 Regresar a menu principal");
        System.out.println(" Selecciones una opcion ");
        opcion = Integer.parseInt(leer.nextLine());
        return opcion;
    }
    //nit editorial
    public String SolicitarNIT(){
            System.out.println("Ingrese el nit de la editorial: ");
            return leer.nextLine();
    }
    
    //nombre Editorial
    public String solicitarNombreEditorial(){
        System.out.println("ingrese el nombre de la editorial");
        
        return leer.nextLine();
    }
    
    //telefono Editorial
    public String solicitarTelefonoEditorial(){
        System.out.println("ingrese el numero de telefono de la editorial");
        return leer.nextLine();
    }
    
    //direccion editorial
    public String solicitarDireccionEditorial(){
        System.out.println("ingrese la direccion de la Editorial");
        return leer.nextLine(); 
    }
    
    //Mostrar el detalle de una editorial
    public void mostrarEditorial(Editorial editorial){
        System.out.println("___Datos editorial___");
        System.out.println("nit:" + editorial.getNit());
        System.out.println("nombre" + editorial.getNombreEditorial());
        System.out.println("numeroDeTelefono" + editorial.getTelefonoEditorial());
        System.out.println("direccionDeEditorial" + editorial.getDireccionEditorial());
        System.out.println("");
    }
    
    public void mostrarListaEditorial(List<Editorial> editoriales){
    System.out.println("--- LISTA DE EDITORIAL ---");
    System.out.println("|     NIT     |    Nombre       |       Telefono      |    Direccion      |");
    for (Editorial editorial : editoriales){
        System.out.printf("%-10s %-15s %-15s %-20s%n",
            editorial.getNit(),
            editorial.getNombreEditorial(),
            editorial.getTelefonoEditorial(),
            editorial.getDireccionEditorial());
    }
}
    
    //para mostrar mensaje personalizado
    public void mostrarMensaje(String mensaje){
        System.out.println("mensaje");
    }
}

