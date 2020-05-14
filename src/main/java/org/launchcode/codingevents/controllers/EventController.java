package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("tittle", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("tittle", "Create Event");
        model.addAttribute("event", new Event());
        return"events/create";
    }

    @PostMapping("create")
//    @RequestMapping(value="create",method = { RequestMethod.GET, RequestMethod.POST })
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {

        if(errors.hasErrors()) {
//            model.addAttribute("event", new Event());
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("tittle", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return("events/delete");
    }

    //@PostMapping("delete") --work in progress

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = EventData.getById(eventId);
        String title = "Edit Event " + eventToEdit.getName() + "(id=" + eventToEdit.getId() + ")";
        model.addAttribute("tittle", title);
        model.addAttribute("event", eventToEdit);
        return("events/edit");
    }
    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        EventData.getById(eventId).setName(name);
        EventData.getById(eventId).setDescription(description);
        return "redirect:";
    }

}
