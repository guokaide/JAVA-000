package cn.geekshell.mail.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface IOrderMapper {

    void insert(@Param("orderM") Map<String,Object> orderM);

//    @Select("select * from t_order where order_id = #{id}")
    Map<String, Object> queryById(@Param("id") int id);
}
