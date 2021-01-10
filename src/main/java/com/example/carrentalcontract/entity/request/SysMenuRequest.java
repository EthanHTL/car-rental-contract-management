package com.example.carrentalcontract.entity.request;

import com.example.carrentalcontract.entity.model.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuRequest extends SysMenu {

    private List<SysMenuRequest> children;
}
