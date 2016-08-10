package io.xujiaji.dmlib.barrage;

/*
 * Copyright 2016 xujiaji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class BarrageEntity {
    /** 默认*/
    public static final int TYPE_DEFAULT = 5;
    /** 消息*/
    public static final int TYPE_MSG = 6;
    /** 头像*/
    public static final int TYPE_HEAD = 7;
    /** 名字和消息*/
    public static final int TYPE_NAME_MSG = 8;
    /** 头像和消息*/
    public static final int TYPE_HEAD_MSG = 9;
    /** 定制*/
    public static final int TYPE_CUSTOMIZE = 10;
    private int layoutType;
    private String pname;
    private String chatStr;
    private String pic;
    private boolean isLive;

    public BarrageEntity(String pname, String chatStr, String pic) {
        layoutType = type(pname, chatStr, pic);
        this.pname = pname;
        this.chatStr = chatStr;
        this.pic = pic;
        isLive = true;
    }

    public void change(String pname, String chatStr, String pic) {
        layoutType = type(pname, chatStr, pic);
        this.pname = pname;
        this.chatStr = chatStr;
        this.pic = pic;
        isLive = true;
    }

    public void over() {
        isLive = false;
    }

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
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

    public int type(String name, String msg, String head) {
        if (name != null && head != null && msg != null) return TYPE_DEFAULT;
        if (name == null && head != null && msg != null) return TYPE_HEAD_MSG;
        if (name == null && head == null && msg != null) return TYPE_MSG;
        if (name != null && head == null && msg != null) return TYPE_NAME_MSG;
        if (name == null && head != null && msg == null) return TYPE_HEAD;
        return TYPE_CUSTOMIZE;
    }
}
