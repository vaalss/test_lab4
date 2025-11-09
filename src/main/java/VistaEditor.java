import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class VistaEditor {

    @FXML
    private Label lblcorreoUser;
    
    public void actualizarCorreo() {
        User u = controlador.getUser();
        lblcorreoUser.setText(u.getCorreo());
    }

    @FXML
    private ListView<Content> lvContenidoEditor;

    @FXML
    private Label lblMensaje;

    @FXML
    private ChoiceBox<String> cbFiltro;

    @FXML
    private Label lblFiltro;

    private Stage mainWindow;
    private Controller controlador;
    
    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setController(Controller c) {
        this.controlador = c;
        actualizarLista();
    }

    public void initialize() {
        cbFiltro.getItems().addAll("Todos", "Artículo", "Imagen", "Video");
        cbFiltro.getSelectionModel().selectFirst();
        cbFiltro.setOnAction(e -> filtrarContenido());
    }

    @FXML
    void crearClk(ActionEvent event) {
        User usuario = controlador.getUser();
        if(usuario instanceof Editor) {
            Editor editor = (Editor) usuario;
            lvContenidoEditor.getItems().addAll(editor.getContenido());
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaCrear.fxml"));
            Parent root = loader.load();
            VistaCrear vistaCrear = loader.getController();
            vistaCrear.setController(controlador);
            vistaCrear.setMainWindow(mainWindow);

            mainWindow.getScene().setRoot(root);
            mainWindow.setTitle("Crear Contenido");

            actualizarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editarClk(ActionEvent event) throws IOException{
        Content seleccionado = lvContenidoEditor.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Debes seleccionar un contenido para editar");
        } 
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaEditar.fxml"));
            Parent root = loader.load();
            VistaEditar vistaEditar = loader.getController();
            vistaEditar.setMainWindow(mainWindow);
            vistaEditar.setController(controlador);
            vistaEditar.setContenido(seleccionado);
            vistaEditar.actualizarCampos();

            mainWindow.getScene().setRoot(root);
            mainWindow.setTitle("Editar Contenido");

            actualizarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitClk(ActionEvent event) {
        try {
            controlador.setUser(null);
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

    private void actualizarLista() {
        lvContenidoEditor.getItems().clear();
        Editor usuario = (Editor) controlador.getUser();
        lvContenidoEditor.getItems().addAll(usuario.getContenido());
    }

    private void filtrarContenido() {
        String filtro = cbFiltro.getValue();
        lvContenidoEditor.getItems().clear();
        for (Content c : controlador.getContenido()) {
            if (filtro.equals("Todos")) {
                lvContenidoEditor.getItems().add(c);
            } else if (filtro.equals("Artículo") && c instanceof Article) {
                lvContenidoEditor.getItems().add(c);
            } else if (filtro.equals("Imagen") && c instanceof Image) {
                lvContenidoEditor.getItems().add(c);
            } else if (filtro.equals("Video") && c instanceof Video) {
                lvContenidoEditor.getItems().add(c);
            }
        }
    }

}

