package com.project.Tailoring.Service;

import com.project.Tailoring.Entities.TopMeasurement;

public interface TopMeasurementService {

    TopMeasurement saveTopMeasurement(Long memberId, TopMeasurement topMeasurement);

    TopMeasurement getTopMeasurementById(Long id);
}