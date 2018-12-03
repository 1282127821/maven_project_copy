package com.xy.Disruptor学习;
/**
 * @fileName:EventFactory
 * @author:xy
 * @date:2018/11/24
 * @description: 消息体工厂类，用来产生消息体对象的
 */
public class EventFactory implements com.lmax.disruptor.EventFactory<Event>{

    @Override
    public Event newInstance() {
        Event event = new Event();
        event.setData(0);
    System.out.println(event.getData());
        return event;
    }
}
