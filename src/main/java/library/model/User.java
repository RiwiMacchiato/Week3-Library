package library.model;

public class User {
    private long dni;
    private String name;

    public User(String name, long dni) {
        this.name = name;
        this.dni = dni;
    }

    public long getDNI() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isBlank()){
            this.name = name;
        }
    }

    public void setDNI(long DNI) {
        if(DNI > 0){
            this.dni = DNI;
        }
    }
}
