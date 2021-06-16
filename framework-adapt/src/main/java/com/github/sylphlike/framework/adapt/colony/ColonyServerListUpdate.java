package com.github.sylphlike.framework.adapt.colony;

import com.netflix.loadbalancer.PollingServerListUpdater;
import org.springframework.stereotype.Component;

/**
 * 获取loadbalancer 中的UpdateAction
 * <p>  time 09:59 2021/06/07  星期一 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Component
public class ColonyServerListUpdate extends PollingServerListUpdater {
    private UpdateAction updateAction;

    @Override
    public synchronized void start(UpdateAction updateAction) {
        super.start(updateAction);
    }

    public  UpdateAction getUpdateAction(){
        return updateAction;
    }
}
