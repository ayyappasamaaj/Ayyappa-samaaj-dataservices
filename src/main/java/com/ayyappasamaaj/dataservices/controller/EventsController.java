package com.ayyappasamaaj.dataservices.controller;

import com.ayyappasamaaj.dataservices.domain.Events;
import com.ayyappasamaaj.dataservices.domain.EventsResponse;
import com.ayyappasamaaj.dataservices.services.EventsServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ssurya on 8/10/17.
 */
@Controller
@Slf4j
public class EventsController {

    @Autowired
    private EventsServices eventsServices;

    @CrossOrigin
    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public @ResponseBody EventsResponse getEbookData() {
        return eventsServices.getAllEvents();
    }

    @CrossOrigin
    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EventsResponse saveEbookData(@RequestBody Events events) {
        return eventsServices.saveEvent(events);
    }

}
