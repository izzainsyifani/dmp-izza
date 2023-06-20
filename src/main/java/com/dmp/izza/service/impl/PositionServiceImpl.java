package com.dmp.izza.service.impl;

import com.dmp.izza.service.PositionService;
import com.dmp.izza.wrapper.PositionData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Override
    public List<PositionData> getJobList() {
        List<PositionData> positions = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

        ResponseEntity<List<PositionData>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        if (response.getStatusCode() == HttpStatus.OK) {
            positions = response.getBody();
        }
        return positions;
    }

    @Override
    public PositionData getJobDetail(String jobId) {
        PositionData position = new PositionData();

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions/{jobId}";

        ResponseEntity<PositionData> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                jobId);

        if (response.getStatusCode() == HttpStatus.OK) {
            position = response.getBody();
        }
        return position;
    }
}
