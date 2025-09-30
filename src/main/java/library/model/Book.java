package library.model;

public class Book extends Item{
    private int book_id;
    private String author;
    private String isbn;
    private int pages;

    public Book(int id, String title, int publishedYear, int userId, int book_id, String author, String isbn, int pages) {
        super(id, title, publishedYear, userId);
        this.book_id = book_id;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
    }



    @Override
    public String getType() {
        return "Book";
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        if(book_id>0){
            this.book_id = book_id;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author!=null && !author.isBlank()){
            this.author = author;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(isbn!=null && !isbn.isBlank()){
            this.isbn = isbn;
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if(pages>0){
            this.pages = pages;
        }
    }
}
