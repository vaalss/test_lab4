public class Image extends Content implements IDescargable{
    
    public Image(String nombre, User creador, String descripcion) {
        super(nombre, creador,descripcion);
    }

    
    @Override
    public String visualizar() {
        String visualizacion = this.descargar();
        return visualizacion;
    }

    @Override
    public String descargar() {
        return "Es descargable";
    }

    @Override
    public String toString() {
        return "Imagen" + " '" + this.nombre + "'" + " - " + this.creador.getCorreo() + " - " + this.descripcion + 
        " - " + (visible ? "Visible" : "Oculto") + " - " + this.vistas + " vistas" + " - " + visualizar(); 
    }

}
