
package com.golovackii.FileStorage.service.impl;

import com.golovackii.FileStorage.model.Event;
import com.golovackii.FileStorage.model.FileUser;
import com.golovackii.FileStorage.model.User;
import com.golovackii.FileStorage.repository.EventRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class EventServiceImplTest {

    @Mock
    private EventRepository repository;

    @InjectMocks
    private EventServiceImpl service;

    public EventServiceImplTest() {
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
        Mockito.when(repository.getById(1l)).thenReturn(getEvent());
        
        Event event = service.get(1l);
        
        assertEquals(getEvent().getId(), event.getId());
        assertEquals(getEvent().getLog(), event.getLog());
    }

    @Test
    public void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(getEvents());
        
        List<Event> events = service.getAll();
        
        assertEquals(getEvents().size(), events.size());
        assertEquals(getEvents().get(0).getLog(), events.get(0).getLog());
        assertEquals(getEvents().get(2).getId(), events.get(2).getId());
    }

    @Test
    public void testDelete() {
        service.delete(1l);
        
        verify(repository).deleteById(1l);
    }
    
    private List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        
        Event event = new Event("Test", new User(), new FileUser());
        
        for (long i = 0; i < 3; i++){
            event.setId(i);
            events.add(event);
        }
        
        return events;
    }
    
    private Event getEvent() {
        Event event = new Event("Test", new User(), new FileUser());
        event.setId(1l);
        return event;
    }
    
}
