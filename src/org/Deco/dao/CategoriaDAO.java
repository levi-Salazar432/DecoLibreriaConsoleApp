package org.Deco.dao;

import java.util.List; 
import org.Deco.model.Categoria;

public interface CategoriaDAO {
   boolean crear(Categoria categoria);
   List<Categoria> listartodos();
   Categoria buscarPorId(int idCategoria);
   boolean actualizar(Categoria categoria);
   boolean eliminar(int idCategoria);
}
