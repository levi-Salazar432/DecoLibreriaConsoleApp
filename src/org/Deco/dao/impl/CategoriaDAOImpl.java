package org.Deco.dao.impl;

import org.Deco.util.Conexion; 
import org.Deco.model.Categoria;
import org.Deco.dao.CategoriaDAO;

import java.util.List; 
import java.util.ArrayList; 
import java.sql.PreparedStatement; 
import java.sql.Connection;
import java.sql.CallableStatement; 
import java.sql.SQLException; 
import java.sql.ResultSet;

public class CategoriaDAOImpl implements CategoriaDAO{

    @Override
    public boolean insertar(Categoria categoria) {
            return false; 
    }

    @Override
    public List<Categoria> listar() {
        return null; 
    }

    @Override
    public Categoria buscar(int idCategoria) {
        return null; 
    }

    @Override
    public boolean actualizar(Categoria categoria) {
        return false; 
    }

    @Override
    public boolean eliminar(int idCategoria) {
        return false; 
    }
    
}
