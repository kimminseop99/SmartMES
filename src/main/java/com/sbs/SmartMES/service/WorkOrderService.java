package com.sbs.SmartMES.service;

import com.sbs.SmartMES.domain.WorkOrder;
import com.sbs.SmartMES.dto.WorkOrderDto;
import com.sbs.SmartMES.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;

    public WorkOrderService(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    public List<WorkOrderDto> getAllWorkOrders() {
        return workOrderRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public WorkOrderDto createWorkOrder(WorkOrderDto dto) {
        WorkOrder saved = workOrderRepository.save(toEntity(dto));
        return toDto(saved);
    }

    public WorkOrderDto updateStatus(Long id, String newStatus) {
        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("작업 지시를 찾을 수 없습니다. ID: " + id));
        workOrder.setStatus(newStatus);
        return toDto(workOrderRepository.save(workOrder));
    }

    // ===== DTO 매핑 =====
    private WorkOrderDto toDto(WorkOrder entity) {
        return WorkOrderDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .targetQuantity(entity.getTargetQuantity())
                .status(entity.getStatus())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .build();
    }

    private WorkOrder toEntity(WorkOrderDto dto) {
        return WorkOrder.builder()
                .productName(dto.getProductName())
                .targetQuantity(dto.getTargetQuantity())
                .status(dto.getStatus())
                .startAt(dto.getStartAt())
                .endAt(dto.getEndAt())
                .build();
    }
}
