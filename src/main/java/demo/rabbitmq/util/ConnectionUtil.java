package demo.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
   public static Connection getConnection () throws Exception{
       //定义连接工厂
       ConnectionFactory factory = new ConnectionFactory();
       //设置服务地址
       factory.setHost("localhost");
       //设置端口号
       factory.setPort(5672);
       //设置账号信息，用户名、密码、vhost
       factory.setUsername("guest");
       factory.setPassword("guest");
       factory.setVirtualHost("/demo");
       //通过工厂获取连接
       Connection connection = factory.newConnection();
       return connection;
   }
}
