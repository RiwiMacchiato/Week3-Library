package library.model;

public class Magazine extends Item{
    private int magazine_id;
    private int issue_number;
    private String publisher;

    public Magazine(int id, String title, int publishedYear, int userId, int magazine_id, int issue_number, String publisher) {
        super(id, title, publishedYear, userId);
        this.magazine_id = magazine_id;
        this.issue_number = issue_number;
        this.publisher = publisher;
    }

    public int getMagazine_id() {
        return magazine_id;
    }

    public void setMagazine_id(int magazine_id) {
        if(magazine_id>0){
            this.magazine_id = magazine_id;
        }
    }

    public int getIssue_number() {
        return issue_number;
    }

    public void setIssue_number(int issue_number) {
        if(issue_number>0){
            this.issue_number = issue_number;
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if(publisher!=null && !publisher.isBlank()){
            this.publisher = publisher;
        }
    }

    @Override
    public String getType() {
        return "Magazine";
    }

}
