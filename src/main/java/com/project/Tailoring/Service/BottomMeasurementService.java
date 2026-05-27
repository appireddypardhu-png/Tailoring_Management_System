package com.project.Tailoring.Service;

import com.project.Tailoring.Entities.BottomMeasurement;

public interface BottomMeasurementService {

    BottomMeasurement saveBottomMeasurement(Long memberId,
                                            BottomMeasurement bottomMeasurement);

    BottomMeasurement getBottomMeasurementById(Long id);
}