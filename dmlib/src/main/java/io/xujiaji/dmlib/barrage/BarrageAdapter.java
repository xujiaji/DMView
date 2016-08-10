package io.xujiaji.dmlib.barrage;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.xujiaji.dmlib.R;

public class BarrageAdapter extends RecyclerView.Adapter<BarrageAdapter.BaseHolder> {
    private Context context;
    private List<BarrageEntity> barrageList;
    private Config config;
    public BarrageAdapter(List<BarrageEntity> barrageList) {
        this.barrageList = barrageList;
    }

    @Override
    public BarrageAdapter.BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case BarrageEntity.TYPE_DEFAULT:
                return new BarrageViewHolder(LayoutInflater.from(context).inflate(R.layout.barrage, parent, false));
            case BarrageEntity.TYPE_MSG:
                return new MsgHolder(LayoutInflater.from(context).inflate(R.layout.barrage_msg, parent, false));
            case BarrageEntity.TYPE_HEAD_MSG:
                return new HeadMsgHolder(LayoutInflater.from(context).inflate(R.layout.barrage_head_msg, parent, false));
            case BarrageEntity.TYPE_HEAD:
                return new HeadHolder(LayoutInflater.from(context).inflate(R.layout.barrage_head, parent, false));
            case BarrageEntity.TYPE_NAME_MSG:
                return new NameMsgHolder(LayoutInflater.from(context).inflate(R.layout.barrage_name_msg, parent, false));
            case BarrageEntity.TYPE_CUSTOMIZE:
                return config.getHolder(parent);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (config != null) {
            return BarrageEntity.TYPE_CUSTOMIZE;
        }
        return barrageList.get(position).getLayoutType();
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        BarrageEntity barrage = barrageList.get(position);
        if (holder instanceof BarrageViewHolder) {
            BarrageViewHolder mHolder = (BarrageViewHolder) holder;
            mHolder.tvBarrageMsg.setText(barrage.getChatStr());
            mHolder.tvBarrageName.setText(barrage.getPname());
            Glide.with(context).load(barrage.getPic()).into(mHolder.imgBarrageHead);
        }

        if (holder instanceof MsgHolder) {
            MsgHolder mHolder = (MsgHolder) holder;
            mHolder.tvBarrageMsg.setText(barrage.getChatStr());
        }

        if (holder instanceof HeadMsgHolder) {
            HeadMsgHolder mHolder = (HeadMsgHolder) holder;
            mHolder.tvBarrageMsg.setText(barrage.getChatStr());
            Glide.with(context).load(barrage.getPic()).into(mHolder.imgBarrageHead);
        }

        if (holder instanceof HeadHolder) {
            HeadHolder mHolder = (HeadHolder) holder;
            Glide.with(context).load(barrage.getPic()).into(mHolder.imgBarrageHead);
        }

        if (holder instanceof NameMsgHolder) {
            NameMsgHolder mHolder = (NameMsgHolder) holder;
            mHolder.tvBarrageMsg.setText(barrage.getChatStr());
            mHolder.tvBarrageName.setText(barrage.getPname());
        }

        if (holder instanceof Config.MyHolder) {
            config.fillData(barrage, (Config.MyHolder) holder);

        }

    }


    public void setConfigHolder(Config configHolder) {
        this.config = configHolder;
    }

    @Override
    public int getItemCount() {
        return barrageList.size();
    }

    static class BaseHolder extends RecyclerView.ViewHolder {
        View view;

        public BaseHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        protected <T extends View> T $(@IdRes int id) {
            return (T) view.findViewById(id);
        }
    }

    static class BarrageViewHolder extends BaseHolder {
        TextView tvBarrageName;
        TextView tvBarrageMsg;
        ImageView imgBarrageHead;

        public BarrageViewHolder(View itemView) {
            super(itemView);
            tvBarrageName = $(R.id.tvBarrageName);
            tvBarrageMsg = $(R.id.tvBarrageMsg);
            imgBarrageHead = $(R.id.imgBarrageHead);
        }
    }

    static class MsgHolder extends BaseHolder {
        TextView tvBarrageMsg;
        public MsgHolder(View itemView) {
            super(itemView);
            tvBarrageMsg = $(R.id.tvBarrageMsg);
        }
    }

    static class HeadMsgHolder extends BaseHolder {
        TextView tvBarrageMsg;
        ImageView imgBarrageHead;

        public HeadMsgHolder(View itemView) {
            super(itemView);
            tvBarrageMsg = $(R.id.tvBarrageMsg);
            imgBarrageHead = $(R.id.imgBarrageHead);
        }
    }

    static class HeadHolder extends BaseHolder {
        ImageView imgBarrageHead;
        public HeadHolder(View itemView) {
            super(itemView);
            imgBarrageHead = $(R.id.imgBarrageHead);
        }
    }

    static class NameMsgHolder extends BaseHolder {
        TextView tvBarrageName;
        TextView tvBarrageMsg;
        public NameMsgHolder(View itemView) {
            super(itemView);
            tvBarrageName = $(R.id.tvBarrageName);
            tvBarrageMsg = $(R.id.tvBarrageMsg);
        }
    }

}
