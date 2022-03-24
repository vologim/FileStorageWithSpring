
package com.golovackii.FileStorage.service.impl;

import com.golovackii.FileStorage.model.User;
import com.golovackii.FileStorage.repository.UserRepository;
import com.golovackii.FileStorage.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;
    
    @Override
    public void save(User elem) {
        repository.save(elem);
    }

    @Override
    public void udate(User elem) {
        repository.save(elem);
    }

    @Override
    public User get(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
