package library.model;

import java.time.LocalDate;

public class Loan {
    private int id;
    private User user;
    private Item item;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(int id, User user, Item item, LocalDate loanDate) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.loanDate = loanDate;
        this.returnDate = null;
        item.setAvailable(false);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public void setUser(User user) {
        if(user != null){
            this.user = user;
        }
    }

    public void setItem(Item item) {
        if(item != null){
            this.item = item;
        }
    }

    public void setLoanDate(LocalDate loanDate) {
        if(loanDate != null){
            this.loanDate = loanDate;
        }
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void returnItem(){
        returnDate = LocalDate.now();
        item.setAvailable(true);
    }
}
