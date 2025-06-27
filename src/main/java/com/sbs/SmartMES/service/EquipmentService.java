package com.sbs.SmartMES.service;

import com.sbs.SmartMES.domain.Equipment;
import com.sbs.SmartMES.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment createEquipment(String name) {
        Equipment equipment = Equipment.builder()
                .name(name)
                .status("STOPPED")
                .lastUpdated(LocalDateTime.now())
                .build();
        return equipmentRepository.save(equipment);
    }

    public Equipment updateStatus(Long id, String newStatus) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 설비가 없습니다: " + id));
        equipment.setStatus(newStatus);
        equipment.setLastUpdated(LocalDateTime.now());
        return equipmentRepository.save(equipment);
    }
}
