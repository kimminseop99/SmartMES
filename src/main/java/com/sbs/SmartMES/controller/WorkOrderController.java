package com.sbs.SmartMES.controller;

import com.sbs.SmartMES.domain.WorkOrder;
import com.sbs.SmartMES.dto.WorkOrderDto;
import com.sbs.SmartMES.service.WorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/work-orders")
@Tag(name = "작업지시 API", description = "작업지시 생성/조회/상태변경 처리")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @GetMapping
    public List<WorkOrderDto> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders();
    }

    @PostMapping
    @Operation(summary = "작업지시 등록", description = "새로운 작업지시를 생성합니다.")
    public WorkOrderDto createWorkOrder(@RequestBody @Valid WorkOrderDto dto) {
        return workOrderService.createWorkOrder(dto);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "작업지시 등록", description = "작업지시를 업데이트합니다.")
    public WorkOrderDto updateWorkOrderStatus(@PathVariable Long id,
                                              @RequestParam String status) {
        return workOrderService.updateStatus(id, status);
    }
}