package cn.geekshell.mail;

import cn.geekshell.mail.service.OrderInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.geekshell.mail.mapper")
public class OrderInfoTest {

    @Autowired
    private OrderInfoService infoService;

    @Test
    public void testQuery(){
        Map<String, Object> slave = infoService.queryByInfoId(1);
        System.out.println(slave);
    }

    @Test
    public void testInsert(){
        Map<String,Object> orderInfo = new HashMap<>();
        orderInfo.put("orderInfoId",2);
        orderInfo.put("orderId",1);
        orderInfo.put("goodsId",2);
        orderInfo.put("goodsNum",2);
        infoService.insert(orderInfo);

    }
}
