package com.dmp.izza.controller;

import com.dmp.izza.service.PositionService;
import com.dmp.izza.wrapper.PositionData;
import com.dmp.izza.wrapper.PositionResp;
import com.dmp.izza.wrapper.StatusResp;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("job")
public class JobController {

    private final PositionService jobService;

    public JobController(PositionService positionService) {
        this.jobService = positionService;
    }

    @Operation(description = "Get job list API")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getJobList() {
        PositionResp response = new PositionResp();
        StatusResp statusResp = new StatusResp();
        List<PositionData> positions = jobService.getJobList();

        if (positions.size() != 0) {
            statusResp.setCode("200");
            statusResp.setMessage("Success");

            response.setStatus(statusResp);
            response.setResult(positions);
        } else {
            statusResp.setCode("200");
            statusResp.setMessage("Jobs data not found!");

            response.setStatus(statusResp);
            response.setResult(positions);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Get job detail API")
    @RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getJobDetailById(@PathVariable String id) {
        PositionResp response = new PositionResp();
        StatusResp statusResp = new StatusResp();
        PositionData position = jobService.getJobDetail(id);

        if (position != null) {
            statusResp.setCode("200");
            statusResp.setMessage("Success");

            response.setStatus(statusResp);
            response.setResult(position);
        } else {
            statusResp.setCode("200");
            statusResp.setMessage("Job data not found!");

            response.setStatus(statusResp);
            response.setResult(position);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
