package org.Deco.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.Deco.dao.UsuarioDao;
import org.Deco.systen.Main;
import org.Deco.util.SecurityUtil;
import org.Deco.util.ValidacionException;

public class RegistrarUsuarioController implements Initializable {

    @FXML private TextField txtUsusario;
    @FXML private TextField txtPassword;
    @FXML private TextField txtConfirmarPassword;
    @FXML private ChoiceBox<String> cbRol;

    @FXML private Button btnRegistrar;
    @FXML private Button btnRegresar;
    @FXML private Label lblMensaje;

    private UsuarioDao usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDAO = new UsuarioDao();
        cbRol.getItems().addAll("admin", "cajero", "empleado");
        cbRol.setValue("empleado");
        lblMensaje.setText("");
    }

    @FXML
    public void eventoRegistrar(ActionEvent evento) {
        try {
            ValidacionException.validarNoVacio(txtUsusario.getText(), "usuario");
            ValidacionException.validarNoVacio(txtPassword.getText(), "contraseña");
            ValidacionException.validarNoVacio(txtConfirmarPassword.getText(),
                    "confirmar contraseña");
            ValidacionException.validarCoincidencia(txtPassword.getText(),
                    txtConfirmarPassword.getText(), "Las contraseñas no coinciden");
            ValidacionException.validarLongitudMinima(txtPassword.getText(), 6,
                    "La contraseña debe tener al menos 6 caracteres");
            ValidacionException.validarNulo(cbRol.getValue(), "Debe seleccionar un rol");

            String usuario = txtUsusario.getText().trim();
            String password = txtPassword.getText();
            String rol = cbRol.getValue();
            String passwordHash = SecurityUtil.hashSHA256Password(password);
            boolean registrado = usuarioDAO.registrarUsuario(usuario, passwordHash, rol);

            if (registrado) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Usuario registrado con exito");
                Main.cambiarVista("/org/Deco/view/InicioSesionView.fxml");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al Registrar. El usuario podría ya existir.");
            }

        } catch (ValidacionException e) {
            mostrarAlerta(Alert.AlertType.WARNING, e.getMessage());
            lblMensaje.setText(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al volver al Login: " + e.getMessage());
        }
    }

    @FXML
    public void eventoRegresar(ActionEvent evento) {
        try {
            Main.cambiarVista("/org/Deco/view/InicioSesionView.fxml");
        } catch (Exception e) {
            System.err.println("Error al cargar registro: " + e.getMessage());
            lblMensaje.setText("Error interno");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.show();
    }
}