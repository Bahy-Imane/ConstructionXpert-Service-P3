package com.user.model;

import com.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends User {


    public Admin() {
        this.setRole(Role.ADMIN);
    }

}
