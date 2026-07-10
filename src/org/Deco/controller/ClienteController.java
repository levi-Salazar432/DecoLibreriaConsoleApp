package org.Deco.controller;

import org.Deco.dao.ClienteDAO;
import org.Deco.dao.impl.ClienteDAOImpl;
import org.Deco.model.Cliente;
import org.Deco.view.ClienteConsoleView;

public class ClienteController {
    
    private final ClienteDAO dao; 
    private final ClienteConsoleView vista; 
    
    public ClienteController(ClienteConsoleView vista) { 
        this.dao = new ClienteDAOImpl(); 
        this.vista = vista; 
    }
    
    public void iniciar() {
        int opcion; 
        do {
             opcion = vista.mostrarMenu();
             switch (opcion) {
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
             }
         }while (opcion != 4); 
    }
    
    private void listar() {
        vista.mostrarListaClientes(dao.listarTodos()); 
    }

    private void buscar() {
        Long cui = vista.solicitarCUI(); 
        Cliente cliente = dao.buscar(cui); 
        if (cliente != null) { 
            vista.mostrarCliente(cliente);
        }else {
            vista.mostrarMensaje("Cliente no encontrado con el ID: " + cui);
        }
        
    }
}
