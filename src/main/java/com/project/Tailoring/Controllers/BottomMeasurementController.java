package com.project.Tailoring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.Tailoring.Entities.BottomMeasurement;
import com.project.Tailoring.Service.BottomMeasurementService;

@RestController
@RequestMapping("/bottom")
public class BottomMeasurementController {

    @Autowired
    private BottomMeasurementService bottomMeasurementService;

    @PostMapping("/{memberId}")
    public BottomMeasurement saveBottom(
            @PathVariable Long memberId,
            @RequestBody BottomMeasurement bottomMeasurement) {

        return bottomMeasurementService
                .saveBottomMeasurement(memberId, bottomMeasurement);
    }

    @GetMapping("/{id}")
    public BottomMeasurement getBottom(@PathVariable Long id) {

        return bottomMeasurementService
                .getBottomMeasurementById(id);
    }
}
