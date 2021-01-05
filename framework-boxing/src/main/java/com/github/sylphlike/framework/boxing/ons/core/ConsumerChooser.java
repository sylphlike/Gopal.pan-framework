package com.github.sylphlike.framework.boxing.ons.core;

import com.github.sylphlike.framework.boxing.ons.api.AbstractConsumer;
import com.github.sylphlike.framework.boxing.ons.api.MessageDefinition;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Component
public class ConsumerChooser<T> implements ApplicationContextAware {
    private ApplicationContext context;



    public Map<String, AbstractConsumer<T>> chooseMap = new HashMap<>();

    public  AbstractConsumer<T> choose(String type) {
        return chooseMap.get(type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @PostConstruct
    public void register(){
        Map<String, AbstractConsumer> solverMap = context.getBeansOfType(AbstractConsumer.class);

        for (AbstractConsumer<T> solver : solverMap.values()) {
            MessageDefinition definition = solver.definition();
            chooseMap.put(definition.getTopic() + ":" + definition.getTag(),solver);
        }
    }
}
