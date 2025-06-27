package com.sbs.SmartMES.service;

import com.sbs.SmartMES.domain.Equipment;
import com.sbs.SmartMES.domain.ProductionRecord;
import com.sbs.SmartMES.domain.WorkOrder;
import com.sbs.SmartMES.dto.ProductionRecordDto;
import com.sbs.SmartMES.repository.EquipmentRepository;
import com.sbs.SmartMES.repository.ProductionRecordRepository;
import com.sbs.SmartMES.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductionRecordService {

    private final ProductionRecordRepository productionRecordRepository;
    private final EquipmentRepository equipmentRepository;
    private final WorkOrderRepository workOrderRepository;

    public ProductionRecordService(ProductionRecordRepository productionRecordRepository,
                                   EquipmentRepository equipmentRepository,
                                   WorkOrderRepository workOrderRepository) {
        this.productionRecordRepository = productionRecordRepository;
        this.equipmentRepository = equipmentRepository;
        this.workOrderRepository = workOrderRepository;
    }

    public List<ProductionRecordDto> getAll() {
        return productionRecordRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProductionRecordDto create(ProductionRecordDto dto) {
        Equipment equipment = equipmentRepository.findById(dto.getEquipmentId())
                .orElseThrow(() -> new IllegalArgumentException("설비를 찾을 수 없습니다."));
        WorkOrder workOrder = workOrderRepository.findById(dto.getWorkOrderId())
                .orElseThrow(() -> new IllegalArgumentException("작업지시를 찾을 수 없습니다."));

        // 실적 저장
        ProductionRecord record = ProductionRecord.builder()
                .equipment(equipment)
                .workOrder(workOrder)
                .producedQty(dto.getProducedQty())
                .defectQty(dto.getDefectQty())
                .recordedAt(dto.getRecordedAt())
                .build();
        ProductionRecord savedRecord = productionRecordRepository.save(record);

        // 👉 작업지시 상태 자동 변경
        String currentStatus = workOrder.getStatus();
        int totalProduced = productionRecordRepository.findAll().stream()
                .filter(r -> r.getWorkOrder().getId().equals(workOrder.getId()))
                .mapToInt(ProductionRecord::getProducedQty)
                .sum();

        if (currentStatus.equals("WAITING")) {
            workOrder.setStatus("IN_PROGRESS");
        }

        if (totalProduced >= workOrder.getTargetQuantity()) {
            workOrder.setStatus("COMPLETED");
        }

        if (workOrder.getStatus().equals("COMPLETED")) {
            throw new IllegalStateException("이미 완료된 작업지시입니다.");
        }


        workOrderRepository.save(workOrder); // 상태 저장

        return toDto(savedRecord);
    }

    private ProductionRecordDto toDto(ProductionRecord entity) {
        return ProductionRecordDto.builder()
                .id(entity.getId())
                .equipmentId(entity.getEquipment().getId())
                .workOrderId(entity.getWorkOrder().getId())
                .producedQty(entity.getProducedQty())
                .defectQty(entity.getDefectQty())
                .recordedAt(entity.getRecordedAt())
                .build();
    }
}
