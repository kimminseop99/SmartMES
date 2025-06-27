package com.sbs.SmartMES.repository;

import com.sbs.SmartMES.domain.ProductionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRecordRepository extends JpaRepository<ProductionRecord, Long> {
}
