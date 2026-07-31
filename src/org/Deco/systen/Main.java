package org.Deco.systen;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
       launch(args); 
    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        Parent raiz = FXMLLoader.load(getClass().getResource("/org/Deco/view/InicioSesionView.fxml")); 
        
        Scene escena = new Scene(raiz); 
        
        escenarioPrincipal.setScene(escena); 
        escenarioPrincipal.show(); 
    }
    
}
