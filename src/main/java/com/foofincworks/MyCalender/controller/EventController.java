package com.foofincworks.MyCalender.controller;

import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.service.mail.EmailSenderService;
import com.foofincworks.MyCalender.service.mail.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private EmailSenderService emailSenderService;
    private EventService eventService;

    @Autowired
    public EventController(EmailSenderService emailSenderService, EventService eventService) {
        this.emailSenderService = emailSenderService;
        this.eventService = eventService;
    }


    @GetMapping("/list")
    public String listEvents(Model model) {
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
        return "event/list-events";
    }

    @GetMapping("showFormForAdd")
    public String showFormForAdd(Model model) {

        Event newEvent = new Event();

        model.addAttribute("event", newEvent);

        return "event/event-form";
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") Event event) {

        String toEmail = "cklawieter@gmail.com";

        String subject = "New event submission";

        String body = "Event name- " + event.getEventName() +
                "\n Event Date- " + event.getEventDate() +
                "\n Event Location- " + event.getEventLocation() +
                "\n Event Start Time- " + event.getStartTime() +
                "\n Event End Time- " + event.getEndTime() +
                "\n Event Description- " + event.getEventDescription();


        emailSenderService.sendSimpleEmail(toEmail, body, subject);

        return "redirect:event/events/list";
    }

    @GetMapping("/showFormForRSVP")
    public String showFormForRSVP(@RequestParam("eventId") int id, Model model) {

        Event event = null;

        List<Event> events = eventService.getAll();

        for (Event e : events) {
            if (e.getId() == id) {
                event = e;
            }
        }

        model.addAttribute("event", event);

        return "event/rsvp-form";

    }


}
