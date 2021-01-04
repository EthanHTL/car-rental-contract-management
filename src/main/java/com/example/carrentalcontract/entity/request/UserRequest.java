package com.example.carrentalcontract.entity.request;

import com.example.carrentalcontract.entity.model.Users;
import lombok.Data;

import java.util.List;

/**
 * @Author admin
 * @Description //TODO
 * @date 2021/1/3 17:56
 * @Version 1.0
 **/
@Data
public class UserRequest extends Users {

    /**
     * 角色列表
     */
    private List<RoleRequest> roles;

}
