package org.Deco.dao.impl;

import org.Deco.util.Conexion;
import org.Deco.model.Editorial;
import org.Deco.dao.EditorialDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class EditorialDAOImpl implements EditorialDAO{

    @Override
    public boolean insertar(Editorial editorial) {
        return false;
    }

    @Override
    public List<Editorial> ListarTodos() {
        //crear  Lista
        List<Editorial> editorial = new ArrayList (); //null
        //crear nuestra consulta
         String consulta = "{call sp_listareditoriales()}";
        //maperar el resultado de la conculta a objeto y lo agregamos a la lista
        //try with resources / intentar con recursos _> cierra el recurso al completar el intento
        //recurso: Conexion, al final se cierra
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consulta);
                ResultSet tablaResultado = consultaCall.executeQuery();){
                //ciclo para rellenar mi lista
                //verificar cada fila del result set
                //va a guarda cada celda dentro de cada atributo de mi objeto
                while (tablaResultado.next()) {
                    editorial.add(new Editorial(
                            tablaResultado.getString("nit"),
                            tablaResultado.getString("NombreEditorial"),
                            tablaResultado.getString("telefonoEditorial"),
                            tablaResultado.getString("direccionEditorial")
                    ));
            }
            
        }catch (SQLException e) {
            System.err.print("Error al listar Clientes:"+ e.getMessage());
        }      
            
        //retornamos una lista
        return editorial;
    }

    @Override
    public Editorial buscar(String nit) {
        return null;
    }

    @Override
    public boolean actualizar(Editorial editorial){
        return false;
    }

    @Override
    public boolean eliminar(String nit) {
        return false;
    }
    
}
