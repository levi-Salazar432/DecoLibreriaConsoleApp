/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.Deco.controller;

import org.Deco.dao.EditorialDAO;
import org.Deco.dao.impl.EditorialDAOImpl;
import org.Deco.view.EditorialConsoleView;
import org.Deco.model.Editorial;

/**
 *
 * @author joshua
 */
public class EditorialController {
    
    private final EditorialDAO dao;
    private final EditorialConsoleView vista;

    public EditorialController(EditorialConsoleView vista) {
        this.dao = new EditorialDAOImpl();
        this.vista = vista;
    }
    
    public void iniciar(){
        int opcion;
        do{
            opcion = vista.mostrarMenu();
            switch (opcion){
                case 1:
                    
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                default:
                    throw new AssertionError();
          
            }
        } while (opcion !=6 );
    }
    
    private void listar(){
        vista.mostrarListaEditorial(dao.ListarTodos());
    }
    
    private void buscar(){
        String nit = vista.SolicitarNIT();
        Editorial editorial = dao.buscarPorId(nit);
        if (editorial != null){
            vista.mostrarEditorial(editorial);
        }else {
            vista.mostrarMensaje("Editorial no encontrado con el NIT:" + nit);
        }
    }
}
