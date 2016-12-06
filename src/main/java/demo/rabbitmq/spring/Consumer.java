package demo.rabbitmq.spring;

public class Consumer {
   
   //需要执行的业务方法
    public void execute (String msg){
        System.out.println("consumer："+msg);
    }
}
