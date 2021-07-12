package com.github.sylphlike.framework.glued.common.utils;

import com.github.sylphlike.framework.adapt.Constants;
import com.github.sylphlike.framework.norm.CharsetUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 *  URL构造
 * <p>  time 17:56 2017/03/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
public class UrlBuilder {

    private  Map<String, String> params = new LinkedHashMap<>(7);
    private String baseUrl;

    public UrlBuilder(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public UrlBuilder  builderParam(String key,String value) {
        if(StringUtils.isEmpty(key) || StringUtils.isEmpty(value)){
            return this;
        }
        this.params.put(key,value);
        return this;

    }


    public String build(){
        if(params.size() < Constants.DIGITAL_ONE){
            return baseUrl;
        }
        if (baseUrl.endsWith(CharsetUtil.STRING_DOUBT)) {
            baseUrl = baseUrl.concat(CharsetUtil.STRING_AND);
        }else {
            baseUrl = baseUrl.concat(CharsetUtil.STRING_DOUBT);
        }

        StringJoiner joiner = new StringJoiner(CharsetUtil.STRING_AND);
        params.forEach((k,v)->{
            joiner.add(StringUtils.join(k,CharsetUtil.STRING_EQUAL,v));

        });

        return StringUtils.join(baseUrl, joiner.toString());

    }



}
