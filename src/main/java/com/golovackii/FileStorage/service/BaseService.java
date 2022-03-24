package com.golovackii.FileStorage.service;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BaseService <T, ID> {

    public void save(T elem);
    
    public void udate(T elem);
    
    public T get(ID id);
    
    public List<T> getAll();
    
    public void delete(ID id);
}
