package com.example.restclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class Role implements GrantedAuthority {

    private Long id;
    private String role;
    @JsonIgnore
    private Set<User> users;

    public Role() {
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Role) {
            Role role1 = (Role) obj;
            return role.equals(role1.role) &&
                    id.equals(role1.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        return result * 31 + role.hashCode();
    }

    @Override
    public String toString() {
        return role;
    }


    @Override
    public String getAuthority() {
        return role;
    }
}
