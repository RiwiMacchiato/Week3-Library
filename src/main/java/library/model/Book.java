package library.model;

public class Book extends Item{
    private String genre;

    public Book(int id, String title, String author, String genre) {
        super(id, title, author);
        this.genre = genre;
    }


    @Override
    public String getType() {
        return "Book";
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if(genre != null && !genre.isBlank()){
            this.genre = genre;
        }
    }
}
