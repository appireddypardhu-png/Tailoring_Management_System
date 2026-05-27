package com.project.Tailoring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Tailoring.Entities.TopMeasurement;

public interface TopMeasurementRepository extends JpaRepository<TopMeasurement, Long> {
}