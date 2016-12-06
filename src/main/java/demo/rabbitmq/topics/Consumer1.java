package demo.rabbitmq.topics;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import demo.rabbitmq.util.ConnectionUtil;

public class Consumer1 {
    
    private final static String QUEUE_NAME = "demo_queue_topic_1";
    private final static String EXCHANGE_NAME = "demo_exchange_topic";
    
    public static void main(String[] args) throws Exception {
        
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "method.*");
        
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println(" 消费者1：" + msg);
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
