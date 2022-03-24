
package com.golovackii.FileStorage.service.impl;

import com.golovackii.FileStorage.model.Event;
import com.golovackii.FileStorage.model.Role;
import com.golovackii.FileStorage.model.User;
import com.golovackii.FileStorage.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
    
    @Mock
    private UserRepository repository;
    
    @InjectMocks
    private UserServiceImpl service;
    
    public UserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        service.save(Mockito.any());
        
        verify(repository).save(Mockito.any());
    }

    @Test
    public void testUdate() {
        service.udate(Mockito.any());
        
        verify(repository).save(Mockito.any());
    }

    @Test
    public void testGet() {
        Mockito.when(repository.getById(1l)).thenReturn(getUser());
        
        User user = service.get(1l);
        
        assertEquals(getUser().getId(), user.getId());
        assertEquals(getUser().getPassword(), user.getPassword());
    }

    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(getUsers());

        List<User> users = service.getAll();

        assertEquals(getUsers().size(), users.size());
        assertEquals(getUsers().get(0).getPassword(), users.get(0).getPassword());
        assertEquals(getUsers().get(2).getId(), users.get(2).getId());
    }

    @Test
    public void testDelete() {
        service.delete(Mockito.anyLong());
        
        verify(repository).deleteById(Mockito.anyLong());
    }
    
    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        
        User user = new User("Test", Role.USER, new ArrayList<>());
        
        for (long i = 0; i < 3; i++) {
            user.setId(i);
            users.add(user);
        }
        return users;
    }
    
    private User getUser() {
        User user = new User("Test", Role.USER, new ArrayList<>());
        user.setId(1l);
        return user;
    }
}
