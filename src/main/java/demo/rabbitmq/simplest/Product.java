package demo.rabbitmq.simplest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import demo.rabbitmq.util.ConnectionUtil;

public class Product {
    private final static String QUEUE_NAME = "simplest_queue";
    public static void main(String[] args) throws Exception {
        //获取连接以及rabbitmq通道
        Connection connection = ConnectionUtil.getConnection();
        //通过连接获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送消息
        for(int i=0;i<10;i++){
        	String msg = "hello world";
        	msg = msg+"***"+i;
            channel.basicPublish("", QUEUE_NAME, null,  msg.getBytes());
            System.out.println("发送消息："+msg+"***"+i);
        }
        
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
