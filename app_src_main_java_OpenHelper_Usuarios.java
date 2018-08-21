package OpenHelper;

public class Usuarios {
    private int idusuario;
    private String nombre,ciudad,contraseña,correo;
    public Usuarios(int idusuario,String nombre,String ciudad,String correo,String contraseña){
        setIdusuario(idusuario);
        setNombre(nombre);
        setCiudad(ciudad);
        setCorreo(correo);
        setContraseña(contraseña);
    }



    public String getCiudad() {
        return ciudad;
    }

    private void setCiudad(String ciudad) {
        this.ciudad=ciudad;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
