package com.latentview.assignment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recommendedShowHistory")
public class RecommendationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserData userData;

    @Column(nullable = false)
    private Long showID;
}
