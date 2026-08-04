package org.Deco.controller;
 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.Deco.dao.CategoriaDAO;
import org.Deco.dao.impl.CategoriaDAOImpl;
import org.Deco.model.Categoria;
import org.Deco.systen.Main;
 
public class CategoriaFXController implements Initializable {
 
 
    @FXML 
    private TextField txtidCategoria; 
    @FXML 
    private TextField txtnombreCategoria; 
    @FXML
    private TableView<Categoria> tablaCategoria; 
    @FXML
    private Label lblMensaje;

    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
    private final ObservableList<Categoria> listaCategoria = FXCollections.observableArrayList(); 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        seleccionarFila();
    }
    private void cargarTabla() {
        listaCategoria.setAll(categoriaDAO.listartodos());
        tablaCategoria.setItems(listaCategoria);
    }   
    private void seleccionarFila() {
       tablaCategoria.getSelectionModel().selectedItemProperty().addListener(
               (obs,oldSelection,newSelection) -> {
                   if (newSelection != null) {
                       txtidCategoria.setText(String.valueOf(newSelection.getIdCategoria()));
                       txtnombreCategoria.setText(newSelection.getNombreCategoria());        
                   }
                });
    }
     @FXML
    private void handleGuardar() {
            if ( txtnombreCategoria.getText().isEmpty()) { 
            mostrarError("Todos los campos son obligatorios.");
            return;
}
    Categoria categoria = new Categoria();
    categoria.setNombreCategoria(txtnombreCategoria.getText().trim()); 
         if (categoriaDAO.crear(categoria)) {
             lblMensaje.setText("Categoria registrado con exito");       
//             cargarTabla();
//             limpiarFormulario();
         }else{
             mostrarError("No se puede registrar la cateogira");
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
        txtidCategoria.clear();
        txtnombreCategoria.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
   
    

