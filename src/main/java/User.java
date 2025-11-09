public abstract class User {
    protected String correo;
    protected String password;

    public User(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public String getCorreo() {
        return this.correo;
    }

    public boolean verificar(String intento) {
        return this.password.equals(intento);
    }
}
