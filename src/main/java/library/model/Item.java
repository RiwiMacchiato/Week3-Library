package library.model;

public abstract class Item {
    private int id;
    private String title;
    private int publishedYear;
    private int userId;

    public Item(int id, String title, int publishedYear, int userId) {
        this.id = id;
        this.title = title;
        this.publishedYear = publishedYear;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null && !title.isBlank()){
            this.title = title;
        }

    }


    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }


    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        if(publishedYear >0){
            this.publishedYear = publishedYear;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        if(userId > 0){
            this.userId = userId;
        }
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishedYear=" + publishedYear +
                ", userId=" + userId +
                '}';
    }
}
