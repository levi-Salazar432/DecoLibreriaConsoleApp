package org.Deco.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.Deco.dao.AutoresLibroDAO;
import org.Deco.model.AutoresLibro;
import org.Deco.util.Conexion;

public class AutoresLibroDAOImpl implements AutoresLibroDAO {

    @Override
    public List<AutoresLibro> listarTodos() {

        List<AutoresLibro> autoresLibros = new ArrayList<>();

        String consulta = "{call sp_listarautoreslibro()}";

        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta);
            ResultSet tablaResultado = consultaCall.executeQuery()
        ) {

            while (tablaResultado.next()) {

                autoresLibros.add(new AutoresLibro(
                    tablaResultado.getInt("id_autor_libro"),
                    tablaResultado.getInt("id_autor"),
                    tablaResultado.getString("isbn")
                ));
            }

        } catch (SQLException e) {

            System.err.println(
                "Error al listar Autores Libro: " + e.getMessage()
            );
        }

        return autoresLibros;
    }

    @Override
    public boolean crear(AutoresLibro autoresLibro) {

        String consulta = "{call sp_insertarautorlibro(?, ?)}";

        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta)
        ) {

            consultaCall.setInt(1, autoresLibro.getIdAutor());
            consultaCall.setString(2, autoresLibro.getIsbn());

            return consultaCall.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                "Error al crear relación Autor Libro: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public AutoresLibro buscarPorId(int idAutorLibro) {

        AutoresLibro autoresLibro = new AutoresLibro();

        String consultaSQL = "{call sp_buscarautorlibro(?)}";

        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consultaSQL)
        ) {

            consultaCall.setInt(1, idAutorLibro);

            ResultSet tablaResultado = consultaCall.executeQuery();

            if (tablaResultado.next()) {

                autoresLibro.setIdAutorLibro(
                    tablaResultado.getInt("id_autor_libro")
                );

                autoresLibro.setIdAutor(
                    tablaResultado.getInt("id_autor")
                );

                autoresLibro.setIsbn(
                    tablaResultado.getString("isbn")
                );

            } else {

                return null;
            }

        } catch (SQLException e) {

            System.err.println(
                "Error al buscar Autor Libro: " + e.getMessage()
            );
        }

        return autoresLibro;
    }

    @Override
    public boolean actualizar(AutoresLibro autoresLibro) {

        String consulta = "{call sp_actualizarautorlibro(?, ?, ?)}";

        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta)
        ) {

            consultaCall.setInt(1, autoresLibro.getIdAutorLibro());
            consultaCall.setInt(2, autoresLibro.getIdAutor());
            consultaCall.setString(3, autoresLibro.getIsbn());

            return consultaCall.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                "Error al actualizar Autor Libro: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean eliminar(int idAutorLibro) {

        String consulta = "{call sp_eliminarautorlibro(?)}";

        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta)
        ) {

            consultaCall.setInt(1, idAutorLibro);

            return consultaCall.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                "Error al eliminar Autor Libro: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean insertar(AutoresLibro autoresLibro) {
        return crear(autoresLibro);
    }

    @Override
    public AutoresLibro buscar(int idAutorLibro) {
        return buscarPorId(idAutorLibro);
    }
}