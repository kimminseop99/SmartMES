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
public class ProductionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Equipment equipment;

    @ManyToOne(optional = false)
    private WorkOrder workOrder;

    @Column(nullable = false)
    private int producedQty;

    @Column(nullable = false)
    private int defectQty;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}
