package library.model;

public class Libro extends Item{
    private String genero;

    public Libro(int id, String titulo, String autor, String genero) {
        super(id, titulo, autor);
        this.genero = genero;
    }


    @Override
    public String getTipo() {
        return "Libro";
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if(genero != null && !genero.isBlank()){
            this.genero = genero;
        }
    }
}
