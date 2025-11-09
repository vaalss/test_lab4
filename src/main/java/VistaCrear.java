import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaCrear {

    @FXML
    private ChoiceBox<String> cbTipoContenido;

    @FXML
    public void initialize() {
        cbTipoContenido.getItems().addAll("Artículo", "Imagen", "Video");
        cbTipoContenido.getSelectionModel().selectFirst();
    }

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblTipoContenido;

    @FXML
    private TextField tfDescContent;

    @FXML
    private TextField tfNombreContent;

    private Stage mainWindow;
    private Controller controlador;

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setController(Controller c) {
        this.controlador = c;
    }

    @FXML
    void crearContClk(ActionEvent event) {
        Editor creador = (Editor) controlador.getUser();
        String tipoSelec = cbTipoContenido.getSelectionModel().getSelectedItem();
        String titulo = tfNombreContent.getText();
        String descripcion = tfDescContent.getText();
        lblMensaje.setText(creador.crear(tipoSelec, titulo, descripcion, controlador.getContenido()));   
    }

    @FXML
    void regresarClk(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaEditor.fxml"));
            Parent root = loader.load();
            VistaEditor vistaEditor = loader.getController();
            vistaEditor.setMainWindow(mainWindow);
            vistaEditor.setController(controlador);
            mainWindow.getScene().setRoot(root);
            mainWindow.setTitle("Editor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
