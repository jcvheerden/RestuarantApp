package com.example.tastehaven_application;

public class User {
    private String user_id;
    private String name;
    private String email;
    private String password_hash;
    private String role;
    private long created_at;

    public User() {}

    public User(String user_id, String name, String email, String password_hash, String role, long created_at) {
        this.setUser_id(user_id);
        this.setName(name);
        this.setEmail(email);
        this.setPassword_hash(password_hash);
        this.setRole(role);
        this.setCreated_at(created_at);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
