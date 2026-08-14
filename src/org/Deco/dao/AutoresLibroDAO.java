package org.Deco.dao;

import java.util.List;
import org.Deco.model.AutoresLibro;

public interface AutoresLibroDAO {

    boolean insertar(AutoresLibro autoresLibro);

    List<AutoresLibro> listarTodos();

    AutoresLibro buscar(int idAutorLibro);

    boolean actualizar(AutoresLibro autoresLibro);

    boolean eliminar(int idAutorLibro);

    AutoresLibro buscarPorId(int id);

    boolean crear(AutoresLibro autoresLibro);
}