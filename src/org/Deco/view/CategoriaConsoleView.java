package org.Deco.view;
import java.util.List;
import java.util.Scanner; 
import org.Deco.model.Categoria;

public class CategoriaConsoleView {
    private final Scanner leer = new Scanner(System.in);
    
    //metodo para mostrar las opciones de este menu
    public int mostrarMenu(){
        int opcion = 0 ; 
                //todo el menu
                System.out.println("--- GESTION DE CATEGORIAS---");
                System.out.println("1 Crear nueva categoria---");
                System.out.println("2 Listar todas las categorias---");
                System.out.println("3 Buscar Categoria por ID---");
                System.out.println("4 Modificar categoria");
                System.out.println("5 Eliminar nueva categoria");
                System.out.println("0 Regresar a menu Principal");
                opcion = Integer.parseInt(leer.nextLine()); 
                return opcion; 
    }         
    
    public int solicitarIdCategoria(){
        System.out.println("Ingrese el id de categoria: ");
        return Integer.parseInt(leer.nextLine());
     
    }
    public String solicitarNombreCategoria(){
        System.out.println("Ingrese el nombre de categoria: ");       
        return leer.nextLine(); 
}
    
    //mostrar el detalle de una categoria 
    public void mostrarCategoria(Categoria categoria){
        System.out.println("--- DATOS DE CATEGORIA---");
        System.out.println("ID " + categoria.getIdCategoria());
        System.out.println("CATEGORIA " + categoria.getNombreCategoria());
        System.out.println("");
    }
    
    // mostrar la vista de Categoria -- lista de objeto  List<T>, ArrayList<Categoria>
    public void mostrarListaCategoria(List<Categoria> categorias){
        System.out.println("--- LISTA DE CATEGORIA---");
        //tabla usando la propiedad %-[tamaño de columnas)s 
        System.out.printf("%-10s %-10s", " | ID | " , " | NOMBRE  | \n " );       
        for (Categoria categoria : categorias){
        System.out.printf("%-10s %-10s\n " ,
                categoria.getIdCategoria() , categoria.getNombreCategoria());
        
    }
        
            
        }

public void mostrarMensaje(String mensaje) {
    System.out.println(mensaje);
} 

public boolean confirmarAccion(String mensaje) {
        System.out.print(mensaje);
        String respuesta = leer.nextLine(); 
        return respuesta.equalsIgnoreCase("s");
    }
    }

