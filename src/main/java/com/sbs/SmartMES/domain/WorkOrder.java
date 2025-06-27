package com.sbs.SmartMES.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int targetQuantity;

    @Column(nullable = false)
    private String status; // WAITING, IN_PROGRESS, COMPLETED

    private LocalDateTime startAt;

    private LocalDateTime endAt;
}
