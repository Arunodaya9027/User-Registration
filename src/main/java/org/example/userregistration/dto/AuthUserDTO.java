package org.example.userregistration.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required. Write '.' if you don't have a last name")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Password must contain at least one uppercase letter, one number, and one special character")
    private String password;
}
