package library.model;

public class Usuario {
    private long cedula;
    private String nombre;

    public Usuario(String nombre, long cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre != null && !nombre.isBlank()){
            this.nombre = nombre;
        }
    }

    public void setCedula(long cedula) {
        if(cedula > 0){
            this.cedula = cedula;
        }
    }
}
