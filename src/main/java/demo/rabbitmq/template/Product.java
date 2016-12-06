package demo.rabbitmq.template;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Product {
  @Autowired
  private RabbitTemplate rabbitTemplate;
  
  private static ObjectMapper mapper = new ObjectMapper();
  
  /**
   * 
   * 说明：进行各种对数据库delete/update/insert的操作
   * 
   * 操作后调用this.sendMsg("操作方法类型insert")sendMsg("insert")
   */
  
  
  
  
  public void sendMsg(String type) throws AmqpException, JsonProcessingException{
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("type", type);//method.* 是routing key
      this.rabbitTemplate.convertAndSend("method."+type, mapper.writeValueAsString(map));
  }
}
