package com.jiaji.dmview;

/**
 * Created by Administrator on 2016/6/6.
 */
public class BarrageEntity {
    private String pname;
    private String chatStr;
    private String pic;
    private boolean isLive;

    public BarrageEntity(String pname, String chatStr, String pic) {
        this.pname = pname;
        this.chatStr = chatStr;
        this.pic = pic;
        isLive = true;
    }

    public void change(String pname, String chatStr, String pic) {
        this.pname = pname;
        this.chatStr = chatStr;
        this.pic = pic;
        isLive = true;
    }

    public void over() {
        isLive = false;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getChatStr() {
        return chatStr;
    }

    public void setChatStr(String chatStr) {
        this.chatStr = chatStr;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
