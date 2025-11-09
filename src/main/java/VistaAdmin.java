import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class VistaAdmin {

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblcorreoUser;

    @FXML
    private ListView<Content> lvContenidoAdmin;

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
    void eliminarClk(ActionEvent event) {
        Content seleccionado = lvContenidoAdmin.getSelectionModel().getSelectedItem();
        Admin admin = (Admin) controlador.getUser();
        ArrayList<Content> contenido = controlador.getContenido();
        lblMensaje.setText(admin.eliminar(seleccionado, contenido, seleccionado.getEditor()));
        actualizarLista();
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

    @FXML
    void publicarClk(ActionEvent event) {
        Content seleccionado = lvContenidoAdmin.getSelectionModel().getSelectedItem();
        Admin admin = (Admin) controlador.getUser();
        lblMensaje.setText(admin.publicar(seleccionado));
        actualizarLista();
    }

    private void actualizarLista() {
        lvContenidoAdmin.getItems().clear();
        lvContenidoAdmin.getItems().addAll(controlador.getContenido());
    }

    private void filtrarContenido() {
        String filtro = cbFiltro.getValue();
        lvContenidoAdmin.getItems().clear();
        for (Content c : controlador.getContenido()) {
            if (filtro.equals("Todos")) {
                lvContenidoAdmin.getItems().add(c);
            } else if (filtro.equals("Artículo") && c instanceof Article) {
                lvContenidoAdmin.getItems().add(c);
            } else if (filtro.equals("Imagen") && c instanceof Image) {
                lvContenidoAdmin.getItems().add(c);
            } else if (filtro.equals("Video") && c instanceof Video) {
                lvContenidoAdmin.getItems().add(c);
            }
        }
    }
}
