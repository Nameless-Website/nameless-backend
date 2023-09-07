package com.second.project.Dto;

public class UserDto {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String role="user";

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto() {
    }

    public UserDto(String userId, String username, String email, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", role='" + getRole()+"'"+
            ", password='" + getPassword() + "'" +
            "}";
    }

}
