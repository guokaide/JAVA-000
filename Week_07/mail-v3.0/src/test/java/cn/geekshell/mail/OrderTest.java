package cn.geekshell.mail;

import cn.geekshell.mail.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderTest.class)
@SpringBootApplication
@MapperScan("cn.geekshell.mail.mapper")
public class OrderTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void testQuery(){
        Map<String, Object> orderM = orderService.queryById(602);
        System.out.println(orderM);
    }

    @Test
    public void testInsert(){
        for (int i = 1; i < 1000; i++){
            Map<String,Object> orderM = new HashMap<>();
            orderM.put("orderId",i);
            orderM.put("userId",randomL(1,100));
            orderM.put("createdTime",randomDate("2020-01-01 00:00:00","2020-12-31 23:59:59"));
            orderM.put("orderTotalAmount",randomF(10000,10,2));
            orderService.insert(orderM);
        }

    }

    private static Date randomDate(String beginDate, String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = randomL(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long randomL(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return randomL(begin,end);
        }
        return rtn;
    }
    private static BigDecimal randomF(float max, float min, int scale){
        BigDecimal cha = new BigDecimal(Math.random() * (max-min) + min);
        return cha.setScale(scale,BigDecimal.ROUND_HALF_UP);//保留 scale 位小数，并四舍五入
    }
}
