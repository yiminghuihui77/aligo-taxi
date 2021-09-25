package com.huihui.aligo.mapper;

import com.huihui.aligo.model.ApiPassengerModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author minghui.y
 * @create 2021-09-25 2:28 下午
 **/
@Mapper
public interface ApiPassengerMapper {

    int insert( ApiPassengerModel model );
}
