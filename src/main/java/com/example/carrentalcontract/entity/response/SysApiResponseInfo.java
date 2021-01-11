package com.example.carrentalcontract.entity.response;

import com.example.carrentalcontract.entity.model.SysApi;
import lombok.Data;

import java.util.List;

@Data
public class SysApiResponseInfo extends SysApi {

    private List<SysApiResponseInfo> children;
}
