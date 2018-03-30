package ru.csu.iit.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetCell
{
    public String getHelpPhone_en() {
        return helpPhone_en;
    }

    public void setHelpPhone_en(String helpPhone_en) {
        this.helpPhone_en = helpPhone_en;
    }

    public String getEmail_en() {
        return email_en;
    }

    public void setEmail_en(String email_en) {
        this.email_en = email_en;
    }

    public String getWebSite_en() {
        return webSite_en;
    }

    public void setWebSite_en(String webSite_en) {
        this.webSite_en = webSite_en;
    }

    @JsonProperty(value = "HelpPhone_en")
    private String helpPhone_en;


    @JsonProperty(value = "Email_en")
    private String email_en;



    @JsonProperty(value = "WebSite_en")
    private String webSite_en;

}