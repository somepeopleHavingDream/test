package org.yangxin.test.mybatisplus;

import lombok.Data;

import java.util.Date;

/**
 * @author yangxin
 * 2020/07/23 10:02
 */
@Data
public class User {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private String telphone;

    private String password;

    private String nickName;

    private Integer gender;
}