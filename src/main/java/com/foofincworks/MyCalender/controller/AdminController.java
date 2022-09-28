package com.foofincworks.MyCalender.controller;

import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.entity.RSVP;
import com.foofincworks.MyCalender.service.mail.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private EventService eventService;

    @Autowired
    public AdminController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/home")
    public String getAdmin(Model model) {
        List<Event> events = eventService.getAll();
        List<Event> approvedEvents = events.stream().filter(event -> event.isApproved()).toList();
        List<Event> unapprovedEvents = events.stream().filter(event -> !event.isApproved()).toList();

        model.addAttribute("events", approvedEvents);
        model.addAttribute("unapprovedEvents", unapprovedEvents);

        return "admin/admin-home";
    }


    // add event method to get rsvps
    @GetMapping("/showRSVPList")
    public String showRSVPList(@RequestParam("eventId") int id, Model model) {

        Event event = null;
        event = eventService.get(id);
        List<RSVP> rsvps = event.getRsvpList();

        model.addAttribute("event", event);
        model.addAttribute("rsvps", rsvps);

        return "admin/rsvp-list";
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") Event event) {
        eventService.save(event);
        return "redirect:/admin/home";
    }

    @GetMapping("/approve")
    public String approveEvent(@RequestParam("eventId") int id) {
        Event updatedEvent = null;

        for (Event e : eventService.getAll()) {
            if (e.getId() == id) {
                updatedEvent = e;
                break;
            }
        }

        if (updatedEvent == null) {
            throw new RuntimeException("Event does not exist");
        }

        updatedEvent.setApproved(true);

        eventService.update(id, updatedEvent);

        return "redirect:/admin/home";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("eventId") int id, Model model) {
        //form ref

        Event event = eventService.get(id);

        model.addAttribute("event", event);


        return "event/event-form";
    }

    @GetMapping("/delete")
    public String deleteEvent(@RequestParam("eventId") int id) {

        Event eventToDelete = null;

        for (Event e : eventService.getAll()) {
            if (e.getId() == id) {
                eventToDelete = e;
                break;
            }
        }

        if (eventToDelete == null) {
            throw new RuntimeException("Event does not exist");
        }

        eventService.delete(eventToDelete);

        return "redirect:/admin/home";

    }

    @GetMapping("/delete/rsvp")
    public String deleteRSVP(@RequestParam Map<String, String> allParams) {

        //Get all values from Map
        int eventID = Integer.parseInt(allParams.get("eventId"));
        String rsvpFirstName = allParams.get("rsvpFirstName");
        String rsvpLastName = allParams.get("rsvpLastName");
        String rsvpEmail = allParams.get("rsvpEmail");

        //Create faux RSVP
        RSVP rsvpToRemove = new RSVP();
        rsvpToRemove.setFirstName(rsvpFirstName);
        rsvpToRemove.setLastName(rsvpLastName);
        rsvpToRemove.setEmail(rsvpEmail);

        //Get Event from ID
        Event event = eventService.get(eventID);

        //Compare RSVP from Event RSVP List to Faux RSVP,
        // If they are equal, delete the RSVP
        for (RSVP rsvp : event.getRsvpList()) {
            if (rsvp.getFirstName().equals(rsvpToRemove.getFirstName()) &&
                    rsvp.getLastName().equals(rsvpToRemove.getLastName()) &&
                    rsvp.getEmail().equals(rsvpToRemove.getEmail())) {
                event.getRsvpList().remove(rsvp);
                break;
            }
        }

        //Save Event
        eventService.update(eventID, event);

        return "redirect:/admin/home";

    }
}
