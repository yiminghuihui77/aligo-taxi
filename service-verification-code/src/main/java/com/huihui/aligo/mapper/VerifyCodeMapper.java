package com.huihui.aligo.mapper;

import com.huihui.aligo.model.VerifyCodeModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author minghui.y
 * @create 2021-09-25 2:44 下午
 **/
@Mapper
public interface VerifyCodeMapper {

    int insert( VerifyCodeModel model );
}
