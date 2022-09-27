package com.foofincworks.MyCalender.service.mail;

import com.foofincworks.MyCalender.dao.EventDao;
import com.foofincworks.MyCalender.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements ApplicationService<Event> {

    EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event get(long id) {
        return eventDao.get(id);
    }

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public void save(Event event) {
        eventDao.save(event);
    }

    @Override
    public void update(Event s, String[] params) {
        eventDao.update(s, params);
    }

    @Override
    public void delete(Event event) {
        eventDao.delete(event);
    }
}
