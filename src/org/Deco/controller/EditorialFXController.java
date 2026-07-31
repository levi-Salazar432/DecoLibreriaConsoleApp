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
import org.Deco.dao.EditorialDAO;
import org.Deco.dao.impl.EditorialDAOImpl;
import org.Deco.model.Editorial;
import org.Deco.systen.Main;

public class EditorialFXController implements Initializable {

    @FXML
    private TextField txtNit;
    @FXML
    private TextField txtNombreEditorial;
    @FXML
    private TextField txtTelefonoEditorial;
    @FXML
    private TextField txtDireccionEditorial;
    @FXML
    private TableView<Editorial> tablaEditoriales;
    @FXML
    private TableColumn<Editorial, String> colNit;
    @FXML
    private TableColumn<Editorial, String> colNombre;
    @FXML
    private TableColumn<Editorial, String> colTelefono;
    @FXML
    private TableColumn<Editorial, String> colDireccion;
    @FXML
    private Label lblMensaje;

    private final EditorialDAO editorialDAO = new EditorialDAOImpl();
    private final ObservableList<Editorial> listaEditoriales
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarColumnas();
        cargarTabla();
        seleccionarFila();
    }

    private void configurarColumnas() {
        colNit.setCellValueFactory(new PropertyValueFactory<>("nit"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEditorial"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoEditorial"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionEditorial"));
    }

    private void cargarTabla() {
        listaEditoriales.setAll(editorialDAO.ListarTodos());
        tablaEditoriales.setItems(listaEditoriales);
    }

    private void seleccionarFila() {
        tablaEditoriales.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtNit.setText(String.valueOf(newSelection.getNit()));
                        txtNombreEditorial.setText(newSelection.getNombreEditorial());
                        txtTelefonoEditorial.setText(newSelection.getTelefonoEditorial());
                        txtDireccionEditorial.setText(newSelection.getDireccionEditorial());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtNit.getText().isEmpty() || txtNombreEditorial.getText().isEmpty()
                    || txtTelefonoEditorial.getText().isEmpty()
                    || txtDireccionEditorial.getText().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }
            Editorial editorial = new Editorial();
            editorial.setNit(txtNit.getText().trim());
            editorial.setNombreEditorial(txtNombreEditorial.getText().trim());
            editorial.setTelefonoEditorial(txtTelefonoEditorial.getText().trim());
            editorial.setDireccionEditorial(txtDireccionEditorial.getText().trim());
            if (editorialDAO.crear(editorial)) {
                lblMensaje.setText("Editorial registrado exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar el cliente.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El CUI debe ser un número válido.");
        } catch (Exception e) {
            mostrarError("Error al guardar: " + e.getMessage());
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
            Main.cambiarVista("/org/Deco/view/MenuPrincipal.fxml");
        } catch (Exception e) {
            mostrarError("Error al volver al menú: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtNit.clear();
        txtNombreEditorial.clear();
        txtTelefonoEditorial.clear();
        txtDireccionEditorial.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}