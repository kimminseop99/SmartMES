package com.sbs.SmartMES.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrderDto {

    private Long id;

    @NotBlank(message = "제품명을 입력하세요.")
    private String productName;

    @Min(value = 1, message = "목표 수량은 1 이상이어야 합니다.")
    private int targetQuantity;

    @NotBlank(message = "상태는 필수입니다.") // WAITING, IN_PROGRESS, COMPLETED
    private String status;

    @NotNull(message = "시작 일시를 입력하세요.")
    private LocalDateTime startAt;

    @NotNull(message = "종료 일시를 입력하세요.")
    @Future(message = "종료일은 미래의 시간이어야 합니다.")
    private LocalDateTime endAt;
}
