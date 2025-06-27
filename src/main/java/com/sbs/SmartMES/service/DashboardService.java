package com.sbs.SmartMES.service;

import com.sbs.SmartMES.domain.ProductionRecord;
import com.sbs.SmartMES.dto.ProductionStatsDto;
import com.sbs.SmartMES.repository.ProductionRecordRepository;
import org.springframework.stereotype.Service;


import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ProductionRecordRepository productionRecordRepository;

    public DashboardService(ProductionRecordRepository productionRecordRepository) {
        this.productionRecordRepository = productionRecordRepository;
    }

    public List<ProductionStatsDto> getStatsByDate() {
        List<ProductionRecord> records = productionRecordRepository.findAll();

        Map<String, List<ProductionRecord>> grouped = records.stream()
                .collect(Collectors.groupingBy(r -> r.getRecordedAt().toLocalDate().format(DateTimeFormatter.ISO_DATE)));

        List<ProductionStatsDto> result = new ArrayList<>();

        for (String date : grouped.keySet()) {
            List<ProductionRecord> dailyRecords = grouped.get(date);
            int totalProduced = dailyRecords.stream().mapToInt(ProductionRecord::getProducedQty).sum();
            int totalDefective = dailyRecords.stream().mapToInt(ProductionRecord::getDefectQty).sum();
            double rate = totalProduced == 0 ? 0.0 : (double) totalDefective / totalProduced * 100;

            result.add(ProductionStatsDto.builder()
                    .label(date)
                    .totalProduced(totalProduced)
                    .totalDefective(totalDefective)
                    .defectRate(Math.round(rate * 100.0) / 100.0)
                    .build());
        }

        result.sort(Comparator.comparing(ProductionStatsDto::getLabel));
        return result;
    }

    public List<ProductionStatsDto> getStatsByEquipment() {
        List<ProductionRecord> records = productionRecordRepository.findAll();

        Map<String, List<ProductionRecord>> grouped = records.stream()
                .collect(Collectors.groupingBy(r -> r.getEquipment().getName()));

        List<ProductionStatsDto> result = new ArrayList<>();

        for (String eqName : grouped.keySet()) {
            List<ProductionRecord> eqRecords = grouped.get(eqName);
            int totalProduced = eqRecords.stream().mapToInt(ProductionRecord::getProducedQty).sum();
            int totalDefective = eqRecords.stream().mapToInt(ProductionRecord::getDefectQty).sum();
            double rate = totalProduced == 0 ? 0.0 : (double) totalDefective / totalProduced * 100;

            result.add(ProductionStatsDto.builder()
                    .label(eqName)
                    .totalProduced(totalProduced)
                    .totalDefective(totalDefective)
                    .defectRate(Math.round(rate * 100.0) / 100.0)
                    .build());
        }

        return result;
    }
}
