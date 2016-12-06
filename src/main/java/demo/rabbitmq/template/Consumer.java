package demo.rabbitmq.template;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Consumer {
    private static ObjectMapper Mapper = new ObjectMapper();
    
    public void execute(String msg){
        try {
         JsonNode node = Mapper.readTree(msg);
         String type = node.get("type").asText();
         if(StringUtils.equals(type, "update")||StringUtils.equals(type, "delete")||StringUtils.equals(type, "insert")){
             //删除缓存,或者更新缓存
             //xxxxxxxxxxxxxxxxxxx
         }
     } catch (Exception e) {
         e.printStackTrace();
       }
     }
}
