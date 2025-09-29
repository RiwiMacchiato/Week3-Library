package library.model;

public class Revista extends Item{
    private int edicion;

    public Revista(int id, String titulo, String autor, int edicion) {
        super(id, titulo, autor);
        this.edicion = edicion;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        if(edicion > 0){
            this.edicion = edicion;
        }
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

}
