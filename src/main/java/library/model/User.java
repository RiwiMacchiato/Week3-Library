package library.model;

public class User {
    private int id;
    private String name;
    private String email;

    public User(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isBlank()){
            this.name = name;
        }
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email != null && !email.isBlank()){
            this.email = email;
        }
    }
}
