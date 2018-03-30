package ru.csu.iit.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetRowModel {

    @JsonProperty(value = "global_id")
    private int id;
    @JsonProperty(value = "Number")
    private int number;
    @JsonProperty(value = "Cells")
    private DatasetCell cells;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DatasetCell getCells() {
        return cells;
    }

    public void setCells(DatasetCell cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "DatasetRowModel{" +
                " global_id=" + id +
                ", Number='" + number + '\'' +
                ", Cells={ 'CommonName' = '" + cells.getName()
                + "' , 'Location' ='" + cells.getLocation()
                + "'\n}}";
    }
}



