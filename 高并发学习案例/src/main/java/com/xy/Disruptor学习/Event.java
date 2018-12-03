package com.xy.Disruptor学习;

/**
 * @fileName:Event
 * @author:xy
 * @date:2018/11/24
 * @description: 事件,或者说是 消息体(数据载体，就好比红细胞是氧气载体--用来携带氧气到身体的)
 */
public class Event<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
