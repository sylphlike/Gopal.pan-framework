package com.github.sylphlike.framework.utils.sequence;


/**
 * ID生成客户端
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class IDProvider {



    private static final StandardSequence standardSequence = new StandardSequence();
    private static final GeneSequence     geneSequence     = new GeneSequence();




    /**
     * 标准snowflake 算法
     * <p>  time 13:56 2020/9/22 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     *
     * @return long
     * @author Gopal.pan
     */
    public static long uniqueID(){
        return standardSequence.nextId();
    }


    /**
     * 雪花算法 + 基因法
     * <p>  time 11:22 2020/9/23 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param originalId  原始序列号
     * @return long
     * @author Gopal.pan
     */
    public static long uniqueGeneID(Long originalId){
       return geneSequence.nextId(originalId);
    }


}
