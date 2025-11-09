import java.util.ArrayList;

public class Controller {
    private User usuarioActual;
    private ArrayList<User> usuarios;
    private ArrayList<Content> contenido;

    public Controller() {
        this.usuarioActual = null;
        this.contenido = new ArrayList<Content>();
        this.usuarios = new ArrayList<User>();
    }

    public boolean ingresar(String correo, String password) {
        for (User u : usuarios) {
            if (u.getCorreo().equals(correo) && u.verificar(password)) {
                this.usuarioActual = u;
                return true;
            }
        }
        return false;
    }

    public User getUser() {
        return this.usuarioActual;
    }

    public void setUser(User u) {
        this.usuarioActual = u;
    }

    public ArrayList<Content> getContenido() {
        return this.contenido;
    }

    public ArrayList<User> getUsuarios() {
        return this.usuarios;
    }

}
