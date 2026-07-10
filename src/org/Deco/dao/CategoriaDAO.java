package org.Deco.dao;

import java.util.List; 

import org.Deco.model.Categoria;

public interface CategoriaDAO {
    //firmas de metodos 
    //CRUD 
   boolean crear(Categoria categoria);
   List<Categoria> ListarTodos();
   Categoria buscarPorId(int idCategoria);
   boolean actualizar(Categoria categoria);
   boolean eliminar(int idCategoria);
}
