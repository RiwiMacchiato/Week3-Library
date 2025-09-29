package library.model;

public abstract class Item {
    private int id;
    private String title;
    private String author;
    private Boolean available = true;

    public Item(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setTitle(String title) {
        if(title != null && !title.isBlank()){
            this.title = title;
        }

    }

    public void setAuthor(String autor) {
        if(autor != null && !autor.isBlank()){
            this.author = autor;
        }
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " [id=" + id + ", title=" + title + ", author=" + author + ", available=" + available + "]";
    }

}
