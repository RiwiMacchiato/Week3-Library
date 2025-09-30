package library.dao;

import library.model.Magazine;

import java.util.List;

public interface MagazineDAO {
    void save(Magazine magazine);
    void update(Magazine magazine);
    Magazine findById(int id);
    List<Magazine> findAll();
}
