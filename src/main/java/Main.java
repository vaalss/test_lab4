import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    private Controller controlador = new Controller();
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaLogin.fxml"));
            Parent root = loader.load();
            VistaLogin vistaLogin =  loader.getController();
            vistaLogin.setController(controlador);
            vistaLogin.setMainWindow(primaryStage);
            primaryStage.setTitle("LOG IN");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}