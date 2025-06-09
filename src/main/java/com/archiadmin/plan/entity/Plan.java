package com.archiadmin.plan.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "plans")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    private String planName;
    private int price;
    private int monthData;
    private String callUsage;
    private String messageUsage;
    private String benefit;
    private long tagCode;
    private String ageCode;
    private String categoryCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
