package com.shuili.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* 临时验证码
* @TableName sys_cachecode
*/

@Data
@TableName("sys_cachecode")
@Accessors(chain = true)
public class SysCachecode implements Serializable {

    /**
    * 验证码编号
    */
    @NotNull(message="[验证码编号]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
    * 验证码
    */
    @Size(max= 5,message="编码长度不能超过5")
    @Length(max= 5,message="编码长度不能超过5")
    @TableField("code")
    private String code;
    /**
    * 登录用户的id
    */
    @NotNull(message="[登录用户的id]不能为空")
    @TableField("user_ip")
    private String userIp;
    /**
    * 验证码创建时间
    */
    @TableField("creat_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creatTime;
    /**
    * 验证码过期时间
    */
    @NotNull(message="[验证码过期时间]不能为空")
    @TableField("exper_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date experTime;


}
