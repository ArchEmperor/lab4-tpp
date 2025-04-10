package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name="users")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name="session_id")
    UUID sessionId;
    @JsonIgnore
    String username;
    @JsonIgnore
    String password;
    @JsonIgnore
    Roles role=Roles.User;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

}
