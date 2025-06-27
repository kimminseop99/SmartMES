package com.sbs.SmartMES.controller;

import com.sbs.SmartMES.dto.ProductionStatsDto;
import com.sbs.SmartMES.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/production-stats")
    public List<ProductionStatsDto> getStatsByDate() {
        return dashboardService.getStatsByDate();
    }

    @GetMapping("/equipment-stats")
    public List<ProductionStatsDto> getStatsByEquipment() {
        return dashboardService.getStatsByEquipment();
    }
}
