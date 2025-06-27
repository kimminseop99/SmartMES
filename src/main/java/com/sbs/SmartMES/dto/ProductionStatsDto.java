package com.sbs.SmartMES.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionStatsDto {

    private String label;       // 일자 또는 설비 이름
    private int totalProduced;
    private int totalDefective;
    private double defectRate; // % 비율
}
