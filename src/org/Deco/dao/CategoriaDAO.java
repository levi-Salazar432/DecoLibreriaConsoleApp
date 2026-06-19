package org.Deco.dao;

import java.util.List; 

import org.Deco.model.Categoria;

public interface CategoriaDAO {
    //firmas de metodos 
    //CRUD 
   boolean insertar(Categoria categoria);
   List<Categoria> listar();
   Categoria buscar(int idCategoria);
   boolean actualizar(Categoria categoria);
   boolean eliminar(int idCategoria);
}
