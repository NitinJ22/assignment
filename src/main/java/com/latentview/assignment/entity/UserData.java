package com.latentview.assignment.entity;

import com.latentview.assignment.dto.TvShow;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    @Nonnull
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @ElementCollection
    private Set<Long> recommendedShowList = new HashSet<>();
}
