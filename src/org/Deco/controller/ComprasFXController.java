package org.Deco.controller;

import java.net.URL;
import java.time.LocalDateTime;
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
import org.Deco.model.Compras;
import org.Deco.systen.Main;

public class ComprasFXController implements Initializable {

    @FXML
    private TextField txtNoCompra;
    @FXML
    private TextField txtTotalCompra;
    @FXML
    private TextField txtCui;
    
    @FXML
    private TableView<Compras> tablaCompras;
    @FXML
    private TableColumn<Compras, Integer> colNoCompra;
    @FXML
    private TableColumn<Compras, LocalDateTime> colFechaCompra;
    @FXML
    private TableColumn<Compras, Float> colTotalCompra;
    @FXML
    private TableColumn<Compras, Integer> colCui;
    
    @FXML
    private Label lblMensaje;

    // Nota: Asegúrate de tener tu ComprasDAO implementado
    // private final ComprasDAO comprasDAO = new ComprasDAOImpl();
    private final ObservableList<Compras> listaCompras = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        // cargarTabla(); // Descomenta cuando tengas el DAO listo
        seleccionarFila();
    }

    private void configurarColumnas() {
        colNoCompra.setCellValueFactory(new PropertyValueFactory<>("noCompra"));
        colFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        colTotalCompra.setCellValueFactory(new PropertyValueFactory<>("totalCompra"));
        colCui.setCellValueFactory(new PropertyValueFactory<>("cui"));
    }

    private void seleccionarFila() {
        tablaCompras.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        txtNoCompra.setText(String.valueOf(newSelection.getNoCompra()));
                        txtTotalCompra.setText(String.valueOf(newSelection.getTotalCompra()));
                        txtCui.setText(String.valueOf(newSelection.getCui()));
                    }
                });
    }

    @FXML
    private void handleLimpiar() {
        limpiarFormulario();
        lblMensaje.setText("");
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
        txtTotalCompra.clear();
        txtCui.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
