package library.dao;

import library.model.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    void update(User user);
    User findById(int id);
    List<User> findAll();
}
