package org.Deco.dao.impl;

import java.util.ArrayList;
import org.Deco.model.Autor;
import org.Deco.dao.AutorDAO;
import org.Deco.util.Conexion;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorDAOImpl implements AutorDAO {

    @Override
    public List<Autor> listarTodos() {
        List<Autor> autores = new ArrayList<>();
        String consulta = "{call sp_listarautores()}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consulta); 
             ResultSet tablaResultado = consultaCall.executeQuery()) {
            while (tablaResultado.next()) {
                autores.add(new Autor(
                        tablaResultado.getInt("id_autor"),
                        tablaResultado.getString("nombre_autor"),
                        tablaResultado.getString("apellido_autor"),
                        tablaResultado.getString("nacionalidad"),
                        tablaResultado.getString("biografia")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Autores: " + e.getMessage());
        }
        return autores;
    }

    @Override
    public boolean crear(Autor autor) {
        String consulta = "{call sp_insertarautor(?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            consultaCall.setString(1, autor.getNombre());
            consultaCall.setString(2, autor.getApellido());
            consultaCall.setString(3, autor.getNacionalidad());
            consultaCall.setString(4, autor.getBiografia());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print("Error al crear Autor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Autor buscarPorId(int idAutor) {
        Autor autor = new Autor();
        String consultaSQL = "{call sp_buscarautor(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            consultaCall.setInt(1, idAutor);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                autor.setIdAutor(tablaResultado.getInt("id_autor"));
                autor.setNombre(tablaResultado.getString("nombre_autor"));
                autor.setApellido(tablaResultado.getString("apellido_autor"));
                autor.setNacionalidad(tablaResultado.getString("nacionalidad"));
                autor.setBiografia(tablaResultado.getString("biografia"));
            } else {
                return null;
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
    public boolean eliminar(int idAutor) {
        return false;
    }

    @Override
    public boolean insertar(Autor autor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Autor buscar(int id_autor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
    

