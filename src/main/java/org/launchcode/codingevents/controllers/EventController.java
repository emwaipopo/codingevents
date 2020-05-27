package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return"events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return("events/delete");
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for(int id : eventIds){
                eventRepository.deleteById(id);
            }
            return "redirect:";
        }
        return "events/delete";
    }

    @GetMapping("delete/{eventId}")
    public String deleteSingleEvent(Model model, @PathVariable int eventId) {
        eventRepository.deleteById(eventId);
        return("redirect:");
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = eventRepository.findById(eventId).get();
        String title = "Edit Event: " + eventToEdit.getName();
        model.addAttribute("title", title);
        model.addAttribute("event", eventToEdit);

        return("events/edit");
    }
    @PostMapping("edit")
    public String processEditForm(Model model, @Valid Errors errors,int eventId, String name, String description) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Edit Events");
            return "events/edit";
        }
        Event eventToEdit = eventRepository.findById(eventId).get();
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        eventRepository.save(eventToEdit);
        return "redirect:";
    }


}
