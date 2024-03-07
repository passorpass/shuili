package com.shuili.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @TableName wp_sys_user
 */
@TableName(value ="wp_sys_user")
@Data
@Accessors(chain = true)
public class WpSysUser implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_username")
    private String nickUsername;

    /**
     * 用户邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 用户手机号码
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 操作人id
     */
    @TableField(value = "operator_id")
    private String operatorId;

    /**
     * 更新人名称
     */
    @TableField(value = "operator_name")
    private String operatorName;

    /**
     * 权限（1.全部权限，2部分权限）
     */
    @TableField(value = "autho")
    private Integer autho;

    /**
     * 权限描述（1.管理员，2.普通用户）
     */
    @TableField(value = "description")
    private Integer description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
