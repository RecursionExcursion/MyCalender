package com.foofincworks.MyCalender.controller;

import com.foofincworks.MyCalender.entity.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {


    private List<Event> events;

    //For testing w/o DB set up
    @PostConstruct
    private void createEvents() {
        Event e1 = new Event(1, "9/22/22",
                             "Grand Rapids",
                             "12:30",
                             "14:30",
                             "Tech-Meet Up",
                             "Come meet us at sloths house!");

        Event e2 = new Event(2, "9/30/22",
                             "Grand Rapids",
                             "13:30",
                             "14:30",
                             "Tech-Booze",
                             "Come meet us at sloths fav bar!");

        Event e3 = new Event(3, "10/22/22",
                             "Grand Rapids",
                             "15:30",
                             "17:30",
                             "Tech-Lunch",
                             "Come meet us at sloths fav brunch spot!");

        events = new ArrayList<>();

        events.add(e1);
        events.add(e2);
        events.add(e3);
    }


    @GetMapping("/list")
    public String listEvents(Model model) {
        model.addAttribute("events", events);
        return "list-events";
    }

    @GetMapping("showFormForAdd")
    public String showFormForAdd(Model model) {

        Event newEvent = new Event();

        model.addAttribute("event", newEvent);

        return "event-form";
    }

}
