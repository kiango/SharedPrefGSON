package com.example.torsh.sharedprefgson;

import java.util.List;

/**
 * Created by torsh on 3/23/17.
 */

class Employee {

    private String name;
    private String profession;
    private Integer profId;
    private List<String> roles;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getProfession() {
        return profession;
    }

    void setProfession(String profession) {
        this.profession = profession;
    }

    Integer getProfId() {
        return profId;
    }

    void setProfId(Integer profId) {
        this.profId = profId;
    }

    List<String> getRoles() {
        return roles;
    }

    void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
