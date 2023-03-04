package com.projects.eventsapp.errorHandling;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "error")
public class ValidationError {

    private String msg;
    private List<String> details;

    public ValidationError( String msg, List<String> details) {
        super();
        this.msg = msg;
        this.details = details;
    }

    // Setters
    public void setDetails (List<String> details) {
        this.details = details;
    }

    public void setMsg (String msg) {
        this.msg = msg;
    }

    // Getters
    public List<String> getDetails () {
        return details;
    }

    public String getMsg () {
        return msg;
    }
}
