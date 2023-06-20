package com.dmp.izza.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PositionResp<T> {
    private StatusResp status;
    private T result;
}