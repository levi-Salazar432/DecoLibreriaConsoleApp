package org.Deco.dao;

import java.util.List;
import org.Deco.model.Autor;

public interface AutorDAO {
    
    boolean insertar(Autor autor);
    List<Autor> listarTodos();
    Autor buscar (int id_autor);
    boolean actualizar(Autor autor);
    boolean eliminar (int id_autor);

    public Autor buscarPorId(int id);

    public boolean crear(Autor cliente);
    
}
