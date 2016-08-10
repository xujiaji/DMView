package io.xujiaji.dmlib.barrage;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by jiana on 16-8-10.
 */
public class Config {
    private int rootId, nameId, msgId, headId;
    private int rowNum;
    public Config(@LayoutRes int rootId, @IdRes int nameId, @IdRes int msgId, @IdRes int headId) {
        this.rootId = rootId;
        this.nameId = nameId;
        this.msgId = msgId;
        this.headId = headId;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getRootId() {
        return rootId;
    }

    public MyHolder getHolder(ViewGroup parent) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(rootId, parent, false), nameId, msgId, headId);
    }

    public void fillData(BarrageEntity barrage, MyHolder myHolder) {
        if (myHolder.name != null && barrage.getPname() != null) {
            myHolder.name.setText(barrage.getPname());
        }
        if (myHolder.msg != null && barrage.getChatStr() != null) {
            myHolder.msg.setText(barrage.getChatStr());
        }
        if (myHolder.head != null && barrage.getPname() != null) {
            Glide.with(myHolder.head.getContext()).load(barrage.getPic()).into(myHolder.head);
        }
    }

    public static class MyHolder extends BarrageAdapter.BaseHolder {
        TextView name;
        TextView msg;
        ImageView head;

        public MyHolder(View itemView, @IdRes int nameId, @IdRes int msgId, @IdRes int headId) {
            super(itemView);
            if (nameId > 0) {
                name = $(nameId);
            }
            if (msgId > 0) {
                msg = $(msgId);
            }
            if (headId > 0) {
                head = $(headId);
            }

        }
    }
}
