package com.sbs.SmartMES.controller;

import com.sbs.SmartMES.domain.Equipment;
import com.sbs.SmartMES.service.EquipmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public List<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    @PostMapping
    public Equipment createEquipment(@RequestParam String name) {
        return equipmentService.createEquipment(name);
    }

    @PutMapping("/{id}/status")
    public Equipment updateEquipmentStatus(@PathVariable Long id, @RequestParam String status) {
        return equipmentService.updateStatus(id, status);
    }
}