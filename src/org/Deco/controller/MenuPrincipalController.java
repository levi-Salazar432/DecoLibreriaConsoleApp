package org.Deco.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.Deco.systen.Main;

public class MenuPrincipalController {

    @FXML
    private void handleAutores() {
        try {
            Main.cambiarVista("/org/Deco/view/AutorView.fxml");
        } catch (Exception e) {
            mostrarError("Error al cargar la vista de autores:\n" + e.getMessage());
        }
    }

    @FXML
    private void handleNoDisponible() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Módulo no disponible");
        alert.setHeaderText(null);
        alert.setContentText("Este módulo aun no esta disponible.");
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
