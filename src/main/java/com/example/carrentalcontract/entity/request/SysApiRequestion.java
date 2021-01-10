package com.example.carrentalcontract.entity.request;

import com.example.carrentalcontract.entity.model.SysApi;
import lombok.Data;

import java.util.List;

@Data
public class SysApiRequestion extends SysApi {

    private List<SysApiRequestion> children;
}
