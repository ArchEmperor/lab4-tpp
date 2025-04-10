package org.example.repositories;

import java.util.List;

public interface IBaseRepository<T> {
    List<T> GetAll ();
    void Add(T o);
    void Update(T o);
    void Remove(Integer id);
}
