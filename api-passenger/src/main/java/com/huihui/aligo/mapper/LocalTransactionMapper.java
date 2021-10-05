package com.huihui.aligo.mapper;

import com.huihui.aligo.model.LocalTransactionEvent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author minghui.y
 * @create 2021-10-04 10:19 下午
 **/
@Mapper
public interface LocalTransactionMapper {

    int insert( LocalTransactionEvent event );

    LocalTransactionEvent getByTransactionId(String transactionId);
}
