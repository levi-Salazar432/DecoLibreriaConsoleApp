package org.Deco.dao.impl;

import org.Deco.util.Conexion; 
import org.Deco.model.Cliente; 
import org.Deco.dao.ClienteDAO; 

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement; 

public class ClienteDAOImpl implements ClienteDAO{

    @Override
    public boolean insertar(Cliente cliente) {
        return false; 
    }

    @Override
    public List<Cliente> Listar() {
        return null; 
    }

    @Override
    public Cliente buscar(long cui) {
        return null; 
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        return false; 
    }

    @Override
    public boolean eliminar(long cui) {
        return false; 
    }
}
