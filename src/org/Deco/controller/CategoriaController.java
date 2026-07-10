
package org.Deco.controller;

import org.Deco.dao.CategoriaDAO;
import org.Deco.dao.impl.CategoriaDAOImpl;
import org.Deco.model.Categoria;
import org.Deco.view.CategoriaConsoleView;

public class CategoriaController {
    private final CategoriaDAO dao; 
    private final CategoriaConsoleView vista;
    
    public CategoriaController(CategoriaConsoleView vista) { 
        this.dao = new CategoriaDAOImpl(); 
        this.vista = vista; 
}
    
    public void iniciar(){
        int opcion ;
        do {
            opcion = vista.mostrarMenu(); 
            switch (opcion ) {
                case 1 : 
                    break ; 
                case 2: 
                    listar(); 
                    
                break; 
                case 3: 
                    buscar(); 
                    
                    break;
                    case 4: 
                        
                   break ; 
                    case 5: 
                        eliminar(); 
                                
            }
            
        }while (opcion != 0) ; 
    } 
    
    private void listar(){ 
        vista.mostrarListaCategoria(dao.ListarTodos());
    }

    private void buscar() {
        int id = vista.solicitarIdCategoria(); 
        Categoria categoria = dao.buscarPorId(id);
        if (categoria != null ) {
            vista.mostrarCategoria(categoria);
        }else {
            vista.mostrarMensaje("Cliente  no encontrado con el ID  " + id); 
        }
                
    }      
   private void eliminar() { 
       int id = vista.solicitarIdCategoria(); 
       
       if (vista.confirmarAccion("¿Está seguro de que desea eliminar esta categoría? (s/n): ")) {
           boolean eliminado = dao.eliminar(id);
           
           if (eliminado){
               vista.mostrarMensaje("Categoria eliminada con exito");
           }else { 
               vista.mostrarMensaje("No se pudo eliminar la categoria. Verifique el ID");
           }
       }
   }
   
           
    }

