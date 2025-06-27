package com.sbs.SmartMES;

import com.sbs.SmartMES.domain.WorkOrder;
import com.sbs.SmartMES.dto.WorkOrderDto;
import com.sbs.SmartMES.service.WorkOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/work-orders")
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
    public WorkOrderDto createWorkOrder(@RequestBody @Valid WorkOrderDto dto) {
        return workOrderService.createWorkOrder(dto);
    }

    @PutMapping("/{id}/status")
    public WorkOrderDto updateWorkOrderStatus(@PathVariable Long id,
                                              @RequestParam String status) {
        return workOrderService.updateStatus(id, status);
    }
}