package org.Deco.systen;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.Deco.view.MenuPrincipal;
 
public class Main extends Application{
 
    private static Stage escenarioPrincipal;
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
         this.escenarioPrincipal = escenarioPrincipal;
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/Deco/view/CategoriaView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        escenarioPrincipal.setTitle("Deco Librería");
        escenarioPrincipal.setScene(scene);
        escenarioPrincipal.show();
    }
    public static void cambiarVista(String fxmlPath) throws Exception {
    java.net.URL url = Main.class.getResource(fxmlPath);
    if (url == null) {
        throw new Exception("No se encontró el archivo FXML en la ruta: " + fxmlPath);
    }
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();
    if (escenarioPrincipal == null) {
        throw new Exception("El escenarioPrincipal no ha sido inicializado.");
    }
    escenarioPrincipal.setScene(new Scene(root));
}

      public static void main(String[] args) {
        launch(args);
    }
}
