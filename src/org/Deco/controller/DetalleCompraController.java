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
import org.Deco.dao.DetalleCompraDAO;
import org.Deco.dao.impl.DetalleCompraDAOImpl;
import org.Deco.model.DetalleCompra;
import org.Deco.systen.Main;

public class DetalleCompraController implements Initializable {

    @FXML
    private TextField txtNoCompra;
    @FXML
    private TextField txtIsbn;
    @FXML
    private TableView<DetalleCompra> tablaDetalleCompra;
    @FXML
    private TableColumn<DetalleCompra, Integer> colIdDetalleCompra;
    @FXML
    private TableColumn<DetalleCompra, Integer> colNoCompra;
    @FXML
    private TableColumn<DetalleCompra, String> colIsbn;
    @FXML
    private Label lblMensaje;

    private final DetalleCompraDAO detalleCompraDAO = new DetalleCompraDAOImpl();
    private final ObservableList<DetalleCompra> listaDetalleCompra
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarColumnas();
        cargarTabla();
        seleccionarFila();
    }

    private void configurarColumnas() {
        colIdDetalleCompra.setCellValueFactory(new PropertyValueFactory<>("idDetalleCompra"));
        colNoCompra.setCellValueFactory(new PropertyValueFactory<>("noCompra"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    }

    private void cargarTabla() {
        listaDetalleCompra.setAll(detalleCompraDAO.ListarTodos());
        tablaDetalleCompra.setItems(listaDetalleCompra);
    }

    private void seleccionarFila() {
        tablaDetalleCompra.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtNoCompra.setText(String.valueOf(newSelection.getNoCompra()));
                        txtIsbn.setText(newSelection.getIsbn());
                    }
                });
    }

    @FXML
    private void handleGuardar() {
        try {
            if (txtNoCompra.getText().isEmpty() || txtIsbn.getText().isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setNoCompra(Integer.parseInt(txtNoCompra.getText().trim()));
            detalleCompra.setIsbn(txtIsbn.getText().trim());
            if (detalleCompraDAO.crear(detalleCompra)) {
                lblMensaje.setText("Detalle de compra registrado exitosamente.");
                cargarTabla();
                limpiarFormulario();
            } else {
                mostrarError("No se pudo registrar el detalle de compra.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El No. Compra debe ser un número válido.");
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
            Main.cambiarVista("/org/Deco/view/MenuPrincipalDashboardView.fxml");
        } catch (Exception e) {
            mostrarError("Error al volver al menú: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtNoCompra.clear();
        txtIsbn.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
