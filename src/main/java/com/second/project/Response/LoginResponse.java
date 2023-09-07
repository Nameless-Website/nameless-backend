package com.second.project.Response;
import java.util.Objects;

public class LoginResponse {
    String message;
    boolean response;

    public LoginResponse(String message, boolean b) {
        this.message = message;
        this.response = b;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getResponse() {
        return this.response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }




   
    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            ", response='" + getResponse() + "'" +
            "}";
    }

    public LoginResponse() {
    }

}
