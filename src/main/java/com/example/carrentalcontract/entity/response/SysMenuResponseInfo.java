package com.example.carrentalcontract.entity.response;

import com.example.carrentalcontract.entity.model.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuResponseInfo extends SysMenu {

    private List<SysMenuResponseInfo> children;
}
