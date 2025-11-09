import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaEditar {

    @FXML
    private Label lblActDesc;

    @FXML
    private Label lblActTitle;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblNewDesc;

    @FXML
    private Label lblNewTitle;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField tfDescContent;

    @FXML
    private TextField tfNombreContent;

    private Stage mainWindow;
    private Controller controlador;
    private Content contenido;

    public void setContenido(Content c) {
        this.contenido = c;
    }
    
    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setController(Controller c) {
        this.controlador = c;
    }

    public void actualizarCampos() {
        lblTitle.setText(contenido.getNombre());
        lblDesc.setText(contenido.getDescrpicion());
    }

    @FXML
    void cambiosClk(ActionEvent event) {
        String nuevoTitulo = tfNombreContent.getText();
        String nuevaDesc = tfDescContent.getText();
        Editor editor = (Editor) controlador.getUser();
        lblMensaje.setText(editor.editar(contenido, nuevoTitulo, nuevaDesc));
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
