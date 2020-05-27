package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class EventCategory extends AbstractEntity{

    @NotBlank(message = "Category name is required.")
    @Size(min = 3, max = 25, message = "Category name must be between 3 and 25 characters")
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }
    public EventCategory(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
