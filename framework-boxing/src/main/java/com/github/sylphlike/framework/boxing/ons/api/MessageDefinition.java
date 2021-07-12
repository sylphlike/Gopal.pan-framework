package com.github.sylphlike.framework.boxing.ons.api;



/**
 * 客户端定义消息接口
 * <p> 实现类必须为枚举类型
 *      枚举至少定义两个属性（topic,tag）desc 属性可不定义，该SDK读取topic和tag属性进行MQ消费端注册
 *      topic属性值可以重复 即是一个topic对应多个tag,当对应的tag也相同时认为是同一个
 *      tag可以符合MQ规范的表达式
 *
 *      示例：
 *          @AllArgsConstructor
 *          @Getter
 *          public enum TestMQEnums implements MessageDefinition {
 *
 *              MQ_TEST_1     ("MQ_TEST_1", "tag_1","描述"),
 *              MQ_TEST_2     ("MQ_TEST_1", "tag_2","描述"),
 *              MQ_TEST_3     ("MQ_TEST_2", "tag_1","描述"),
 *              MQ_TEST_4     ("MQ_TEST_3", "tag_1","描述"),
 *              MQ_TEST_5     ("MQ_TEST_4", "tag_1","描述"),
 *
 *              ;
 *              private String topic;
 *              private String tag;
 *              private String desc;
 *
 *           }                       </p>
 * <p>  time 17:56 2019/03/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public interface MessageDefinition {


    String getTopic();

    String getTag();

    String getDesc();



}
