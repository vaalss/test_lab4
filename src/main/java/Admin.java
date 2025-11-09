import java.util.ArrayList;

public class Admin extends User implements IPublicar, IEliminar{

    public Admin(String correo, String password) {
        super(correo, password);
    }

    @Override
    public String eliminar(Content seleccionado, ArrayList<Content> contenido, User editor) {
        String eliminar;
        Editor creador = (Editor) editor;
        if (seleccionado == null) {
            eliminar = "Debes seleccionar un contenido para eliminar";
        } else {
            contenido.remove(seleccionado);
            creador.getContenido().remove(seleccionado);
            eliminar = "Se ha eliminado " + seleccionado.getNombre() + " correctamente";
        }
        return eliminar;
    }

    @Override
    public String publicar(Content seleccionado) {
        String publicar;
        if (seleccionado == null) {
            publicar = "Debes seleccionar un contenido para publicar";
        } else {
            if (!seleccionado.getVisible()) {
                seleccionado.setVisible(true);
                 publicar = "Se ha publicado " + seleccionado.getNombre() + " correctamente";
            } else {
                publicar = seleccionado.getNombre() + " ya se encuentra publicado";
            }
        }
        return publicar;
    }
}
