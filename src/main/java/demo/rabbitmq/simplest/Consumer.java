package demo.rabbitmq.simplest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

import demo.rabbitmq.util.ConnectionUtil;

public class Consumer {
   private final static String QUEUE_NAME = "simplest_queue";
   public static void main(String[] args) throws Exception {
     //获取连接以及rabbitmq通道
       Connection connection = ConnectionUtil.getConnection();
       //通过连接获取通道
       Channel channel = connection.createChannel();
       
    // 声明队列
       channel.queueDeclare(QUEUE_NAME, false, false, false, null);

       // 定义队列的消费者
       QueueingConsumer consumer = new QueueingConsumer(channel);
       // 监听队列
       channel.basicConsume(QUEUE_NAME, true, consumer);

       // 获取消息
       for(int i=0;i<5;i++){
    	   Delivery delivery = consumer.nextDelivery();
           String msg = new String(delivery.getBody());
           System.out.println("消费者" + msg + "'");
       }
}
}
