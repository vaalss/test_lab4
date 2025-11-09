public class Video  extends Content implements IReproducible, IDescargable{
    
    public Video(String nombre, User creador, String descripcion) {
        super(nombre, creador,descripcion);
    }

    @Override
    public String visualizar() {
        String visualizacion =  this.descargar() + ", " + this.reproducir();
        return visualizacion;
    }

    @Override
    public String descargar() {
        return "Es descargable";
    }

    @Override
    public String reproducir() {
        return "Es reproducible";
    }

    @Override
    public String toString() {
        return "Video" + " '" + this.nombre + "'" + " - " + this.creador.getCorreo() + " - " + this.descripcion + 
        " - " + (visible ? "Visible" : "Oculto") + " - " + this.vistas + " vistas" + " - " + visualizar(); 
    }
}
