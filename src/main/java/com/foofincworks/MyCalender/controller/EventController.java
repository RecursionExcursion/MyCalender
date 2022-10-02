package com.foofincworks.MyCalender.controller;

import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.entity.RSVP;
import com.foofincworks.MyCalender.persistence.EventIdPersistence;
import com.foofincworks.MyCalender.persistence.settings.SettingsController;
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

    private final EmailSenderService emailSenderService;
    private final EventService eventService;

    @Autowired
    public EventController(EmailSenderService emailSenderService, EventService eventService) {
        this.emailSenderService = emailSenderService;
        this.eventService = eventService;
    }


    @GetMapping("/list")
    public String listEvents(Model model) {

        List<Event> approvedEvents = eventService.getAll().stream().filter(Event::isApproved).toList();

        model.addAttribute("events", approvedEvents);

//        if statement for admin redirect?

        return "event/list-events";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Event newEvent = new Event();

        model.addAttribute("event", newEvent);

        return "event/event-form";
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") Event event) {

        boolean emailNotificationsEnabled = SettingsController.getInstance().getSettings().enableEmailNotifications;

        if (emailNotificationsEnabled) {
            String toEmail = SettingsController.getInstance().getSettings().emailAddress;

            String subject = "New event submission";

            String body = "Event name- " + event.getEventName() +
                    "\n Event Date- " + event.getEventDate() +
                    "\n Event Location- " + event.getEventLocation() +
                    "\n Event Start Time- " + event.getStartTime() +
                    "\n Event End Time- " + event.getEndTime() +
                    "\n Event Description- " + event.getEventDescription();


            emailSenderService.sendSimpleEmail(toEmail, body, subject);
        }

        int id = EventIdPersistence.getInstance().getEventId();

        event.setId(id);

        eventService.save(event);

        return "redirect:/events/list";
    }

    @GetMapping("/showFormForRSVP")
    public String showFormForRSVP(@RequestParam("eventId") int id, Model model) {

        Event event = eventService.get(id);

        model.addAttribute("event", event);
        model.addAttribute("rsvp", new RSVP());

        return "event/rsvp-form";
    }

    @PostMapping("/rsvp")
    public String saveRSVP(@RequestParam("eventId") int id,
                           @ModelAttribute("rsvp") RSVP rsvp) {

        Event eventToAddRSVP = eventService.get(id);

        eventToAddRSVP.getRsvpList().add(rsvp);

        //Save changes to json or DB
        eventService.update(id, eventToAddRSVP);

        return "redirect:/events/list";
    }
}
