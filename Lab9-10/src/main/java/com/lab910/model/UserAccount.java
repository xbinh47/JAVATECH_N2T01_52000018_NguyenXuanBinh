package com.lab910.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UserAccounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

}
