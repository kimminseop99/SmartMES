package com.sbs.SmartMES.repository;

import com.sbs.SmartMES.domain.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
}