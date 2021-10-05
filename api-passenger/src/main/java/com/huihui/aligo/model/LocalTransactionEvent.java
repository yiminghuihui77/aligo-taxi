package com.huihui.aligo.model;

import lombok.Data;

/**
 * @author minghui.y
 * @create 2021-10-04 10:17 下午
 **/
@Data
public class LocalTransactionEvent {

    private Long id;

    private String transactionId;

    private String message;
}
