package com.sbs.SmartMES.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionRecordDto {

    private Long id;

    @NotNull(message = "설비 ID는 필수입니다.")
    private Long equipmentId;

    @NotNull(message = "작업지시 ID는 필수입니다.")
    private Long workOrderId;

    @Min(value = 0, message = "생산 수량은 0 이상이어야 합니다.")
    private int producedQty;

    @Min(value = 0, message = "불량 수량은 0 이상이어야 합니다.")
    private int defectQty;

    @NotNull(message = "기록일시는 필수입니다.")
    private LocalDateTime recordedAt;
}
