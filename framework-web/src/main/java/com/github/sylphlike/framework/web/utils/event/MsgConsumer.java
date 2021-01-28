package com.github.sylphlike.framework.web.utils.event;



/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 * @author Gopal.pan
 * @version 1.0.0
 */
public interface  MsgConsumer {

     /**
      * 具体业务处理
      * @param msgEvent  业务参数
      */
     void doMsg(MsgEvent<?> msgEvent);


     /**
      * 消息类型
      * @return
      */
     String msgType();





}
