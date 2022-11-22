package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @Column
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 3, max = 25, message = "Password must be between 3 and 25 characters")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "Username cannot be not blank")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    private String username;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
