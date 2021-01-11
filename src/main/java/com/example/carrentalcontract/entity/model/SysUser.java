package com.example.carrentalcontract.entity.model;

import com.example.carrentalcontract.annotation.Dict;
import com.example.carrentalcontract.common.DbPageParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2020-12-27 22:11:57
 */
@Data
@Accessors(chain = true)
@Table(name = "sys_users")
public class SysUser extends DbPageParameter implements Serializable {
    private static final long serialVersionUID = 136163826059612169L;

    /**
     * 主键
     */
    @Id
    private Long id;
    /**
    * 用户账号
    */
    private String username;
    /**
    * 性别
    */
    private Integer sex;
    /**
    * 姓名
    */
    private String nickname;
    /**
     * 密码
     **/
    private String password;
    /**
    * 住址
    */
    private String addr;
    /**
    * 电话
    */
    private String telephone;
    /**
    * 身份证号
    */
    private String idCard;
    /**
    * 信誉分数
    */
    @Column(name = "reputation_score")
    private Integer reputationScore;
    /**
    * 状态
    */
    private Integer flag;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    @Transient
    private String updatorId;

    @Transient
    private String creatorId;


}