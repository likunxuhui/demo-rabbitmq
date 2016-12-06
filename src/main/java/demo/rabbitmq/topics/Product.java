package demo.rabbitmq.topics;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import demo.rabbitmq.util.ConnectionUtil;

public class Product {
    
    private final static String EXCHANGE_NAME = "demo_exchange_topic";
    
    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        
        // 消息内容
        String msg = "this is Topics demo!";
        channel.basicPublish(EXCHANGE_NAME, "method.insert", null, msg.getBytes());
        System.out.println("Topics 生产者：" + msg);

        channel.close();
        connection.close();
    }
}
