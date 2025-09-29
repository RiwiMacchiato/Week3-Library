package library.model;

public class Magazine extends Item{
    private int edition;

    public Magazine(int id, String title, String author, int edition) {
        super(id, title, author);
        this.edition = edition;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        if(edition > 0){
            this.edition = edition;
        }
    }

    @Override
    public String getType() {
        return "Magazine";
    }

}
