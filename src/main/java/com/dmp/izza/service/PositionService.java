package com.dmp.izza.service;

import com.dmp.izza.wrapper.PositionData;

import java.util.List;

public interface PositionService {

    List<PositionData> getJobList();

    PositionData getJobDetail(String jobId);
}
