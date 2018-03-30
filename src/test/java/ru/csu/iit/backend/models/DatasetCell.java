package ru.csu.iit.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetCell
{

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @JsonProperty(value = "CommonName")
    private String commonName;

}