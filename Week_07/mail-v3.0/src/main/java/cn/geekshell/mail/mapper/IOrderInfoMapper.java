package cn.geekshell.mail.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface IOrderInfoMapper {

    @Select("select * from T_ORDER_INFO where ORDER_INFO_ID = #{infoId}")
    Map<String, Object> queryByInfoId(@Param("infoId") int infoId);

}
