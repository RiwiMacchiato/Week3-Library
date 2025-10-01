package library.model;

import java.time.LocalDate;

public class Loan {
    private int id;
    private User user;
    private Item item;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(int id, User user, Item item, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
