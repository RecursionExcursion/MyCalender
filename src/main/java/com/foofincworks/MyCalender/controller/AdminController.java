package com.foofincworks.MyCalender.controller;

import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.entity.RSVP;
import com.foofincworks.MyCalender.persistence.EventIdPersistence;
import com.foofincworks.MyCalender.service.mail.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        List<Event> approvedEvents = new ArrayList<>();
        List<Event> unapprovedEvents = new ArrayList<>();

        //Assign all events to a list base on isApproved boolean
        eventService.getAll().forEach(event -> {
            if (event.isApproved()) {
                approvedEvents.add(event);
            } else {
                unapprovedEvents.add(event);
            }
        });

        model.addAttribute("events", approvedEvents);
        model.addAttribute("unapprovedEvents", unapprovedEvents);

        return "admin/admin-home";
    }


    // add event method to get rsvps
    @GetMapping("/showRSVPList")
    public String showRSVPList(@RequestParam("eventId") int id, Model model) {

        Event event = eventService.get(id);
        List<RSVP> rsvps = event.getRsvpList();

        model.addAttribute("event", event);
        model.addAttribute("rsvps", rsvps);

        return "admin/rsvp-list";
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") Event event) {

        if (event.getId() != 0) {
            eventService.update(event.getId(), event);
        } else {

            int id = EventIdPersistence.getInstance().getEventId();

            event.setId(id);

            eventService.save(event);
        }
        return "redirect:/admin/home";
    }

    @GetMapping("/approve")
    public String approveEvent(@RequestParam("eventId") int id) {
        Event updatedEvent = eventService.get(id);

        if (updatedEvent == null) {
            throw new RuntimeException("Event does not exist");
        }

        updatedEvent.setApproved(true);

        eventService.update(id, updatedEvent);

        return "redirect:/admin/home";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("eventId") int id, Model model) {

        Event event = eventService.get(id);

        model.addAttribute("event", event);

        return "event/event-form";
    }

    @GetMapping("/delete")
    public String deleteEvent(@RequestParam("eventId") int id) {

        Event eventToDelete = eventService.get(id);

        if (eventToDelete == null) {
            throw new RuntimeException("Event id does not exist");
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
        RSVP rSVPFromPage = new RSVP();
        rSVPFromPage.setFirstName(rsvpFirstName);
        rSVPFromPage.setLastName(rsvpLastName);
        rSVPFromPage.setEmail(rsvpEmail);

        //Get Event from ID
        Event event = eventService.get(eventID);

        //Compare RSVP from Event RSVP List to Faux RSVP,
        // If they are equal, delete the RSVP
        for (RSVP rsvp : event.getRsvpList()) {

            boolean rSVPsAreEqual =
                    rsvp.getFirstName().equals(rSVPFromPage.getFirstName()) &&
                            rsvp.getLastName().equals(rSVPFromPage.getLastName()) &&
                            rsvp.getEmail().equals(rSVPFromPage.getEmail());

            if (rSVPsAreEqual) {
                event.getRsvpList().remove(rsvp);
                break;
            }
        }

        //Save Event
        eventService.update(eventID, event);


        String returnParam = "?eventId=";

        return "redirect:/admin/showRSVPList" + returnParam + eventID;

    }
}
