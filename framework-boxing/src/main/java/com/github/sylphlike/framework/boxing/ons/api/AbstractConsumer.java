package com.github.sylphlike.framework.boxing.ons.api;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.github.sylphlike.framework.boxing.GoSerializer;
import com.github.sylphlike.framework.boxing.ons.core.ConsumerChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public abstract class AbstractConsumer<T> implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(AbstractConsumer.class);

    @Autowired ConsumerChooser<T> consumerChooser;



    /**
     * 监听的topic和tag
     * <p>  time 15:13 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  com.github.sylphlike.boxing.ons.api.MessageDefinition
     * @author  Gopal.pan
     */
    public abstract MessageDefinition definition();



    /**
     * 具体业务处理
     * <p>  time 15:12 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param message  业务数据
     * @return  com.aliyun.openservices.ons.api.Action
     * @author  Gopal.pan
     */
    public abstract Action handleMQ(T message);

    @Override
    public Action consume(Message message, ConsumeContext context) {
        T t = (T) GoSerializer.deSerialize(message.getBody());
        logger.info("【framework-boxing】收到消息,topic[{}],tag[{}],message[{}]",message.getTopic(),message.getTag(),t);

        AbstractConsumer<T> choose = consumerChooser.choose(message.getTopic() + ":" + message.getTag());
        if(StringUtils.isEmpty(choose)){
            logger.info("【framework-boxing】MQ消息签收失败,不存在对应的消费实现类");
            return Action.CommitMessage;
        }

        return choose.handleMQ(t);
    }

}
