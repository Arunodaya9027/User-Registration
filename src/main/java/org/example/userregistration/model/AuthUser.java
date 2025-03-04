package org.example.userregistration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthUser {
    @Id
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
