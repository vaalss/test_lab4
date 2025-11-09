import java.util.Random;

public abstract class Content {
    protected String nombre;
    protected User creador;
    protected int vistas;
    protected String descripcion;
    protected boolean visible;

    public Content(String nombre, User creador, String descripcion) {
        this.nombre = nombre;
        this.creador = creador;
        this.vistas = new Random().nextInt(5000);
        this.descripcion = descripcion;
        this.visible = false;
    }

    public abstract String visualizar();

    public boolean getVisible() {
        return this.visible;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescrpicion() {
        return this.descripcion;
    }

    public User getEditor() {
        return this.creador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
