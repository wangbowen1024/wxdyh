package com.wxdyh.domain.request;

/**
 * EventRequest class
 * 事件推送消息
 *
 * @author BowenWang
 * @date 2019/08/11
 */
public class EventRequest extends BaseRequestMessage {

    /**
     * 	事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    private String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
