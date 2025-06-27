package com.sbs.SmartMES.controller;

import com.sbs.SmartMES.dto.ProductionRecordDto;
import com.sbs.SmartMES.service.ProductionRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-records")
public class ProductionRecordController {

    private final ProductionRecordService productionRecordService;

    public ProductionRecordController(ProductionRecordService productionRecordService) {
        this.productionRecordService = productionRecordService;
    }

    @GetMapping
    public List<ProductionRecordDto> getAll() {
        return productionRecordService.getAll();
    }

    @PostMapping
    public ProductionRecordDto create(@RequestBody @Valid ProductionRecordDto dto) {
        return productionRecordService.create(dto);
    }
}