package com.project.Tailoring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.Tailoring.Entities.TopMeasurement;
import com.project.Tailoring.Service.TopMeasurementService;

@RestController
@RequestMapping("/top")
public class TopMeasurementController {

    @Autowired
    private TopMeasurementService topMeasurementService;

    @PostMapping("/{memberId}")
    public TopMeasurement saveTop(
            @PathVariable Long memberId,
            @RequestBody TopMeasurement topMeasurement) {

        return topMeasurementService.saveTopMeasurement(memberId, topMeasurement);
    }

    @GetMapping("/{id}")
    public TopMeasurement getTop(@PathVariable Long id) {
        return topMeasurementService.getTopMeasurementById(id);
    }
}