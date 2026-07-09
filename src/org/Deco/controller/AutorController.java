package org.Deco.controller;

import org.Deco.dao.AutorDAO;
import org.Deco.dao.impl.AutorDAOImpl;
import org.Deco.view.AutorConsoleView;

public class AutorController {

    private final AutorDAO dao;
    private final AutorConsoleView vista;

    public AutorController(AutorConsoleView vista) {
        this.dao = new AutorDAOImpl();
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;

        do {
            opcion = vista.mostrarMenu();

            if (opcion == 2) {
                listar();
            }

        } while (opcion != 4);
    }

    private void listar() {
        vista.mostrarListaAutores(dao.listarTodos());
    }
}