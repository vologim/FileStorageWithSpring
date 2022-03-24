
package com.golovackii.FileStorage.service.impl;

import com.golovackii.FileStorage.model.FileUser;
import com.golovackii.FileStorage.repository.FileUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class FileUserServiceImplTest {
    
    @Mock
    private FileUserRepository repository;
    
    @InjectMocks
    private FileUserServiceImpl service;
    
    public FileUserServiceImplTest() {
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
        Mockito.when(repository.getById(1l)).thenReturn(getFileUser());
        
        FileUser fileUser = service.get(1l);
        
        assertEquals(getFileUser().getId(), fileUser.getId());
        assertEquals(getFileUser().getFileLocation(), fileUser.getFileLocation());
    }

    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(getFileUsers());
        
        List<FileUser> fileUsers = service.getAll();
        
        assertEquals(getFileUsers().size(), fileUsers.size());
        assertEquals(getFileUsers().get(0).getFileLocation(), fileUsers.get(0).getFileLocation());
        assertEquals(getFileUsers().get(2).getId(), fileUsers.get(2).getId());
    }

    @Test
    public void testDelete() {
        service.delete(1l);
        
        verify(repository).deleteById(1l);
    }
    
    private List<FileUser> getFileUsers() {
        List<FileUser> fileUsers = new ArrayList<>();
        
        FileUser fileUser = new FileUser("File", "Location");
        
        for (long i = 0; i < 3; i++) {
            fileUser.setId(i);
            fileUsers.add(fileUser);
        }
        return fileUsers;
    }
    
    private FileUser getFileUser() {
        FileUser fileUser = new FileUser("File", "Location");
        fileUser.setId(1l);
        return fileUser;
    }
}
