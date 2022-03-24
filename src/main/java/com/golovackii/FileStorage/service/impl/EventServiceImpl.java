
package com.golovackii.FileStorage.service.impl;

import com.golovackii.FileStorage.model.Event;
import com.golovackii.FileStorage.repository.EventRepository;
import com.golovackii.FileStorage.service.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository repository;

    @Override
    public void save(Event elem) {
        repository.save(elem);
    }

    @Override
    public void udate(Event elem) {
        repository.save(elem);
    }

    @Override
    public Event get(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
