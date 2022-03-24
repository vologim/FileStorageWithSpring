
package com.golovackii.FileStorage.service.impl;

import com.golovackii.FileStorage.model.FileUser;
import com.golovackii.FileStorage.repository.FileUserRepository;
import com.golovackii.FileStorage.service.FileUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUserServiceImpl implements FileUserService{

    @Autowired
    private FileUserRepository repository;
    
    @Override
    public void save(FileUser elem) {
        repository.save(elem);
    }

    @Override
    public void udate(FileUser elem) {
        repository.save(elem);
    }

    @Override
    public FileUser get(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<FileUser> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
