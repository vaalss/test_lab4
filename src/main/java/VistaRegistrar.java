import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaRegistrar {

    @FXML
    private TextField tfcorreo;

    @FXML
    private PasswordField tfpass;

    @FXML
    private Label lblMensaje;

    @FXML
    private ChoiceBox<String> cbRol;

    @FXML
    public void initialize() {
        cbRol.getItems().addAll("Editor", "Administrador");
        cbRol.getSelectionModel().selectFirst();
    }


    private Stage mainWindow;
    private Controller controlador;

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setController(Controller c) {
        this.controlador = c;
    }

    @FXML
    void registrarClk(ActionEvent event) {
        String correo = tfcorreo.getText();
        String password = tfpass.getText();
        String rolSelec = cbRol.getSelectionModel().getSelectedItem();
        User u;
        if (!correo.trim().isEmpty() && !password.trim().isEmpty()) {
            for (User U : controlador.getUsuarios()) {
                if (U.getCorreo().equals(correo)) {
                    lblMensaje.setText("Ya existe una cuenta con este correo");
                    return;
                }
            }
            switch (rolSelec) {
                case "Editor":
                    u = new Editor(correo, password);
                    this.controlador.getUsuarios().add(u);
                    lblMensaje.setText("Registro exitoso. Puedes iniciar sesión");
                    break;
                case "Administrador":
                    u = new Admin(correo, password);
                    this.controlador.getUsuarios().add(u);
                    lblMensaje.setText("Registro exitoso. Puedes iniciar sesión");
                    break;
                default:
                    lblMensaje.setText("No se ha podido registrar");
                    break;
            }
        } else {
            lblMensaje.setText("Debes llenar todos los campos");
        }
    }

    @FXML
    void volverLogInClk(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaLogin.fxml"));
            Parent root = loader.load();
            VistaLogin vistaLogin =  loader.getController();
            vistaLogin.setController(controlador);
            vistaLogin.setMainWindow(mainWindow);
            mainWindow.setTitle("LOG IN");
            mainWindow.setScene(new Scene(root));
            mainWindow.show();
        } catch (Exception e) {
            e.printStackTrace();        
        }
    }

}
