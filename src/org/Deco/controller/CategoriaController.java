
package org.Deco.controller;

import org.Deco.dao.CategoriaDAO;
import org.Deco.dao.impl.CategoriaDAOImpl;
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
            if (opcion == 3 ) {
                    listar(); 
            }
            
        }while (opcion != 0) ; 
    } 
    
    private void listar(){ 
        vista.mostrarListaCategoria(dao.ListarTodos());
    }
}
