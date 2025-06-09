package com.archiadmin.service.entity;

import com.archiadmin.service.dto.request.ServiceRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "services")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private String serviceName;
    private int price;
    private String imageUrl;
    private String serviceDescription;
    private int saleRate;
    private long tagCode;
    private String categoryCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
