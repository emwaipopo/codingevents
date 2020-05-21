package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("tittle", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("tittle", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("types", EventType.values());
        return"events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("event", new Event());
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("tittle", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return("events/delete");
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for(int id : eventIds){
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId) {
//        Event eventToEdit = EventData.getById(eventId);
//        String title = "Edit Event " + eventToEdit.getName() + "(id=" + eventToEdit.getId() + ")";
//        model.addAttribute("tittle", title);
//        model.addAttribute("event", eventToEdit);
//        Event eventToEdit1 = eventRepository.findById(eventId);
//        return("events/edit");
//    }
//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String description) {
//        EventData.getById(eventId).setName(name);
//        EventData.getById(eventId).setDescription(description);
//        return "redirect:";
//    }


}
