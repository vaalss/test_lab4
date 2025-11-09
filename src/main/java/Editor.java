import java.util.ArrayList;

public class Editor extends User implements IEditar, ICrear{
    
    private ArrayList<Content> contenidoEditor;

    public Editor(String correo, String password) {
        super(correo, password);
        this.contenidoEditor = new ArrayList<Content>();
    }

    @Override
    public String crear(String tipo, String nombre, String descripcion, ArrayList<Content> contenido) {
        Content c;
        String creado;
        switch (tipo) {
            case "Artículo":
                c = new Article(nombre, this, descripcion);
                contenido.add(c);
                contenidoEditor.add(c);
                creado = "Se ha creado un artículo titulado " + nombre;
                break;
        
            case "Imagen":
                c = new Image(nombre, this, descripcion);
                contenido.add(c);
                contenidoEditor.add(c);
                creado = "Se ha creado una imágen titulada " + nombre;
                break;
            
            case "Video":
                c = new Video(nombre, this, descripcion);
                contenido.add(c);
                contenidoEditor.add(c);
                creado = "Se ha creado un video titulado " + nombre;
                break;
            
            default:
                c = null;
                creado = "No se ha creado nada";
                break;
        }
        return creado;
    }

    @Override
    public String editar(Content c, String titulo, String descripcion) {
        String modificado;
        if (titulo.isEmpty() && descripcion.isEmpty()) {
            modificado = "Debes ingresar un cambio";
        } else {
            if (!titulo.isEmpty()) c.setNombre(titulo);
            if (!descripcion.isEmpty()) c.setDescripcion(descripcion);
            modificado = "Cambios guardados correctamente";
        }
        return modificado;
    }

    public ArrayList<Content> getContenido() {
        return this.contenidoEditor;
    }
}
