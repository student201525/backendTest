package ru.csu.iit.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetCell
{
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(value = "Location")
    private String location;



    @JsonProperty(value = "Name")
    private String name;

}