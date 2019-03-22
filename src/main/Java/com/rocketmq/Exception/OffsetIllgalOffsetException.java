package com.rocketmq.Exception;

/**
 * @program: com
 * @description:offset不合格Exception
 * @author: liangzr
 * @create: 2019-02-01 10:36
 */
public class OffsetIllgalOffsetException extends Exception {
    public OffsetIllgalOffsetException(String string){
        super(string);
    }
    public OffsetIllgalOffsetException(){
        super();
    }
}
