package com.example.shoppeerw59.modal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",length = 50,unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "password",length = 50,unique = true)
    private String password;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "full_name",length = 50)
    private String fullName;

    @Column(name = "phone_number",length = 12)
    private String phoneNumber;

    @Column(name = "email",length = 50)
    private String email;

    @Column(name = "facebook",length = 50)
    private String facebook;

    @Column(name = "information",length = 255)
    private String information;
}