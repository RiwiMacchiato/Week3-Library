package library.dao;

import library.model.Book;

import java.util.List;

public interface BookDAO {
    void save(Book book);
    void update(Book book);
    Book findById(int id);
    List<Book> findAll();
}
