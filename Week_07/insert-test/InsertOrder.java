package cn.geekshell.week07;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertOrder {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /**
         *  100w 执行时间： 387931
         *  1000w 执行时间: 4138615
         */
        insertBatch();
        /**
         * 100w 执行时间：738685
         * */
//        insert();

    }

    private static void insertBatch() throws ClassNotFoundException, SQLException {
        long start = System.currentTimeMillis();
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //定义连接信息
        String url = "jdbc:mysql://127.0.0.1:3306/mail";
        String user = "root";
        String password = "12345678";

        Connection connection = null;
        Statement statement = null;
        try {
            //获取数据库连接对象
            connection = DriverManager.getConnection(url, user, password);
            //通过connection获取操作类
            statement = connection.createStatement();
            //使用 Statement批处理
            String beginTime = "2020-01-01 00:00:00";
            String endTime = "2020-12-31 23:59:59";
            for(int i = 1; i <= 10000000; i++){
                Date date = randomDate(beginTime, endTime);
                Timestamp timeStamp = new Timestamp(date.getTime());
                String sql = "insert into t_order (ORDER_ID,USER_ID,ORDER_TOTAL_AMOUNT,CREATED_TIME) " +
                        "values("+i+","+"'"+random(1,1000)+"',"+makeRandomF(10f,10000f,2)+",'"+timeStamp.toString()+"'" +
                        ")";
                statement.addBatch(sql);
            }
            statement.executeBatch();
            statement.clearBatch();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭连接资源
            statement.close();
            connection.close();
        }
        System.out.println("执行时间：" + (System.currentTimeMillis()-start));
    }

    private static void insert() throws ClassNotFoundException, SQLException {
        long start = System.currentTimeMillis();
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //定义连接信息
        String url = "jdbc:mysql://127.0.0.1:3306/mail";
        String user = "root";
        String password = "12345678";

        Connection connection = null;
        Statement statement = null;
        try {
            //获取数据库连接对象
            connection = DriverManager.getConnection(url, user, password);
            //通过connection获取操作类
            statement = connection.createStatement();

            String beginTime = "2020-01-01 00:00:00";
            String endTime = "2020-12-31 23:59:59";
            for(int i = 1; i <= 1000000; i++){
                Date date = randomDate(beginTime, endTime);
                Timestamp timeStamp = new Timestamp(date.getTime());
                String sql = "insert into t_order (ORDER_ID,USER_ID,ORDER_TOTAL_AMOUNT,CREATED_TIME) " +
                        "values("+i+","+"'"+random(1,1000)+"',"+makeRandomF(10f,10000f,2)+",'"+timeStamp.toString()+"'" +
                        ")";
                statement.execute(sql);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭连接资源
            statement.close();
            connection.close();
        }
        System.out.println("执行时间：" + (System.currentTimeMillis()-start));
    }

    private static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
    private static BigDecimal makeRandomF(float max, float min, int scale){
        BigDecimal cha = new BigDecimal(Math.random() * (max-min) + min);
        return cha.setScale(scale,BigDecimal.ROUND_HALF_UP);//保留 scale 位小数，并四舍五入
    }

}
