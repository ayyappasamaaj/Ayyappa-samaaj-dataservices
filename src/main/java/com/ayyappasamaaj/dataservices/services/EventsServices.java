package com.ayyappasamaaj.dataservices.services;

import com.ayyappasamaaj.dataservices.domain.Events;
import com.ayyappasamaaj.dataservices.domain.EventsResponse;
import com.ayyappasamaaj.dataservices.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssurya on 8/10/17.
 */
@Service
public class EventsServices {

    @Autowired
    private EventsRepository eventsRepository;

    public EventsResponse getAllEvents () {

        EventsResponse eventsResponse = new EventsResponse();

        LocalDate localDate = LocalDate.now();
        Timestamp currentDate = Timestamp.valueOf(localDate.atStartOfDay());

        List<Events> events = eventsRepository.findAllEvents(currentDate);

        if (events != null && events.size() > 0) {
            eventsResponse.setStatus(EventsResponse.ResponseCode.SUCCESS);
            eventsResponse.setMessage("");
            eventsResponse.setEvents(events);
        } else {
            eventsResponse.setStatus(EventsResponse.ResponseCode.ERROR);
            eventsResponse.setMessage("No events found !!!");
            eventsResponse.setEvents(new ArrayList<>());
        }

        return eventsResponse;
    }

    public EventsResponse saveEvent (Events event) {
        EventsResponse eventsResponse = new EventsResponse();
        List<Events> events = new ArrayList<>();

        try {
            event = eventsRepository.save(event);
            events.add(event);

            if (events != null && events.size() > 0) {
                eventsResponse.setStatus(EventsResponse.ResponseCode.SUCCESS);
                eventsResponse.setMessage("");
                eventsResponse.setEvents(events);
            } else {
                eventsResponse.setStatus(EventsResponse.ResponseCode.ERROR);
                eventsResponse.setMessage("Something bad happened");
                eventsResponse.setEvents(events);
            }
        } catch (DataAccessException e) {
            eventsResponse.setStatus(EventsResponse.ResponseCode.ERROR);
            eventsResponse.setMessage("Something bad happened");
            eventsResponse.setEvents(events);
        }

        return eventsResponse;
    }
}
