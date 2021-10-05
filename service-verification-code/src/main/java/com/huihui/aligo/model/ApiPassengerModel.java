package com.huihui.aligo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author minghui.y
 * @create 2021-09-25 2:27 下午
 **/
@Data
public class ApiPassengerModel {

    private Integer id;

    private Integer identity;

    private String phoneNumber;

    private Date createTime;
}
