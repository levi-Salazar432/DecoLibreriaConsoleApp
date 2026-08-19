package org.Deco.dao;

import java.util.List;
import org.Deco.model.Libro;

public interface LibroDAO {
    List<Libro> ListarTodos();
    boolean crear(Libro libro);
    Libro buscarPorId(String isbn);
    boolean actualizar(Libro libro);
    boolean eliminar(String isbn);
}