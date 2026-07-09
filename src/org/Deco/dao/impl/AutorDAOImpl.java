package org.Deco.dao.impl;

import org.Deco.util.Conexion;
import org.Deco.model.Autor;
import org.Deco.dao.AutorDAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AutorDAOImpl implements AutorDAO {


        @Override
        public List<Autor> listarTodos() {
        List<Autor> autor = new ArrayList<>();

        String consulta = "{call sp_listar_autor()}";

        try (
        Connection conexion = Conexion.getInstancia().conectar();
        CallableStatement consultaCall = conexion.prepareCall(consulta);
        ResultSet tabla = consultaCall.executeQuery()
    ) {

        while (tabla.next()) {
            autor.add(new Autor(
                tabla.getInt("id_autor"),
                tabla.getString("nombre_autor"),
                tabla.getString("apellido_autor")
            ));
        }

    } catch (SQLException e) {
        System.err.print("Error al listar autores: " + e.getMessage());
    }

    return autor;
}


    @Override
    public boolean insertar(Autor autor) {
        return false;
    }

    @Override
    public Autor buscar(int id_autor) {
        return null;
    }

    @Override
    public boolean actualizar(Autor autor) {
        return false;
    }

    @Override
    public boolean eliminar(int id_autor) {
        return false;
    }
}

