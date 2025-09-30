package library.dao;

import java.util.List;

public interface Repository<T>{
    void save(T t);
    void update(T t);
    T findById(int id);
    List<T> findAll();
}
