package com.example.carrentalcontract.mapper;


import com.example.carrentalcontract.common.DbMapper;
import com.example.carrentalcontract.common.Result;
import com.example.carrentalcontract.entity.view.Users;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户表(Users)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
public interface UsersMapper extends DbMapper<Users> {


}