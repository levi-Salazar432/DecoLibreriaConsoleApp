package org.Deco.view;

import java.util.List;
import java.util.Scanner;
import org.Deco.model.Cliente;

public class ClienteConsoleView {
    private final Scanner leer = new Scanner (System.in); 
    
    //metodo para mostrar las opciones de este menu 
    public int mostrarMenu() {
        int opcion = 0; 
        //todo el menu 
        System.out.println("_____Gestion Clientes_____");
        System.out.println("1. crear nuevo cliente");
        System.out.println("2. listar todos los clientes");
        System.out.println("3. buscar cliente por ID");
        System.out.println("4. modificar cliente");
        System.out.println("5. eliminar nuevo cliente");
        System.out.println("6. regresar a menu principal ");
        System.out.println("Seleccione una opcion");
        opcion = Integer.parseInt(leer.nextLine()); 
        return opcion ; 
    }
    
    public long solicitarCUI() {
        System.out.println("Ingrese el cui del cliente: ");
        return Long.parseLong(leer.nextLine()); 
    }
    
    //nombre Cliente 
    public String solicitarNombreCliente() {
        System.out.println("ingrese el nombre del cliente: ");
        
        return leer.nextLine(); 
    }
    
        public String solicitarApellidoCliente() {
        System.out.println("ingrese el apellido del cliente: ");
        return leer.nextLine(); 
    }
        
         public String solicitarCorreoElectronico() {
        System.out.println("ingrese el correo electronico del cliente: ");
        return leer.nextLine(); 
    }
         
   //Mostrar el detalle de un cliente   
   public void mostrarCliente(Cliente cliente){
             System.out.println("_____Datos cliente_____");
             System.out.println("cui: " + cliente.getCui()); 
             System.out.println("nombrei: " + cliente.getNombreCliente()); 
             System.out.println("apellido: " + cliente.getApellidoCliente()); 
             System.out.println("correo electronico: " + cliente.getCorreoElectronico()); 
             System.out.println("---\n");
         }
   
   public void mostrarListaClientes(List<Cliente> clientes) {
       System.out.println("_____Lista clientes_____");
       //tabla usando la propiedad %-[tamaño columna]s
       System.out.printf("%-10s %-10s %-10s %-10s" ,  "| cui | "," | nombre | " ," | apellido | " , "| correo electronico  | \n");
       
       for (Cliente cliente : clientes) {
           System.out.printf("%-10s %-10s %-10s %-10s\n",
                          cliente.getCui(), cliente.getNombreCliente(), cliente.getApellidoCliente(), cliente.getCorreoElectronico()); 
         }
        System.out.println(" --- Fin de clientes ---\n");
   }
    
   //para mostrar mensaje personalizado 
   public void mostrarMensaje(String mensaje) { 
       System.out.println("mensaje");
   }
   
   public boolean confirmarAccion(String mensaje) {
        System.out.print(mensaje);
        String respuesta = leer.nextLine(); 
        return respuesta.equalsIgnoreCase("s");
    }
}
