package com.foofincworks.MyCalender.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foofincworks.MyCalender.entity.Event;
import com.foofincworks.MyCalender.service.mail.EventService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private EventService eventService;

    @Autowired
    public AdminController(EventService eventService){
        this.eventService = eventService;
    }


    @GetMapping("/home")
    public String getAdmin(Model model){
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
        return "admin/admin-home";
    }
}
