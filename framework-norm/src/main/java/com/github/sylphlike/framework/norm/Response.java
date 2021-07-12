package com.github.sylphlike.framework.norm;


import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统规范标准接口返回。
 * <p> 系统规范的标准接口返回，所有接口返回该实体类，具体的业务参数为 T data </p>
 * <p>  time 17:56 2018/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */


public class Response<T> implements Cloneable , Serializable{

    private static final long serialVersionUID = 355207958304927788L;

    /** 成功响应状态码*/
    private static final String    DEFAULT_CODE = "20000";
    /** 成功响应消息摘要*/
    private static final String DEFAULT_MESSAGE = "success";


    /** 响应结果状态码*/
    private String code;

    /** 响应结果消息摘要 */
    private String msg;

    /** 响应结果详细描述 */
    private String subMsg;

    /** 业务数据  */
    private T  data;

    /** 响应时间 */
    private String timestamp = LocalDateTime.now().toString();


    /**
     * 构造方法  返回成功不带业务参数的对象
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @author  Gopal.pan
     */
    public  Response() {
        this.code = DEFAULT_CODE;
        this.msg  = DEFAULT_MESSAGE;
    }


    /**
     * 设置响应码， 响应消息摘要
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param rCode  {@link RCode}
     * @author   Gopal.pan
     */
    public  Response(RCode rCode) {
        this.code   = rCode.getCode();
        this.msg    = rCode.getMsg();
        this.subMsg = rCode.getSubMsg();
    }


    /**
     * 设置响应码， 响应消息摘要 并增加附加讯息
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param rCode         {@link RCode}
     * @param subMsg 附加讯息(对RCode 的 message 补充描述)
     * @author   Gopal.pan
     */
    public  Response(RCode rCode, String subMsg) {
        this.code    = rCode.getCode();
        this.msg     = rCode.getMsg();
        this.subMsg  = subMsg;
    }


    /**
     * 设定业务数据
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param data       业务数据
     * @author Gopal.pan
     */
    public  Response(T data) {
        this.code = DEFAULT_CODE;
        this.msg  = DEFAULT_MESSAGE;
        this.data = data;
    }




    /**
     * 设定业务参数值，该方法不会修改业务响应码
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param data       业务数据
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public Response<T> data(T data){
        this.setData(data);
        return this;
    }






    /**
     * 重置响应码
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param rCode     {@link RCode}
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public Response<T> reset(RCode rCode){
        this.code   = rCode.getCode();
        this.msg    = rCode.getMsg();
        this.subMsg = rCode.getSubMsg();
        return this;
    }



    /**
     * 重置响应状态码
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param rCode             {@link RCode}
     * @param subMsg     附加讯息(对RCode 的 message 补充描述)
     * @return   com.github.sylphlike.norm.Response
     * @author   Gopal.pan
     */
    public Response<T> reset(RCode rCode, String subMsg){
        this.code   = rCode.getCode();
        this.msg    = rCode.getMsg();
        this.subMsg = subMsg;
        return this;

    }



    /**
     * 静态方法，返回成功不带业务参数
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public static <T>  Response<T> success() {
        return new Response<>();
    }


    /**
     * 静态方法，返回成功并设置业务参数
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param t  业务参数
     * @return   com.github.sylphlike.norm.Response
     * @author   Gopal.pan
     */
    public static <T> Response<T> success(T t ){
        return new Response<>(t);
    }


    /**
     * 静态方法，设置响应码和message
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public static <T> Response<T> error(RCode rCode) {
        return new Response<>(rCode);
    }





    /**
     * 静态方法，设置响应码
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param rCode             {@link RCode}
     * @param subMsg     附加讯息(对RCode 的 message 补充描述)
     * @return   com.github.sylphlike.norm.Response
     * @author   Gopal.pan
     */
    public static <T> Response<T> error(RCode rCode, String subMsg) {
        return new Response<>(rCode, subMsg);
    }




    /**
     * 静态方法，设置响应码、message、具体描述信息，
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param e     异常需继承 {@link UniteException}
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    public static <T> Response<T> error(UniteException e){
        Response<T> response = new Response<>();
        response.setCode(e.getCode());
        response.setMsg(e.getMsg());
        return response;
    }





    /**
     * 判断是否成功
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @return boolean
     * @author Gopal.pan
     */
    @Transient
    public boolean isSuccess(){
        return DEFAULT_CODE.equals( this.getCode());

    }


    /**
     * 判断是否失败
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @return boolean
     * @author Gopal.pan
     */
    @Transient
    public boolean isFail(){
        return !DEFAULT_CODE.equals(this.getCode());

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
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
                "code='" + code + '\'' +
                ", message='" + msg + '\'' +
                ", detailMessage='" + subMsg + '\'' +
                ", data=" + data +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
