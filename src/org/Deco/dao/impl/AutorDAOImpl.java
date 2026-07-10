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

        String consulta = "{call sp_listarautores()}";

        try (
        Connection conexion = Conexion.getInstancia().conectar();
        CallableStatement consultaCall = conexion.prepareCall(consulta);
        ResultSet tabla = consultaCall.executeQuery()
    ) {

        while (tabla.next()) {
            autor.add(new Autor(
                tabla.getInt("id_autor"),
                tabla.getString("nombre_autor"),
                tabla.getString("apellido_autor"),
                tabla.getString("nacionalidad"),
                tabla.getString("biografia")
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

    // objeto
    Autor autor = new Autor();

    // consulta
    String consultaSQL = "{call sp_buscarAutor(?)}";

    try (
        Connection conexion = Conexion.getInstancia().conectar();
        CallableStatement consultaCall = conexion.prepareCall(consultaSQL);
    ) {

        consultaCall.setInt(1, id_autor);

        ResultSet tablaResultado = consultaCall.executeQuery();

        if (tablaResultado.next()) {
            autor.setId_autor(tablaResultado.getInt("id_autor"));
            autor.setNombre_autor(tablaResultado.getString("nombre_autor"));
            autor.setApellido_autor(tablaResultado.getString("apellido_autor"));
            autor.setNacionalidad(tablaResultado.getString("nacionalidad"));
            autor.setBiografia(tablaResultado.getString("biografia"));
        } else {
            System.out.println("No existe el Autor con ese ID");
        }

    } catch (SQLException e) {
        System.err.print("Error al buscar Autor: " + e.getMessage());
    }

    return autor;
}


    @Override
    public boolean actualizar(Autor autor) {
        return false;
    }

    @Override
    public boolean eliminar(int id_autor) {
        return false;
    }

    @Override
    public Autor buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    

