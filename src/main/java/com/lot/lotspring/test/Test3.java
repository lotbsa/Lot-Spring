package com.lot.lotspring.test;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test3 {

    private long begin = 33112001;//起始id
    private long end = begin + 100000;//每次循环插入的数据量
    private final String url = "jdbc:mysql://localhost:3306/loTDB?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8";
    private final String user = "root";
    private final String password = "root";

    public void insertBigData() {

        //定义连接、statement对象
        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            LocalDateTime now = LocalDateTime.now();
            System.out.println("开始时间（LocalDateTime类型）: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            conn.setAutoCommit(false);
            //编写sql
            String sql = "INSERT INTO tb_user VALUES (?,?,?,?,?,?,?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();

            //循环10次，每次一万数据，一共10万
            for (int i = 0; i < 10000; i++) {
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //赋值
                    pstm.setLong(1, begin);
                    pstm.setString(2, RandomValue.getChineseName());
                    pstm.setString(3, RandomValue.getName_sex());
                    pstm.setInt(4, RandomValue.getNum(1, 100));
                    pstm.setString(5, RandomValue.getEmail(4, 15));
                    pstm.setString(6, RandomValue.getTel());
                    pstm.setString(7, RandomValue.getRoad());
                    pstm.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                    //执行sql
                    pstm.execute();
                    begin++;
                }
                //提交事务
                conn.commit();
                //边界值自增10W
                end += 10000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入1W条数据耗时：" + (eTime - bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入10W数据共耗时：" + (eTime1 - bTime1));

            System.out.println("结束时间（LocalDateTime类型）: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {
        Test3 test3 = new Test3();
        test3.insertBigData();
    }


}