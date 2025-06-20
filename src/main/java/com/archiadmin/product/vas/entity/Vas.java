package com.archiadmin.product.vas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "vass")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vasId;
    private String vasName;
    private int price;
    private String imageUrl;
    private String vasDescription;
    private int saleRate;
    private long tagCode;
    private String categoryCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
