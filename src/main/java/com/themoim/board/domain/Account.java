package com.themoim.board.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Table(name = "account")
@Entity
public class Account {
    @Column(name = "a_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aId;

    @NotEmpty
    @Column(name = "user_name")
    private String userName;

    @NotEmpty
    @Column(name = "user_id")
    private String userId;

    @NotEmpty
    @Column(name = "password")
    private String passWord;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotEmpty
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;
}
