package org.Deco.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import org.Deco.dao.AutoresLibroDAO;
import org.Deco.dao.impl.AutoresLibroDAOImpl;
import org.Deco.model.AutoresLibro;
import org.Deco.systen.Main;

public class AutoresLibroFXController implements Initializable {

    @FXML
    private TextField txtIdAutorLibro;
    @FXML
    private TextField txtIdAutor;
    @FXML
    private TextField txtIsbn;
    @FXML
    private Label lblMensaje;
    @FXML
    private TableView<AutoresLibro> tablaAutoresLibro;
    @FXML
    private TableColumn<AutoresLibro, Integer> colID;
    @FXML
    private TableColumn<AutoresLibro, Integer> colIdAutor;
    @FXML
    private TableColumn<AutoresLibro, String> colIsbn;
    private final AutoresLibroDAO autoresLibroDAO =
            new AutoresLibroDAOImpl();

    private final ObservableList<AutoresLibro> listaAutoresLibro =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cargarTabla();
        seleccionarFila();
        configurarTabla();
    }

    private void cargarTabla() {
        listaAutoresLibro.setAll(
                autoresLibroDAO.listarTodos()
        );
        tablaAutoresLibro.setItems(listaAutoresLibro);
    }

    private void seleccionarFila() {
        tablaAutoresLibro.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {

                    if (newSelection != null) {

                        txtIdAutorLibro.setText(
                                String.valueOf(
                                        newSelection.getIdAutorLibro()
                                )
                        );

                        txtIdAutor.setText(
                                String.valueOf(
                                        newSelection.getIdAutor()
                                )
                        );

                        txtIsbn.setText(
                                newSelection.getIsbn()
                        );
                    }
                });
    }
    @FXML
    private void handleGuardar() {
        try {
            if (txtIdAutor.getText().isEmpty()
                    || txtIsbn.getText().isEmpty()) {

                mostrarError(
                        "Todos los campos son obligatorios."
                );

                return;
            }

            AutoresLibro autoresLibro = new AutoresLibro();

            autoresLibro.setIdAutor(
                    Integer.parseInt(
                            txtIdAutor.getText().trim()
                    )
            );

            autoresLibro.setIsbn(
                    txtIsbn.getText().trim()
            );

            if (autoresLibroDAO.crear(autoresLibro)) {

                lblMensaje.setText(
                        "Relación Autor Libro registrada exitosamente."
                );

                cargarTabla();
                limpiarFormulario();

            } else {

                mostrarError(
                        "No se pudo registrar la relación Autor Libro."
                );
            }
        } catch (NumberFormatException e) {

            mostrarError(
                    "El ID del autor debe ser un número válido."
            );

        } catch (Exception e) {

            mostrarError(
                    "Error al guardar: " + e.getMessage()
            );
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarFormulario();
        lblMensaje.setText("");
    }
    @FXML
    private void handleActualizar() {

        cargarTabla();
        lblMensaje.setText("Tabla actualizada.");
    }
    @FXML
    private void handleVolver() {
        try {
            Main.cambiarVista(
                    "/org/Deco/view/MenuPrincipalDashboardView.fxml"
            );

        } catch (Exception e) {

            mostrarError(
                    "Error al volver al menú: " + e.getMessage()
            );
        }
    }

    private void limpiarFormulario() {
        txtIdAutorLibro.clear();
        txtIdAutor.clear();
        txtIsbn.clear();
    }
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
    private void configurarTabla() {
        colID.setCellValueFactory(
                new PropertyValueFactory<AutoresLibro, Integer>(
                        "idAutorLibro"
                )
        );

        colIdAutor.setCellValueFactory(
                new PropertyValueFactory<AutoresLibro, Integer>(
                        "idAutor"
                )
        );

        colIsbn.setCellValueFactory(
                new PropertyValueFactory<AutoresLibro, String>(
                        "isbn"
                )
        );
    }
}