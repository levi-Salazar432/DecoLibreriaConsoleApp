package org.Deco.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.Deco.model.Usuario;


public class MenuPrincipalDashboardController implements Initializable, DashboardController {

    private Usuario usuarioActual;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void iniciarUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
    }
    
    @FXML
    private void handleNoDisponible() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Módulo no disponible");
        alert.setHeaderText(null);
        alert.setContentText("Este módulo no está disponible aún.");
        alert.showAndWait();
    }

    @FXML
    private void handleSalir() {
        Platform.exit();
    }
    

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
