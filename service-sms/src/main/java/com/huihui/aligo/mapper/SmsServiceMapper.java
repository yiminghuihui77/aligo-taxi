package com.huihui.aligo.mapper;

import com.huihui.aligo.model.SmsServiceModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author minghui.y
 * @create 2021-09-25 2:49 下午
 **/
@Mapper
public interface SmsServiceMapper {

    int insert( SmsServiceModel model );
}
