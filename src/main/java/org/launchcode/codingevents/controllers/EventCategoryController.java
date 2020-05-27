package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEventCategories(Model model){
        model.addAttribute("title", "All Categories");
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute(new EventCategory());
        model.addAttribute("title", "Create Category");
        return"eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventCategoryForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return("eventCategories/delete");
    }

    @PostMapping("delete")
    public String processDeleteEventCategoriesForm(@RequestParam(required = false) int[] eventCategoryIds){
        if(eventCategoryIds != null){
            for(int id : eventCategoryIds){
                eventCategoryRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditEventCategoryForm(Model model, @PathVariable int eventCategoryId) {
        EventCategory eventCategoryToEdit = eventCategoryRepository.findById(eventCategoryId).get();
        String title = "Edit Category: " + eventCategoryToEdit.getName();
        model.addAttribute("title", title);
        model.addAttribute("eventCategory", eventCategoryToEdit);

        return("eventCategories/edit");
    }
    @PostMapping("edit")
    public String processEditEventCategoryForm(int eventId, String name) {
        EventCategory eventCategoryToEdit = eventCategoryRepository.findById(eventId).get();
        eventCategoryToEdit.setName(name);
        eventCategoryRepository.save(eventCategoryToEdit);
        return "redirect:";
    }
}
