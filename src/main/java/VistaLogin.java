import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaLogin {

    @FXML
    private TextField tfcorreo;

    @FXML
    private PasswordField tfpass;
    
    @FXML
    private Label lblMensaje;

    private Stage mainWindow;
    private Controller controlador;

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setController(Controller c) {
        this.controlador = c;
    }

    @FXML
    void loginClk(ActionEvent event) {
        String correo = tfcorreo.getText();
        String password = tfpass.getText();
        if (!correo.isEmpty() && !password.isEmpty()) {
            boolean acceso = controlador.ingresar(correo, password);
            if (acceso) {
                User u = controlador.getUser();
                if (u instanceof Editor) {
                    try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaEditor.fxml"));
                    Parent root = loader.load();
                    VistaEditor vistaEditor = loader.getController();
                    vistaEditor.setController(controlador);
                    vistaEditor.setMainWindow(mainWindow);
                    vistaEditor.actualizarCorreo();
                    mainWindow.getScene().setRoot(root);
                    mainWindow.setTitle("Editor");
                    mainWindow.sizeToScene();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (u instanceof Admin) {
                    try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaAdmin.fxml"));
                    Parent root = loader.load();
                    VistaAdmin vistaAdmin = loader.getController();
                    vistaAdmin.setController(controlador);
                    vistaAdmin.setMainWindow(mainWindow);
                    mainWindow.getScene().setRoot(root);
                    mainWindow.setTitle("Admin");
                    mainWindow.sizeToScene();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                lblMensaje.setText("No se ha encontrado el usuario o \nlas credenciales son incorrectas");
            }
        } else {
            lblMensaje.setText("Debes llenar todos los campos");
        }
    }

    @FXML
    void registrarClk(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaRegistrar.fxml"));
            Parent root = loader.load();
            VistaRegistrar vistaRegistrar = loader.getController();
            vistaRegistrar.setMainWindow(mainWindow);
            vistaRegistrar.setController(controlador);
            mainWindow.getScene().setRoot(root);
            mainWindow.setTitle("Registro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

