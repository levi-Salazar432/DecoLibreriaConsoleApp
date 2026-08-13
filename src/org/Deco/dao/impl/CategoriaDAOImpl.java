package org.Deco.dao.impl;
 
import org.Deco.util.Conexion; 
import org.Deco.model.Categoria;
import org.Deco.dao.CategoriaDAO;
 
import java.util.List; 
import java.sql.CallableStatement; 
import java.util.ArrayList; 
import java.sql.Connection;
import java.sql.SQLException; 
import java.sql.ResultSet;
 
public class CategoriaDAOImpl implements CategoriaDAO {
        @Override
        public List<Categoria> listartodos() { 
        List<Categoria> categoria = new ArrayList<>();
        String consulta = "{ call sp_listarcategorias()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta);
             ResultSet tablaResultado = consultaCall.executeQuery()) {
            while (tablaResultado.next()) {
                categoria.add(new Categoria(
                    tablaResultado.getInt("id_categoria"),
                    tablaResultado.getString("nombre_categoria")          
                ));
            }
        } catch (SQLException e) {    
            System.err.print("Error al listar Categoria: " + e.getMessage());
        }           
        return categoria; 
    }
 
    @Override
    public boolean crear(Categoria categoria) {
        String consultaSQL = "{ call sp_insertarcategoria(?) }"; 
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            consultaCall.setString(1, categoria.getNombreCategoria());
            int filasAfectadas = consultaCall.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear categoría: " + e.getMessage());
            return false;
        }
    }
 
    @Override
    public Categoria buscarPorId(int idCategoria) {
        Categoria categoria = new Categoria();
        String consultaSQL = "{Call sp_buscar_categorias(?)}"; 
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            consultaCall.setInt(1, idCategoria);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                categoria.setIdCategoria(tablaResultado.getInt("ID"));
                categoria.setNombreCategoria(tablaResultado.getString("CATEGORIA"));
                return categoria; 
            } else {
                System.out.println("No existe la Categoria con ese ID");
                return null; 
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Categoria: " + e.getMessage());
        }                                
        return null; 
    }
    @Override
    public boolean actualizar(Categoria categoria) {
        return false; 
    }
 
    @Override
    public boolean eliminar(int idCategoria) {
        String consultaSQL = "{Call sp_eliminar_categoria()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            consultaCall.setInt(1, idCategoria); 
            int filasAfectadas = consultaCall.executeUpdate(); 
            if (filasAfectadas > 0) { 
                System.out.println("Categoria eliminada con exito");
                return true; 
            } else {
                System.out.println("No se encontro ninguna categoria con ese ID");
                return false;    
            }
        } catch (SQLException e) { 
            System.out.println("Error al eliminar categoria: " + e.getMessage());
            return false;
        }
    }
}

