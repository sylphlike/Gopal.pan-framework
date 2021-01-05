package com.github.sylphlike.framework.norm;


import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统规范标准接口返回。
 *
 * <p> 系统规范的标准接口返回，所有接口返回该实体类，具体的业务参数为 T data.
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */


public class Response<T> implements Cloneable , Serializable{

    private static final long serialVersionUID = 355207958304927788L;

    /** 成功响应状态码*/
    private static final int     DEFAULT_CODE = 20000;
    /** 成功响应消息摘要*/
    private static final String  DEFAULT_MESSAGE = "success";


    /** 执行结果状态码*/
    private int code;


    /** 执行结果消息摘要 */
    private String message;

    /** 业务数据  */
    private T  data;

    /** 执行时间 */
    private String timestamp = LocalDateTime.now().toString();


    /**
     * 构造方法  返回成功不带业务参数的对象
     * <p>  time 15:55 2020/9/16 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @author Gopal.pan
     */
    public  Response() {
        this.code           = DEFAULT_CODE;
        this.message        = DEFAULT_MESSAGE;
    }


    /**
     * 设置响应码， 响应消息摘要
     * <p>  time 17:18 2020/12/21 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param rCode  {@link RCode}
     * @author   Gopal.pan
     */
    public  Response(RCode rCode) {
        this.code           = rCode.getCode();
        this.message        = rCode.getMessage();
    }


    /**
     * 设置响应码， 响应消息摘要 并增加附加讯息
     * <p>  time 17:17 2020/12/21 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param rCode         {@link RCode}
     * @param appendMessage 附加讯息(对RCode 的 message 补充描述)
     * @author   Gopal.pan
     */
    public  Response(RCode rCode, String appendMessage) {
        this.code           = rCode.getCode();
        this.message        = rCode.getMessage() + CharsetUtil.STRING_ENGLISH_COMMA_SPACE + appendMessage;
    }


    /**
     * 设定业务数据
     * <p>  time 16:11 2020/9/16 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param data       业务数据
     * @author Gopal.pan
     */
    public  Response(T data) {
        this.code           = DEFAULT_CODE;
        this.message        = DEFAULT_MESSAGE;
        this.data           = data;
    }




    /**
     * 设定业务参数值，该方法不会修改业务响应码
     * <p>  time 11:36 2020/9/21 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param data       业务数据
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public Response<T> date(T data){
        this.setData(data);
        return this;
    }






    /**
     * 重置响应码
     * <p>  time 16:06 2020/9/16 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param rCode     {@link RCode}
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public Response<T> reset(RCode rCode){
        this.code           = rCode.getCode();
        this.message        = rCode.getMessage();
        return this;
    }



    /**
     * 重置响应状态码
     * <p>  time 11:28 2020/10/21 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param rCode             {@link RCode}
     * @param appendMessage     附加讯息(对RCode 的 message 补充描述)
     * @return   com.github.sylphlike.norm.Response
     * @author   Gopal.pan
     */
    public Response<T> reset(RCode rCode, String appendMessage){
        this.code           = rCode.getCode();
        this.message        = rCode.getMessage() + CharsetUtil.STRING_ENGLISH_COMMA_SPACE + appendMessage;
        return this;

    }



    /**
     * 静态方法，返回成功不带业务参数
     * <p>  time 16:11 2020/9/16 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public static <T>  Response<T> success() {
        return new Response<>();
    }


    /**
     * 静态方法，返回成功并设置业务参数
     * <p>  time 9:35 2020/12/15 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param t  业务参数
     * @return   com.github.sylphlike.norm.Response
     * @author   Gopal.pan
     */
    public static <T> Response<T> success(T t ){
        return new Response<>(t);
    }


    /**
     * 静态方法，设置响应码和message
     * <p>  time 16:11 2020/9/16 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public static <T> Response<T> error(RCode rCode) {
        return new Response<>(rCode);
    }





    /**
     * 静态方法，设置响应码
     * <p>  time 17:27 2020/12/21 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param rCode             {@link RCode}
     * @param appendMessage     附加讯息(对RCode 的 message 补充描述)
     * @return   com.github.sylphlike.norm.Response
     * @author   Gopal.pan
     */
    public static <T> Response<T> error(RCode rCode, String appendMessage) {
        return new Response<>(rCode, appendMessage);
    }




    /**
     * 静态方法，设置响应码、message、具体描述信息，
     * <p>  time 16:36 2020/9/21 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param e     异常需继承 {@link UniteException}
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public static <T> Response<T> error(UniteException e){
        Response<T> response = new Response<>();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }





    /**
     * 判断是否成功
     * <p>  time 16:05 2020/9/16 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return boolean
     * @author Gopal.pan
     */
    @Transient
    public boolean isSuccess(){
        return DEFAULT_CODE == this.getCode();

    }


    /**
     * 判断是否失败
     * <p>  time 16:05 2020/9/16 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return boolean
     * @author Gopal.pan
     */
    @Transient
    public boolean isFail(){
        return DEFAULT_CODE != this.getCode();

    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
