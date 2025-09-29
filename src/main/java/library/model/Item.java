package library.model;

public abstract class Item {
    private int id;
    private String titulo;
    private String autor;
    private Boolean disponible = true;

    public Item(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setTitulo(String titulo) {
        if(titulo != null && !titulo.isBlank()){
            this.titulo = titulo;
        }

    }

    public void setAutor(String autor) {
        if(autor != null && !autor.isBlank()){
            this.autor = autor;
        }
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", disponible=" + disponible + "]";
    }

}
