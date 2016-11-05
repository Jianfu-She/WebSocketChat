package org.sjf.dto;

import java.util.Date;

/**
 * Created by SJF on 2016/10/31.
 */
public class Message {
    //发送者
    private int from;
    //发送者名称
    private String fromName;
    //接收者
    private int to;
    //发送的文本
    private String text;
    //发送日期
    private Date date;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
