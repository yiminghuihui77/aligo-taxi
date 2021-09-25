package com.huihui.aligo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author minghui.y
 * @create 2021-09-25 2:42 下午
 **/
@Data
public class VerifyCodeModel {

    private Integer id;

    private Integer identity;

    private String phoneNumber;

    private String verifyCode;

    private Date createTime;
}
