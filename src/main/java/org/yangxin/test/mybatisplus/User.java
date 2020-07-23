package org.yangxin.test.mybatisplus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author yangxin
 * 2020/07/23 10:02
 */
@Data
@TableName("user")
public class User {

    @TableId
    private Integer id;

    @TableField("created_at")
    private Date createdAt;

    private Date updatedAt;

    private String telphone;

    private String password;

    private String nickName;

    private Integer gender;

    /**
     * 备注
     * 排除非表字段的三种方式（transient、static、@TableField(exist=false))
     */
    private transient String remark;
}